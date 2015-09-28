package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;

/**
 * Define the communication between repository and datasources
 *
 * @author Adrián García Lomas
 */
public interface NewsRepository {

  List<NewItem> getTodayNews();
}
