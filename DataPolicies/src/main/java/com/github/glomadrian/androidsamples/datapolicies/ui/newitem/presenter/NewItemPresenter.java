package com.github.glomadrian.androidsamples.datapolicies.ui.newitem.presenter;

import android.content.Intent;
import android.net.Uri;
import com.github.glomadrian.androidsamples.datapolicies.ui.Presenter;
import com.github.glomadrian.androidsamples.datapolicies.ui.newitem.view.NewItemView;

/**
 * @author Adrián García Lomas
 */
public class NewItemPresenter extends Presenter<NewItemView> {

  public void onNewItemAction(NewItemView newItemView, String url) {
    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    newItemView.getView().getContext().startActivity(browserIntent);
  }
}
