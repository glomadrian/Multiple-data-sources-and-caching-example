package com.github.glomadrian.androidsamples.datapolicies;

import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Doc;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Enriched;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Source;
import com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model.Url;

/**
 * @author Adrián García Lomas
 */
public class DocBuilder {

  private String title = "Test title";
  private String url = "Test url";

  public DocBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public DocBuilder withUrl(String url) {
    this.url = url;
    return this;
  }

  public Doc build() {
    Url newUrl = new Url();
    newUrl.setUrl(url);
    newUrl.setTitle(title);

    Enriched enriched = new Enriched();
    enriched.setUrl(newUrl);

    Source source = new Source();
    source.setEnriched(enriched);

    Doc doc = new Doc();
    doc.setSource(source);

    doc.setSource(source);
    return doc;
  }
}
