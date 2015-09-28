package com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.InteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutor;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.AbstractInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepositoryImp;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsPolices;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class GetTodayNewsInteractor extends AbstractInteractor {

  protected NewsRepositoryImp newsRepository;
  private GetTodayNewsCallback getTodayNewsCallback;

  public GetTodayNewsInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor, NewsRepositoryImp newsRepository) {
    super(mainThreadExecutor, interactorExecutor);
    this.newsRepository = newsRepository;
  }

  public void execute(GetTodayNewsCallback getTodayNewsCallback) {
    this.getTodayNewsCallback = getTodayNewsCallback;
    launch();
  }

  @Override
  public void run() {
    try {
      List<NewItem> newItems = newsRepository.withPolicy(NewsPolices.DATABASE_FIRST).getTodayNews();
      doOnNews(newItems);
      doObtained(newsRepository.getLastDataSourceUse());
    } catch (Exception e) {
      doOnError();
    }
  }

  protected void doOnNews(final List<NewItem> newItems) {
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        getTodayNewsCallback.onTodayNews(newItems);
      }
    });
  }

  protected void doOnError() {
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        getTodayNewsCallback.onError();
      }
    });
  }

  protected void doObtained(final NewsDataSources newsDataSources) {
    launchOnMainThread(new Runnable() {
      @Override
      public void run() {
        getTodayNewsCallback.obtainedFrom(newsDataSources);
      }
    });
  }

  public interface GetTodayNewsCallback {

    void onTodayNews(List<NewItem> news);

    void obtainedFrom(NewsDataSources dataSource);

    void onError();
  }
}
