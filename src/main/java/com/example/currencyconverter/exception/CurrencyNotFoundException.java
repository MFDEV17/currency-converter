package com.example.currencyconverter.exception;

public class CurrencyNotFoundException extends RuntimeException {
  public CurrencyNotFoundException(String currencyName) {
    super(String.format("Currency '%s' were not found", currencyName));
  }
}