package com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.InteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutor;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepositoryImp;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsPolices;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class GetTodayNewsCloudInteractor extends GetTodayNewsInteractor {

  public GetTodayNewsCloudInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor, NewsRepositoryImp newsRepository) {
    super(mainThreadExecutor, interactorExecutor, newsRepository);
  }

  @Override
  public void run() {
    try {
      List<NewItem> newItems =
          newsRepository.withPolicy(NewsPolices.NETWORK_ONLY_WITH_UPDATE).getTodayNews();
      doOnNews(newItems);
      doObtained(newsRepository.getLastDataSourceUse());
    } catch (Exception e) {
      doOnError();
    }
  }
}
