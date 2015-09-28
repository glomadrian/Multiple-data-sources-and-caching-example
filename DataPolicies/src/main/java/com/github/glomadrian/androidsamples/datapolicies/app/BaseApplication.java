package com.github.glomadrian.androidsamples.datapolicies.app;

import android.app.Application;

/**
 * @author Adrián García Lomas
 */
public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    DependencyProvider.context = this;
  }
}
