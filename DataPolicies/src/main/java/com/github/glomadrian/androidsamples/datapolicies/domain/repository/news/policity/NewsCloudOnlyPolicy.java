package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud.CloudNewsDataSource;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsCloudOnlyPolicy implements NewsPolice {

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
