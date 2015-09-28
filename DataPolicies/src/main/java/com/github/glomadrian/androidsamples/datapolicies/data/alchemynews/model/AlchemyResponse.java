package com.github.glomadrian.androidsamples.datapolicies.data.alchemynews.model;

/**
 * @author Adrián García Lomas
 */
public class AlchemyResponse {

  private String status;
  private String usage;
  private int totalTransactions;
  private Result result;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }

  public int getTotalTransactions() {
    return totalTransactions;
  }

  public void setTotalTransactions(int totalTransactions) {
    this.totalTransactions = totalTransactions;
  }

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }
}
