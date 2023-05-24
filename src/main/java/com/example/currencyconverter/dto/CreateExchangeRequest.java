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
public class CreateExchangeRequest {
  private String baseCurrencyCode;
  private String targetCurrencyCode;
  private BigDecimal rate;
}
