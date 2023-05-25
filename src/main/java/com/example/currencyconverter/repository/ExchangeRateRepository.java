package com.example.currencyconverter.repository;

import com.example.currencyconverter.entity.CurrencyEntity;
import com.example.currencyconverter.entity.ExchangeRateEntity;
import com.example.currencyconverter.projection.ExchangeRateProjection;
import com.example.currencyconverter.projection.ExchangeRateProjectionWithAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

  @Query("select " +
          "e.base as baseCurrency, " +
          "e.target as targetCurrency, " +
          "e.rate as rate, " +
          "e.id as id " +
          "from exchange_rate e " +
          "where e.base.code = upper(?1) and e.target.code = upper(?2)")
  ExchangeRateProjection findByCodePair_ExchangeRateProjection(String baseCode, String targetCode);

  @Query("select " +
          "e.rate * :amount as convertedAmount," +
          "e.base as baseCurrency, " +
          "e.target as targetCurrency, " +
          "e.rate as rate, " +
          "e.id as id " +
          "from exchange_rate e " +
          "where e.base.code = upper(:from) and e.target.code = upper(:to)")
  ExchangeRateProjectionWithAmount findByCodePair_ExchangeProjectionWithAmount(
          @Param("from") String from,
          @Param("to") String to,
          @Param("amount") int amount
  );

  Optional<ExchangeRateEntity> findByBaseAndTarget(CurrencyEntity base, CurrencyEntity target);
}
