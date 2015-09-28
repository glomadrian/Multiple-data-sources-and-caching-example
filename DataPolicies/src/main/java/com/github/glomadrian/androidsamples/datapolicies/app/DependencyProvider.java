package com.github.glomadrian.androidsamples.datapolicies.app;

import android.content.Context;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.DefaultInteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.InteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutorImp;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.AlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.RetrofitAlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.DeleteDataBaseSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.GetTodayNewsCloudInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news.GetTodayNewsInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyDocToNewItemMapper;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.AlchemyResponseToNews;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepositoryImp;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud.AlchemyCloudDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud.CloudNewsDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.database.DataBaseDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.database.NewFileDataSource;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsCloudOnlyPolicy;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsCloudOnlyWIthUpdate;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity.NewsDataBaseFirstPolicy;
import com.github.glomadrian.androidsamples.datapolicies.ui.main.presenter.MainPresenter;
import com.github.glomadrian.androidsamples.datapolicies.ui.newitem.presenter.NewItemPresenter;

/**
 * For learning proposes Dagger / Dagger2 is not used on this project, but the project follows the
 * Inversion principle , this static class provide the dependencies instances instead of an
 * injector
 *
 * @author Adrián García Lomas
 */
public final class DependencyProvider {

  public static Context context;
  private static MainPresenter mainPresenter = null;
  private static MainThreadExecutor<Runnable> mainThreadExecutor = null;
  private static InteractorExecutor interactorExecutor = null;
  private static NewsRepositoryImp newsRepository = null;

  private DependencyProvider() {
    //No instances allowed
  }

  public static MainPresenter provideMainPresenter() {
    if (mainPresenter == null) {
      mainPresenter =
          new MainPresenter(provideGetTodayNewsInteractor(), provideDeleteDataBaseSource(),
              provideGetTodayNewsCloudInteractor());
    }
    return mainPresenter;
  }

  public static GetTodayNewsInteractor provideGetTodayNewsInteractor() {

    return new GetTodayNewsInteractor(provideMainThreadExecutor(), provideInteractorExecutor(),
        provideNewsRepository());
  }

  public static GetTodayNewsCloudInteractor provideGetTodayNewsCloudInteractor() {

    return new GetTodayNewsCloudInteractor(provideMainThreadExecutor(), provideInteractorExecutor(),
        provideNewsRepository());
  }

  public static MainThreadExecutor provideMainThreadExecutor() {
    if (mainThreadExecutor == null) {
      mainThreadExecutor = new MainThreadExecutorImp();
    }
    return mainThreadExecutor;
  }

  public static InteractorExecutor provideInteractorExecutor() {
    if (interactorExecutor == null) {
      interactorExecutor = new DefaultInteractorExecutor();
    }
    return interactorExecutor;
  }

  public static NewsRepositoryImp provideNewsRepository() {
    if (newsRepository == null) {
      newsRepository =
          new NewsRepositoryImp(provideNewsDataBaseFirstPolicy(), proNewsCloudOnlyWIthUpdate());
    }
    return newsRepository;
  }

  private static NewsDataBaseFirstPolicy provideNewsDataBaseFirstPolicy() {
    return new NewsDataBaseFirstPolicy(provideDataBaseDataSource(), provideCloudNewsDataSource());
  }

  private static NewsCloudOnlyPolicy provideNewsCloudOnlyPolicy() {
    return new NewsCloudOnlyPolicy(provideCloudNewsDataSource());
  }

  private static NewsCloudOnlyWIthUpdate proNewsCloudOnlyWIthUpdate() {
    return new NewsCloudOnlyWIthUpdate(provideCloudNewsDataSource(), provideDataBaseDataSource());
  }

  private static DataBaseDataSource provideDataBaseDataSource() {
    return new NewFileDataSource(context);
  }

  private static CloudNewsDataSource provideCloudNewsDataSource() {
    return new AlchemyCloudDataSource(provideAlchemyResponseToNews(), provideAlchemyApi());
  }

  private static AlchemyResponseToNews provideAlchemyResponseToNews() {
    return new AlchemyResponseToNews(new AlchemyDocToNewItemMapper());
  }

  public static DeleteDataBaseSource provideDeleteDataBaseSource() {
    return new DeleteDataBaseSource(provideMainThreadExecutor(), provideInteractorExecutor(),
        provideDataBaseDataSource());
  }

  private static AlchemyApi provideAlchemyApi() {
    String baseUrl = "https://access.alchemyapi.com";
    String apiKey = "fece4ff013c0d873958a218f9491e4f127b8848e";
    String rreturn = "enriched.url.title,enriched.url.url";
    String type = "positive";
    String returnType = "json";

    return new RetrofitAlchemyApi(baseUrl, apiKey, rreturn, type, returnType);
  }

  public static NewItemPresenter provideNewItemPresenter() {
    return new NewItemPresenter();
  }
}
