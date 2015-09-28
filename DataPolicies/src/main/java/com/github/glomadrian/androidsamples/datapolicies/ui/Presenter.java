package com.github.glomadrian.androidsamples.datapolicies.ui;

/**
 * @author Adrián García Lomas
 */
public abstract class Presenter<T extends View> {

  protected T view;

  /**
   * The view must be attached to the presenter in order to work
   */
  public void attachView(T view) {
    this.view = view;
  }

  /**
   * The onViewReady method is used when the layout has been inflated and the view
   * is fully ready
   */
  public void onViewReady() {
    //Empty
  }
}
