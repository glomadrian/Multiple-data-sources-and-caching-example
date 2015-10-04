package com.github.glomadrian.androidsamples.datapolicies.data.news.policity;

import com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.NewsRepository;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.NewsDataSources;

/**
 * @author Adrián García Lomas
 */
public interface NewsPolicy extends NewsRepository {

  NewsDataSources getLastDataSourceUsed();
}
