package com.github.glomadrian.androidsamples.datapolicies.data.news.policity;

import android.test.AndroidTestCase;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.RetrofitAlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyDocToNewItemMapper;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyResponseToNews;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.cloud.AlchemyCloudDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.cloud.CloudNewsDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.DataBaseDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.NewFileDataSource;
import java.io.File;
import java.util.List;
import junit.framework.Assert;

/**
 * @author Adrián García Lomas
 */
public class NewsDataBaseFirstPolicyTest extends AndroidTestCase {

  private static final String FILENAME = "news.json";
  private NewsDataBaseFirstPolicy newsDataBaseFirstPolicy;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    deleteDataBaseFiles();
    init();
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

    newsDataBaseFirstPolicy = new NewsDataBaseFirstPolicy(dataBaseDataSource, cloudNewsDataSource);
  }

  public void testNewsDataBaseFirstShouldGetFromNetworkFirst() throws Exception {

    List<NewItem> newItems = newsDataBaseFirstPolicy.getTodayNews();

    Assert.assertNotNull(newItems);
    Assert.assertEquals(NewsDataSources.CLOUD, newsDataBaseFirstPolicy.getLastDataSourceUsed());
  }

  public void testNewsDataBaseFirstShouldGetFromDataBaseSecondTime() {

    newsDataBaseFirstPolicy.getTodayNews();
    newsDataBaseFirstPolicy.getTodayNews();
    Assert.assertEquals(NewsDataSources.DATABASE, newsDataBaseFirstPolicy.getLastDataSourceUsed());
  }
}
