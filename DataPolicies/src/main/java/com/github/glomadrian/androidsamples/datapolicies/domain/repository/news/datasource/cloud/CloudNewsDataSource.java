package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud;

import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;

/**
 * Mark interface to define and inject all CloudNewsDataSources
 *
 * @author Adrián García Lomas
 */
public interface CloudNewsDataSource {

  List<NewItem> getTodayNews();
}
