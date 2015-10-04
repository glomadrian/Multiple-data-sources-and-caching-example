package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasources.database;

import android.test.AndroidTestCase;
import com.github.glomadrian.androidsamples.datapolicies.NewsBuilder;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.DataBaseDataSource;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.NewFileDataSource;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class NewItemFileDataSourceTest extends AndroidTestCase {

  public void testFileDataSource() throws Exception {

    DataBaseDataSource dataBaseDataSource = new NewFileDataSource(getContext());
    List<NewItem> news = new NewsBuilder().withAmount(5).build();
    dataBaseDataSource.saveNews(news);

    List<NewItem> newsFromStorage = dataBaseDataSource.getToadyNews();
    assertEquals(5, newsFromStorage.size());
  }
}
