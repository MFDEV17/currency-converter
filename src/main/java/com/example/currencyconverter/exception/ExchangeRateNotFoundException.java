package com.example.currencyconverter.exception;

import static java.lang.String.format;

public class ExchangeRateNotFoundException extends RuntimeException {
  public ExchangeRateNotFoundException(String baseCurrency, String targetCurrency) {
    super(format("Exchange pair (%s, %s) were not found", baseCurrency, targetCurrency));
  }
}
