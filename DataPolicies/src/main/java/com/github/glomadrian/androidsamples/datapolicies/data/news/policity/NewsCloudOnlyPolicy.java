package com.github.glomadrian.androidsamples.datapolicies.data.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.cloud.CloudNewsDataSource;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsCloudOnlyPolicy implements NewsPolicy {

  private CloudNewsDataSource cloudNewsDataSource;

  public NewsCloudOnlyPolicy(CloudNewsDataSource cloudNewsDataSource) {
    this.cloudNewsDataSource = cloudNewsDataSource;
  }

  @Override
  public NewsDataSources getLastDataSourceUsed() {
    return NewsDataSources.CLOUD;
  }

  @Override
  public List<NewItem> getTodayNews() {
    return cloudNewsDataSource.getTodayNews();
  }
}
