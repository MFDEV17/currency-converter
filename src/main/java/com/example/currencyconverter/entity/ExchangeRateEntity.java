package com.example.currencyconverter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "exchange_rate")
@DynamicUpdate
@Table(indexes = {
        @Index(columnList = "base_id, target_id", unique = true)
})
public class ExchangeRateEntity extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CurrencyEntity base;

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private CurrencyEntity target;

  @Column(nullable = false, columnDefinition = "numeric(38,6)")
  @Positive(message = "Курс не может быть отрицательным")
  private BigDecimal rate;
}
