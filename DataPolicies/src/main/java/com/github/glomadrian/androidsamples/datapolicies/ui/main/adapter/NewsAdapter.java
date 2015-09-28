package com.github.glomadrian.androidsamples.datapolicies.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.ui.newitem.view.NewItemView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsAdapter extends RecyclerView.Adapter {

  private Context context;
  private List<NewItem> newItems;

  public NewsAdapter(Context context) {
    this.context = context;
    this.newItems = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return makeNewItemView(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    NewItem newItem = newItems.get(position);
    ((NewItemViewHolder) holder).newItemView.render(newItem);
  }

  @Override
  public int getItemCount() {
    return newItems.size();
  }

  public void addNews(List<NewItem> newItems) {
    this.newItems.addAll(newItems);
    notifyDataSetChanged();
  }

  private RecyclerView.ViewHolder makeNewItemView(ViewGroup viewGroup) {
    NewItemView newItemView = new NewItemView(context, viewGroup);
    return new NewItemViewHolder(newItemView);
  }

  public void removeNews() {
    newItems.clear();
    notifyDataSetChanged();
  }

  private class NewItemViewHolder extends RecyclerView.ViewHolder {

    protected NewItemView newItemView;

    public NewItemViewHolder(NewItemView newItemView) {
      super(newItemView.getView());
      this.newItemView = newItemView;
    }
  }
}
