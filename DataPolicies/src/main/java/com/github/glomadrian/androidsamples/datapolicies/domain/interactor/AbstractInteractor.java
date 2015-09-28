package com.github.glomadrian.androidsamples.datapolicies.domain.interactor;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.Interactor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor.InteractorExecutor;
import com.github.glomadrian.androidsamples.datapolicies.app.executor.main.MainThreadExecutor;

/**
 * @author adrian
 */
public abstract class AbstractInteractor implements Interactor {

  protected MainThreadExecutor mainThreadExecutor;
  protected InteractorExecutor interactorExecutor;

  protected AbstractInteractor(MainThreadExecutor mainThreadExecutor,
      InteractorExecutor interactorExecutor) {
    this.mainThreadExecutor = mainThreadExecutor;
    this.interactorExecutor = interactorExecutor;
  }

  protected void launch() {
    interactorExecutor.execute(this);
  }

  protected void launchOnMainThread(Runnable runnable) {
    mainThreadExecutor.execute(runnable);
  }
}
