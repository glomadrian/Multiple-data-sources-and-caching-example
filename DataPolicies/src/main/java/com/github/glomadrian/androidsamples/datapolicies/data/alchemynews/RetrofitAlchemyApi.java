package com.github.glomadrian.androidsamples.datapolicies.data.alchemynews;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.exception.GetNewsException;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import java.io.IOException;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Adrián García Lomas
 */
public class RetrofitAlchemyApi implements AlchemyApi {

  private AlchemyService alchemyService;
  private String baseUrl;
  private String apiKey;
  private String rreturn;
  private String type;
  String outputMode;

  public RetrofitAlchemyApi(String baseUrl, String apiKey, String rreturn, String type,
      String outputMode) {
    this.baseUrl = baseUrl;
    this.apiKey = apiKey;
    this.rreturn = rreturn;
    this.type = type;
    this.outputMode = outputMode;
    init();
  }

  public void init() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    alchemyService = retrofit.create(AlchemyService.class);
  }

  @Override
  public AlchemyResponse getNews(String start, String end, String title, String taxonomy,
      int count) {
    Call<AlchemyResponse> call =
        alchemyService.getAlchemyNews(apiKey, rreturn, start, end, title, type, taxonomy, count,
            outputMode);
    try {
      Response<AlchemyResponse> response = call.execute();
      return response.body();
    } catch (IOException e) {
      GetNewsException getNewsException = new GetNewsException();
      getNewsException.setStackTrace(e.getStackTrace());
      throw getNewsException;
    }
  }
}
