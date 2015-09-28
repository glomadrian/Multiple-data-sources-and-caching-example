package com.github.glomadrian.androidsamples.datapolicies.domain.repository;

import android.test.AndroidTestCase;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.RetrofitAlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyDocToNewItemMapper;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyResponseToNews;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepositoryImp;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud.AlchemyCloudDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud.CloudNewsDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.database.DataBaseDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.database.NewFileDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsCloudOnlyWIthUpdate;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsDataBaseFirstPolicy;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsPolices;
import java.io.File;
import junit.framework.Assert;

/**
 * @author Adrián García Lomas
 */
public class NewsRepositoryImpTest extends AndroidTestCase {

  private static final String FILENAME = "news.json";
  private NewsRepositoryImp newsRepository;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    deleteDataBaseFiles();
    init();
  }

  @Override
  public void tearDown() throws Exception {
    super.tearDown();
    deleteDataBaseFiles();
  }

  private void deleteDataBaseFiles() {
    File file = new File(getContext().getFilesDir(), FILENAME);
    file.delete();
  }

  private void init() {
    String baseUrl = "https://access.alchemyapi.com";
    String apiKey = "fece4ff013c0d873958a218f9491e4f127b8848e";
    String rreturn = "enriched.url.title,enriched.url.url";
    String type = "positive";
    String returnType = "json";

    RetrofitAlchemyApi retrofitAlchemyApi =
        new RetrofitAlchemyApi(baseUrl, apiKey, rreturn, type, returnType);
    DataBaseDataSource dataBaseDataSource = new NewFileDataSource(getContext());
    AlchemyResponseToNews alchemyResponseToNews =
        new AlchemyResponseToNews(new AlchemyDocToNewItemMapper());

    CloudNewsDataSource cloudNewsDataSource =
        new AlchemyCloudDataSource(alchemyResponseToNews, retrofitAlchemyApi);

    NewsDataBaseFirstPolicy newsDataBaseFirstPolicy =
        new NewsDataBaseFirstPolicy(dataBaseDataSource, cloudNewsDataSource);
    NewsCloudOnlyWIthUpdate newsCloudOnlyWIthUpdate =
        new NewsCloudOnlyWIthUpdate(cloudNewsDataSource, dataBaseDataSource);
    newsRepository = new NewsRepositoryImp(newsDataBaseFirstPolicy, newsCloudOnlyWIthUpdate);
  }

  public void testRepositoryDefaultPolicy() throws Exception {
    newsRepository.getTodayNews();
    Assert.assertEquals(NewsDataSources.CLOUD, newsRepository.getLastDataSourceUse());
  }

  public void testRepositorySecondTryDefaultPolicy() {
    newsRepository.getTodayNews();
    newsRepository.getTodayNews();
    Assert.assertEquals(NewsDataSources.DATABASE, newsRepository.getLastDataSourceUse());
  }

  public void testCloudOnlyPolicy() {
    //Force to use default policy and store on database
    newsRepository.withPolicy(NewsPolices.DATABASE_FIRST).getTodayNews();

    newsRepository.withPolicy(NewsPolices.DATABASE_FIRST).getTodayNews();

    newsRepository.withPolicy(NewsPolices.NETWORK_ONLY).getTodayNews();

    Assert.assertEquals(NewsDataSources.CLOUD, newsRepository.getLastDataSourceUse());
  }
}
