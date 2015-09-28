package com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor;

/**
 * Execute a interactor in the same thread that execute the interactor
 * Careful use in the UI Thread, the execution may block the UI
 *
 * @author Adrián García Lomas
 */
public class NoThreadInteractorExecutor implements InteractorExecutor {

  @Override
  public void execute(Interactor interactor) {
    interactor.run();
  }
}
