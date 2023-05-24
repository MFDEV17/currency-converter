package com.example.currencyconverter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

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

  @OneToOne
  private Currency target;

  @Column(columnDefinition = "NUMERIC(6)", nullable = false)
  private BigDecimal rate;
}
