package com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model;

/**
 * @author Adrián García Lomas
 */
public class Doc {

  private String id;
  private long timestamp;
  private Source source;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public Source getSource() {
    return source;
  }

  public void setSource(Source source) {
    this.source = source;
  }
}
