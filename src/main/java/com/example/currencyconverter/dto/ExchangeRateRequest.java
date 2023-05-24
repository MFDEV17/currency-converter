package com.example.currencyconverter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateRequest {
  private Long id;
  private CurrencyDtoRequest baseCurrency;
  private CurrencyDtoRequest targetCurrency;
  private BigDecimal rate;
}
