package com.github.glomadrian.androidsamples.datapolicies.ui.newitem.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.glomadrian.androidsamples.R;
import com.github.glomadrian.androidsamples.datapolicies.app.DependencyProvider;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.ui.View;
import com.github.glomadrian.androidsamples.datapolicies.ui.newitem.presenter.NewItemPresenter;

/**
 * @author Adrián García Lomas
 */
public class NewItemView implements View {

  private Context context;
  private ViewGroup viewGroup;
  private TextView title;
  private android.view.View view;
  private NewItem newItem;
  private NewItemPresenter newItemPresenter;

  public NewItemView(Context context, ViewGroup viewGroup) {
    this.context = context;
    this.viewGroup = viewGroup;
    provideDependency();
    initView();
    initializeActions();
  }

  private void provideDependency() {
    newItemPresenter = DependencyProvider.provideNewItemPresenter();
  }

  private void initView() {
    this.view = LayoutInflater.from(context).inflate(R.layout.newtem_view, viewGroup, false);
    this.title = (TextView) view.findViewById(R.id.title);
  }

  public void render(NewItem newItem) {
    title.setText(newItem.getTitle());
    this.newItem = newItem;
  }

  void initializeActions() {
    view.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        newItemPresenter.onNewItemAction(NewItemView.this, newItem.getUrl());
      }
    });
  }

  public android.view.View getView() {
    return view;
  }
}
