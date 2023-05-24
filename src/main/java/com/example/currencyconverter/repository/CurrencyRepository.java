package com.example.currencyconverter.repository;

import com.example.currencyconverter.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
  Optional<CurrencyEntity> findBySignIgnoreCase(String code);
  Optional<CurrencyEntity> findByCodeIgnoreCase(String code);
}
