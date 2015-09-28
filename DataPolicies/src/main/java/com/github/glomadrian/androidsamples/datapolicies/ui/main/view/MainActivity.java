package com.github.glomadrian.androidsamples.datapolicies.ui.main.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.github.glomadrian.androidsamples.R;
import com.github.glomadrian.androidsamples.datapolicies.app.DependencyProvider;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.ui.View;
import com.github.glomadrian.androidsamples.datapolicies.ui.main.adapter.NewsAdapter;
import com.github.glomadrian.androidsamples.datapolicies.ui.main.presenter.MainPresenter;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class MainActivity extends AppCompatActivity implements View {

  private MainPresenter mainPresenter;
  private RecyclerView newsRecyclerView;
  private NewsAdapter newsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainPresenter = DependencyProvider.provideMainPresenter();
    mainPresenter.onCreate();
    mainPresenter.attachView(this);
    setContentView(R.layout.main_view);
    bindViews();
    createAdapter();
    initNewsRecyclerView();
  }

  private void createAdapter() {
    newsAdapter = new NewsAdapter(this);
  }

  private void initNewsRecyclerView() {
    newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    newsRecyclerView.setAdapter(newsAdapter);
  }

  private void bindViews() {
    newsRecyclerView = (RecyclerView) findViewById(R.id.news_list);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mainPresenter.onViewReady();
  }

  public void addNews(List<NewItem> newItemList) {
    newsAdapter.addNews(newItemList);
  }

  public void showInfo(String name) {
    Snackbar.make(findViewById(android.R.id.content), name, Snackbar.LENGTH_SHORT).show();
  }

  public void removeNews() {
    newsAdapter.removeNews();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
      case R.id.force_update:
        mainPresenter.updateAction();
        return true;
      case R.id.delete_db:
        mainPresenter.deleteDbAction();
        return true;
      case R.id.network:
        mainPresenter.cloudAction();
        return true;
      default:
        return false;
    }
  }
}
