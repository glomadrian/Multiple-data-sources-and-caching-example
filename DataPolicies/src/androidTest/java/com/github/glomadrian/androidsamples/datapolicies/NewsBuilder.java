package com.github.glomadrian.androidsamples.datapolicies;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewsBuilder {

  private int amount = 1;

  public NewsBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public List<NewItem> build() {
    List<NewItem> newItems = new ArrayList<>();

    for (int i = 0; i < amount; i++) {
      NewItem newItemObject = new NewItem();
      newItemObject.setTitle("Test title");
      newItemObject.setUrl("Test url");
      newItems.add(newItemObject);
    }

    return newItems;
  }
}
