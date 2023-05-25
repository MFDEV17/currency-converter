package com.example.currencyconverter.service;

import com.example.currencyconverter.dto.CreateExchangeRequest;
import com.example.currencyconverter.dto.ExchangeRateRequest;
import com.example.currencyconverter.dto.UpdateExchangeRateRequest;
import com.example.currencyconverter.entity.CurrencyEntity;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import com.example.currencyconverter.exception.CurrencyNotFoundException;
import com.example.currencyconverter.exception.ExchangeRateNotFoundException;
import com.example.currencyconverter.mapper.ExchangeRateMapper;
import com.example.currencyconverter.projection.ExchangeRateProjection;
import com.example.currencyconverter.projection.ExchangeRateProjectionWithAmount;
import com.example.currencyconverter.repository.CurrencyRepository;
import com.example.currencyconverter.repository.ExchangeRateRepository;
import com.example.currencyconverter.util.ServiceUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {
  private final ExchangeRateRepository exchangeRateRepository;
  private final ExchangeRateMapper exchangeRateMapper;
  private final CurrencyRepository currencyRepository;

  @Transactional(readOnly = true)
  @EntityGraph(attributePaths = {"base", "target"})
  public ResponseEntity<?> getAllExchangeRates() {
    List<ExchangeRateRequest> exchangeRateDtoList =
            exchangeRateMapper.fromEntityListToDto(exchangeRateRepository.findAll());
    return ResponseEntity.ok().body(exchangeRateDtoList);
  }

  @Transactional(readOnly = true)
  public ResponseEntity<?> findExchangeRate(String codePair) {
    if (codePair.length() != 6) {
      return ResponseEntity.unprocessableEntity().build();
    }

    String[] pair = ServiceUtil.halfCodePair(codePair);

    ExchangeRateProjection exchangeRate
            = exchangeRateRepository.findByCodePair_ExchangeRateProjection(pair[0], pair[1]);

    return ResponseEntity.ok(exchangeRate);
  }

  public ResponseEntity<?> createExchangeRate(CreateExchangeRequest request) {
    ExchangeRateEntity saved = exchangeRateMapper.fromCreateRequestToEntity(request);
    exchangeRateRepository.save(saved);

    return ResponseEntity.ok().body(saved);
  }

  @Transactional
  public ResponseEntity<?> updateExchangeRate(
          String codePair,
          UpdateExchangeRateRequest request
  ) {
    if (codePair.length() != 6) {
      return ResponseEntity.unprocessableEntity().build();
    }

    String[] pair = ServiceUtil.halfCodePair(codePair);
    String base = pair[0];
    String target = pair[1];

    CurrencyEntity baseCurrency = currencyRepository
            .findByCodeIgnoreCase(base)
            .orElseThrow(() -> new CurrencyNotFoundException(base));

    CurrencyEntity targetCurrency = currencyRepository
            .findByCodeIgnoreCase(target)
            .orElseThrow(() -> new CurrencyNotFoundException(target));

    ExchangeRateEntity toUpdate =
            exchangeRateRepository
                    .findByBaseAndTarget(baseCurrency, targetCurrency)
                    .orElseThrow(() -> new ExchangeRateNotFoundException(base, target));
    toUpdate.setRate(request.getNewRate());

    exchangeRateRepository.save(toUpdate);

    return ResponseEntity.ok(toUpdate);
  }

  @Transactional(isolation = SERIALIZABLE, readOnly = true)
  public ResponseEntity<?> makeExchange(String from, String to, int amount) {
    ExchangeRateProjectionWithAmount result = exchangeRateRepository
            .findByCodePair_ExchangeProjectionWithAmount(from, to, amount);

    return ResponseEntity.ok(result);
  }
}
