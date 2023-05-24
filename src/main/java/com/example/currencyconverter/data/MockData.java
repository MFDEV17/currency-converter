package com.example.currencyconverter.data;

import com.example.currencyconverter.entity.CurrencyEntity;
import com.example.currencyconverter.mapper.CurrencyMapper;
import com.example.currencyconverter.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Currency;
import java.util.List;
import java.util.Locale;


@Configuration
@RequiredArgsConstructor
public class MockData {
  private final CurrencyMapper mapper;

  @Bean
  CommandLineRunner runner(CurrencyRepository currencyRepository) {
    Locale.setDefault(Locale.US);

    return args -> {
      List<CurrencyEntity> currencies =
              Currency
                      .getAvailableCurrencies()
                      .stream()
                      .filter(c -> !c.getDisplayName().equals("Unknown Currency"))
                      .map(mapper::fromUtilCurrencyToEntity)
                      .toList();

      currencyRepository.saveAll(currencies);
    };
  }
}
