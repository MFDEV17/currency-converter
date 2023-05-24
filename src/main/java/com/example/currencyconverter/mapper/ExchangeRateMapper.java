package com.example.currencyconverter.mapper;

import com.example.currencyconverter.dto.CreateExchangeRequest;
import com.example.currencyconverter.dto.ExchangeRateRequest;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import com.example.currencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(uses = {CurrencyMapper.class})
@RequiredArgsConstructor
public abstract class ExchangeRateMapper {
  @Autowired
  protected CurrencyRepository currencyRepository;

  @Mapping(target = "baseCurrency", source = "base")
  @Mapping(target = "targetCurrency", source = "target")
  public abstract ExchangeRateRequest fromEntityToDto(ExchangeRateEntity entity);

  public abstract List<ExchangeRateRequest> fromEntityListToDto(List<ExchangeRateEntity> entityList);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "base", expression = "java(currencyRepository.findByCodeIgnoreCase(request.getBaseCurrencyCode()).orElseThrow(() -> new com.example.currencyconverter.exception.CurrencyNotFoundException(request.getBaseCurrencyCode())))")
  @Mapping(target = "target", expression = "java(currencyRepository.findByCodeIgnoreCase(request.getTargetCurrencyCode()).orElseThrow(() -> new com.example.currencyconverter.exception.CurrencyNotFoundException(request.getTargetCurrencyCode())))")
  public abstract ExchangeRateEntity fromCreateRequestToEntity(CreateExchangeRequest request);
}
