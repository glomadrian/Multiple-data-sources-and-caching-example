package com.github.glomadrian.androidsamples.datapolicies.domain.mapper;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Doc;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;

/**
 * Given a doc return a new
 *
 * @author Adrián García Lomas
 */
public class AlchemyDocToNewItemMapper implements NewItemMapper<Doc> {

  @Override
  public NewItem map(Doc doc) {

    NewItem mappedNewItem = new NewItem();
    mappedNewItem.setTitle(doc.getSource().getEnriched().getUrl().getTitle());
    mappedNewItem.setUrl(doc.getSource().getEnriched().getUrl().getUrl());
    return mappedNewItem;
  }
}
