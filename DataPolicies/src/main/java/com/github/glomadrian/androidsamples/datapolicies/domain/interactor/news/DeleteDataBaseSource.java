package com.github.glomadrian.androidsamples.datapolicies.domain.interactor.news;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.InteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutor;
import com.github.glomadrian.androidsamples.datapolicies.domain.interactor.AbstractInteractor;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.database.DataBaseDataSource;

/**
 * @author Adrián García Lomas
 */
public class DeleteDataBaseSource extends AbstractInteractor {

  private DataBaseDataSource dataBaseDataSource;

  public DeleteDataBaseSource(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor, DataBaseDataSource dataBaseDataSource) {
    super(mainThreadExecutor, interactorExecutor);
    this.dataBaseDataSource = dataBaseDataSource;
  }

  public void execute() {
    launch();
  }

  @Override
  public void run() {
    dataBaseDataSource.clear();
  }
}
