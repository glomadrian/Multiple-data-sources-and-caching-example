package com.github.glomadrian.androidsamples.datapolicies.domain.mapper;

import com.github.glomadrian.androidsamples.datapolicies.AlchemyResponseBuilder;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Adrián García Lomas
 */
public class AlchemyResponseToNewsMapperTest {

  @Test
  public void alchemyResponseToNewsMapperTest() {
    AlchemyResponseToNews alchemyResponseToNews =
        new AlchemyResponseToNews(new AlchemyDocToNewItemMapper());

    List<NewItem> newItemList = alchemyResponseToNews.map(new AlchemyResponseBuilder().build());

    Assert.assertTrue(newItemList.size() > 0);
  }
}
