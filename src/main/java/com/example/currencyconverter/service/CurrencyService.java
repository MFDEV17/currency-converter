package com.example.currencyconverter.service;

import com.example.currencyconverter.dto.CreateCurrencyRequest;
import com.example.currencyconverter.dto.CurrencyDtoRequest;
import com.example.currencyconverter.entity.CurrencyEntity;
import com.example.currencyconverter.mapper.CurrencyMapper;
import com.example.currencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {
  private final CurrencyRepository currencyRepository;
  private final CurrencyMapper currencyMapper;

  public ResponseEntity<?> createCurrency(CreateCurrencyRequest request) {
    CurrencyEntity saved
            = currencyMapper.fromCurrencyDtoRequestToEntity(request);

    return ResponseEntity.ok(currencyRepository.save(saved));
  }

  @Transactional(readOnly = true)
  public ResponseEntity<?> getAllCurrencies(int page, int pageSize) {
    PageRequest pageRequest = PageRequest.of(page, pageSize);

    List<CurrencyDtoRequest> dtoList =
            currencyMapper.fromEntityListToDtoList(
                    currencyRepository.findAll(pageRequest).getContent()
            );

    return ResponseEntity.ok().body(dtoList);
  }

  @Transactional(readOnly = true)
  public ResponseEntity<?> findCurrency(String code) {
    if (code == null) {
      return ResponseEntity.status(400).build();
    }

    Optional<CurrencyEntity> currency
            = currencyRepository.findBySignIgnoreCase(code);

    if (currency.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok()
            .body(currencyMapper.fromEntityToDto(currency.get()));
  }
}
