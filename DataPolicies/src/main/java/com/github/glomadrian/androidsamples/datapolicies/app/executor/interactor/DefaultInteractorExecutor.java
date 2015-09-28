package com.github.glomadrian.androidsamples.datapolicies.app.executor.interactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Default implementation of InteractorExecutor
 * In this implementation A ThreadPoolExecutor is used to launch the threads
 *
 * @author Adrián García Lomas
 */
public class DefaultInteractorExecutor implements InteractorExecutor {

  private static final int CORE_POOL_SIZE = 3;
  private static final int MAX_POOL_SIZE = 5;
  private static final int KEEP_ALIVE_TIME = 120;
  private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
  private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

  private ThreadPoolExecutor threadPoolExecutor;

  public DefaultInteractorExecutor() {
    threadPoolExecutor =
        new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT,
            WORK_QUEUE);
  }

  @Override
  public void execute(Interactor interactor) {
    if (interactor != null) {
      threadPoolExecutor.submit(interactor);
    }
  }
}
