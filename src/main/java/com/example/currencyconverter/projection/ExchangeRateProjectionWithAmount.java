package com.example.currencyconverter.projection;

import java.math.BigDecimal;

public interface ExchangeRateProjectionWithAmount extends ExchangeRateProjection {
  BigDecimal getConvertedAmount();
}
