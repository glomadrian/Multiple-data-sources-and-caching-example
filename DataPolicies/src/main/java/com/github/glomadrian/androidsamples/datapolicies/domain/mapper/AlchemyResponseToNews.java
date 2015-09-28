package com.github.glomadrian.androidsamples.datapolicies.domain.mapper;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Doc;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a AlchemyResponse return a List of news
 *
 * @author Adrián García Lomas
 */
public class AlchemyResponseToNews implements NewsMapper<AlchemyResponse> {

  private NewItemMapper<Doc> docNewItemMapper;

  public AlchemyResponseToNews(NewItemMapper<Doc> docNewItemMapper) {
    this.docNewItemMapper = docNewItemMapper;
  }

  @Override
  public List<NewItem> map(AlchemyResponse alchemyResponse) {

    List<NewItem> newItems = new ArrayList<>();

    for (Doc doc : alchemyResponse.getResult().getDocs()) {
      NewItem mappedNewItem = docNewItemMapper.map(doc);
      newItems.add(mappedNewItem);
    }
    return newItems;
  }
}
