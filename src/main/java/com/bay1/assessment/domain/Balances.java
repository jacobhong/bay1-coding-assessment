package com.bay1.assessment.domain;

import java.math.BigDecimal;
import java.util.HashMap;

public class Balances {
  private static HashMap<String, BigDecimal> balances = new HashMap<>();

  public static HashMap<String, BigDecimal> getBalances() {
    return balances;
  }

  public void setBalances(HashMap<String, BigDecimal> balances) {
    this.balances = balances;
  }

  public Balances(HashMap<String, BigDecimal> balances) {
    this.balances = balances;
  }

  public Balances(){}
}
