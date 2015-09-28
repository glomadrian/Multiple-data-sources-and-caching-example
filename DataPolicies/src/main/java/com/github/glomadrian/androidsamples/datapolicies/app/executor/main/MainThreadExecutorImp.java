package com.github.glomadrian.androidsamples.datapolicies.app.executor.main;

import android.os.Handler;
import android.os.Looper;

/**
 * MainThreadExecutor executes Runnable on the Android Main Thread
 * Usually callbacks from interactors
 * The runnable executed by this executor can modify the UI
 *
 * @author Adrián García Lomas
 */
public class MainThreadExecutorImp implements MainThreadExecutor<Runnable> {

  private Handler handler;

  public MainThreadExecutorImp() {
    this.handler = new Handler(Looper.getMainLooper());
  }

  @Override public void execute(Runnable runnable) {
    handler.post(runnable);
  }
}
