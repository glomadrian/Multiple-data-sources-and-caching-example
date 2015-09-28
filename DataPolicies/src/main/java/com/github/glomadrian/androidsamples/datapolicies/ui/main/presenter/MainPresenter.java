package com.github.glomadrian.androidsamples.datapolicies.ui.main.presenter;

import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.DeleteDataBaseSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.GetTodayNewsCloudInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.GetTodayNewsInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;
import com.github.glomadrian.androidsamples.datapolicies.ui.LifeCyclePresenter;
import com.github.glomadrian.androidsamples.datapolicies.ui.main.view.MainActivity;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class MainPresenter extends LifeCyclePresenter<MainActivity> {

  private GetTodayNewsInteractor getTodayNewsInteractor;
  private GetTodayNewsCloudInteractor getTodayNewsCloudInteractor;
  private DeleteDataBaseSource deleteDataBaseSource;

  public MainPresenter(GetTodayNewsInteractor getTodayNewsInteractor,
      DeleteDataBaseSource deleteDataBaseSource,
      GetTodayNewsCloudInteractor getTodayNewsCloudInteractor) {
    this.getTodayNewsInteractor = getTodayNewsInteractor;
    this.deleteDataBaseSource = deleteDataBaseSource;
    this.getTodayNewsCloudInteractor = getTodayNewsCloudInteractor;
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public void onViewReady() {
    super.onViewReady();
    getTodayNews();
  }

  public void updateAction() {
    getTodayNews();
  }

  public void getTodayNews() {
    view.removeNews();
    getTodayNewsInteractor.execute(new GetTodayNewsCallback());
  }

  public void deleteDbAction() {
    deleteDataBaseSource.execute();
    view.showInfo("Database deleted");
  }

  public void cloudAction() {
    view.removeNews();
    getTodayNewsCloudInteractor.execute(new GetTodayNewsCallback());
  }

  private class GetTodayNewsCallback implements GetTodayNewsInteractor.GetTodayNewsCallback {

    @Override
    public void onTodayNews(List<NewItem> news) {
      view.addNews(news);
    }

    @Override
    public void obtainedFrom(NewsDataSources dataSource) {
      view.showInfo("updated from: " + dataSource.name());
    }

    @Override
    public void onError() {
      view.showInfo("Error, limit exceeded? Wait some time");
    }
  }
}
