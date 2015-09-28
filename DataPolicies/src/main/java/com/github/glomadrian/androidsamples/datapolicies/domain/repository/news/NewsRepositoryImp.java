package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsCloudOnlyWIthUpdate;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsDataBaseFirstPolicy;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsPolice;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsPolices;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsRepositoryImp implements NewsRepository {

  private NewsPolice lastPoliceUsed;
  private NewsPolices policy;
  private NewsDataBaseFirstPolicy newsDataBaseFirstPolicy;
  private NewsCloudOnlyWIthUpdate newsCloudOnlyWIthUpdate;

  public NewsRepositoryImp(NewsDataBaseFirstPolicy newsDataBaseFirstPolicy,
      NewsCloudOnlyWIthUpdate newsCloudOnlyWIthUpdate) {
    this.newsDataBaseFirstPolicy = newsDataBaseFirstPolicy;
    this.newsCloudOnlyWIthUpdate = newsCloudOnlyWIthUpdate;
    init();
  }

  private void init() {
    policy = NewsPolices.DATABASE_FIRST;
  }

  public void setPolicy(NewsPolices policy) {
    this.policy = policy;
  }

  public NewsRepositoryImp withPolicy(NewsPolices policy) {
    this.policy = policy;
    return this;
  }

  /**
   * In this case and for learning proposes i'm using a switch statement but a factory or some
   * pattern to obtain and execute the proper police is more cleaner and effective.
   * Decouple the repository from the policies to be used is fully recommended
   */
  @Override
  public List<NewItem> getTodayNews() {
    switch (policy) {
      case DATABASE_FIRST:
        return databaseFirst();
      case NETWORK_ONLY_WITH_UPDATE:
        return networkOnlyWithUpdate();
      default:
        return databaseFirst();
    }
  }

  private List<NewItem> databaseFirst() {
    lastPoliceUsed = newsDataBaseFirstPolicy;
    return newsDataBaseFirstPolicy.getTodayNews();
  }

  private List<NewItem> networkOnlyWithUpdate() {
    lastPoliceUsed = newsCloudOnlyWIthUpdate;
    return newsCloudOnlyWIthUpdate.getTodayNews();
  }

  public NewsDataSources getLastDataSourceUse() {
    return lastPoliceUsed.getLastDataSourceUsed();
  }
}
