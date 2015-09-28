package com.github.glomadrian.androidsamples.datapolicies.domain.mapper;

import com.github.glomadrian.androidsamples.datapolicies.DocBuilder;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Doc;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Adrián García Lomas
 */
public class AlchemyDocToNewMapperTest {

  @Test
  public void alchemyMapperTest() {

    Doc doc = new DocBuilder().build();

    AlchemyDocToNewItemMapper alchemyDocToNewMapper = new AlchemyDocToNewItemMapper();
    NewItem newItemObject = alchemyDocToNewMapper.map(doc);

    Assert.assertEquals("Test url", newItemObject.getUrl());
    Assert.assertEquals("Test title", newItemObject.getTitle());
  }
}
