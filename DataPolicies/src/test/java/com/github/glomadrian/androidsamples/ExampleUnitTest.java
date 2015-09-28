package com.github.glomadrian.androidsamples;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.RetrofitAlchemyApi;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
  @Test
  public void addition_isCorrect() throws Exception {
    assertEquals(4, 2 + 2);
  }

  //TODO Delete
  @Test
  public void testRetrofit() {
    String baseUrl = "https://access.alchemyapi.com";
    String apiKey = "fece4ff013c0d873958a218f9491e4f127b8848e";
    String rreturn = "enriched.url.title,enriched.url.url";
    String type = "positive";
    String returnType = "json";

    RetrofitAlchemyApi retrofitAlchemyApi =
        new RetrofitAlchemyApi(baseUrl, apiKey, rreturn, type, returnType);

    AlchemyResponse response =
        retrofitAlchemyApi.getNews("now-1d", "now", "Android", "technology and computing", 10);

    Assert.assertNotNull(response);
  }
}