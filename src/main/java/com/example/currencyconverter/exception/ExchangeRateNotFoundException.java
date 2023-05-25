package com.example.currencyconverter.exception;

public class ExchangeRateNotFoundException extends RuntimeException {
  public ExchangeRateNotFoundException(String baseCurrency, String targetCurrency) {
    super(String.format("Exchange pair (%s, %s) were not found", baseCurrency, targetCurrency));
  }
}
