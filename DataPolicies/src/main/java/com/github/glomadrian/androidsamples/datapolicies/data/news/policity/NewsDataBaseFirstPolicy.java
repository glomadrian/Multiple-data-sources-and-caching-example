package com.github.glomadrian.androidsamples.datapolicies.data.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.cloud.CloudNewsDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.DataBaseDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.exception.FileDataSourceException;
import java.util.List;

/**
 * Return the database stored news first , if there are not stored news then get from cloud
 *
 * @author Adrián García Lomas
 */
public class NewsDataBaseFirstPolicy implements NewsPolicy {

  private DataBaseDataSource dataBaseDataSource;
  private CloudNewsDataSource cloudNewsDataSource;
  private NewsDataSources newsDataSources;

  public NewsDataBaseFirstPolicy(DataBaseDataSource dataBaseDataSource,
      CloudNewsDataSource cloudNewsDataSource) {
    this.dataBaseDataSource = dataBaseDataSource;
    this.cloudNewsDataSource = cloudNewsDataSource;
  }

  @Override
  public List<NewItem> getTodayNews() {
    try {
      return obtainFromDataBase();
    } catch (FileDataSourceException e) {
      List<NewItem> news = obtainFromCloud();
      dataBaseDataSource.saveNews(news);
      return news;
    }
  }

  private List<NewItem> obtainFromDataBase() {
    newsDataSources = NewsDataSources.DATABASE;
    return dataBaseDataSource.getToadyNews();
  }

  private List<NewItem> obtainFromCloud() {
    newsDataSources = NewsDataSources.CLOUD;
    return cloudNewsDataSource.getTodayNews();
  }

  public NewsDataSources getLastDataSourceUsed() {
    return newsDataSources;
  }
}
