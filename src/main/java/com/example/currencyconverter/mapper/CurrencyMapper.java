package com.example.currencyconverter.mapper;

import com.example.currencyconverter.entity.CurrencyEntity;
import com.example.currencyconverter.dto.CreateCurrencyRequest;
import com.example.currencyconverter.dto.CurrencyDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public abstract class CurrencyMapper {
  public abstract CurrencyDtoRequest fromEntityToDto(CurrencyEntity currency);
  public abstract List<CurrencyDtoRequest> fromEntityListToDtoList(List<CurrencyEntity> currency);

  @Mapping(target = "id", ignore = true)
  public abstract CurrencyEntity fromCurrencyDtoRequestToEntity(CreateCurrencyRequest request);

  @Mapping(target = "code", source = "currencyCode")
  @Mapping(target = "name", source = "displayName")
  @Mapping(target = "sign", source = "symbol")
  @Mapping(target = "id", ignore = true)
  public abstract CurrencyEntity fromUtilCurrencyToEntity(java.util.Currency currency);
}
