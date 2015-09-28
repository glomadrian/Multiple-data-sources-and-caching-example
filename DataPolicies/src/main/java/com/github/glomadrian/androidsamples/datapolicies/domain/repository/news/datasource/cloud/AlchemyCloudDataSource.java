package com.github.glomadrian.androidsamples.datapolicies.domain.repository.news.datasource.cloud;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.AlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import com.github.glomadrian.androidsamples.datapolicies.domain.mapper.NewsMapper;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import java.util.List;

/**
 * Implementation of a cloudDataSource, in this case a Alchemy api data source
 *
 * @author Adrián García Lomas
 */
public class AlchemyCloudDataSource implements CloudNewsDataSource {

  private static final String YESTERDAY = "now-1d";
  private static final String TODAY = "now";
  private static final String title = "Android";
  private static final String genere = "technology and computing";
  private static final int count = 20;

  private NewsMapper<AlchemyResponse> alchemyResponseNewsMapper;
  private AlchemyApi alchemyApi;

  public AlchemyCloudDataSource(NewsMapper<AlchemyResponse> alchemyResponseNewsMapper,
      AlchemyApi alchemyApi) {
    this.alchemyResponseNewsMapper = alchemyResponseNewsMapper;
    this.alchemyApi = alchemyApi;
  }

  @Override
  public List<NewItem> getTodayNews() {
    AlchemyResponse alchemyResponse = alchemyApi.getNews(YESTERDAY, TODAY, title, genere, count);
    return alchemyResponseNewsMapper.map(alchemyResponse);
  }
}
