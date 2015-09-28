package com.github.glomadrian.androidsamples.datapolicies.data.alchemynews;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Adrián García Lomas
 */
public interface AlchemyService {

  @GET("calls/data/GetNews")
  Call<AlchemyResponse> getAlchemyNews(@Query("apikey") String apiKey,
      @Query("return") String rreturn, @Query("start") String start, @Query("end") String end,
      @Query("q.enriched.url.cleanedTitle") String title,
      @Query("q.enriched.url.enrichedTitle.docSentiment.type") String type,
      @Query("q.enriched.url.enrichedTitle.taxonomy.taxonomy_.label") String label,
      @Query("count") int count, @Query("outputMode") String outputMode);
}
