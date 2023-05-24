package com.example.currencyconverter.projection;

import java.math.BigDecimal;

public interface ExchangeRateProjection {
  Long getId();

  Currency getBaseCurrency();

  Currency getTargetCurrency();

  BigDecimal getRate();

  interface Currency {
    Long getId();

    String getCode();

    String getName();

    String getSign();
  }
}
