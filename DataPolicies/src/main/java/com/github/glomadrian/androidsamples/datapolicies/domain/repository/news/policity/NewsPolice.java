package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepository;
import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.NewsDataSources;

/**
 * @author Adrián García Lomas
 */
public interface NewsPolice extends NewsRepository {

  NewsDataSources getLastDataSourceUsed();
}
