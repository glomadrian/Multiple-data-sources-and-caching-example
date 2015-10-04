package com.github.glomadrian.androidsamples.datapolicies.data.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.cloud.CloudNewsDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.DataBaseDataSource;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsCloudOnlyWIthUpdate extends NewsCloudOnlyPolicy {

  private DataBaseDataSource dataBaseDataSource;

  public NewsCloudOnlyWIthUpdate(CloudNewsDataSource cloudNewsDataSource,
      DataBaseDataSource dataBaseDataSource) {
    super(cloudNewsDataSource);
    this.dataBaseDataSource = dataBaseDataSource;
  }

  @Override
  public List<NewItem> getTodayNews() {
    List<NewItem> newItems = super.getTodayNews();
    dataBaseDataSource.saveNews(newItems);
    return newItems;
  }
}
