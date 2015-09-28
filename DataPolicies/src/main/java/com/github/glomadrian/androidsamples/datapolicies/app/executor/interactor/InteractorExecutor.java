package com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor;

import com.github.glomadrian.androidsamples.datapolicies.app.executor.Executor;

/**
 * Interactor executor interface
 *
 * @author Adrián García Lomas
 */
public interface InteractorExecutor extends Executor<Interactor> {

  void execute(Interactor interactor);
}
