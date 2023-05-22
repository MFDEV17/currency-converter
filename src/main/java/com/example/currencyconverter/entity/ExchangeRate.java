package com.example.currencyconverter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "base_id, target_id", unique = true)
})
public class ExchangeRate extends BaseEntity {
  @ManyToOne
  private Currency base;

  @OneToOne
  private Currency target;

  @Column(columnDefinition = "NUMERIC(6)", nullable = false)
  private BigDecimal rate;
}
