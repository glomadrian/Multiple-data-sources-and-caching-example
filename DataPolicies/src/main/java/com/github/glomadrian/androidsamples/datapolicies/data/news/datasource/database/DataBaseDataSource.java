package com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public interface DataBaseDataSource {

  List<NewItem> getToadyNews();

  void saveNews(List<NewItem> news);

  void clear();
}
