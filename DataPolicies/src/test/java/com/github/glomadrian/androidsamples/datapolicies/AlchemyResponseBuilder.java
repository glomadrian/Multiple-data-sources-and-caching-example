package com.github.glomadrian.androidsamples.datapolicies;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.AlchemyResponse;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Doc;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Result;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián García Lomas
 */
public class AlchemyResponseBuilder {

  private DocBuilder docBuilder = new DocBuilder();
  private int amount = 1;

  public AlchemyResponseBuilder withAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public AlchemyResponse build() {
    AlchemyResponse alchemyResponse = new AlchemyResponse();
    Result result = new Result();

    List<Doc> docs = new ArrayList<>();

    for (int i = 0; i < amount; i++) {
      docs.add(docBuilder.build());
    }

    result.setDocs(docs);
    alchemyResponse.setResult(result);
    return alchemyResponse;
  }
}
