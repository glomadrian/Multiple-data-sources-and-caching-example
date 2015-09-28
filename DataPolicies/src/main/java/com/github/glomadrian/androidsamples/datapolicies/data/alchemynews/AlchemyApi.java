package com.github.glomadrian.androidsamples.datapolicies.data.alchemynews;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;

/**
 * @author Adrián García Lomas
 */
public interface AlchemyApi {

  AlchemyResponse getNews(String start, String end, String title, String taxonomy, int count);
}
