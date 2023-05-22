package com.example.currencyconverter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Currency extends BaseEntity {

  @Column(unique = true, nullable = false)
  @Size(min = 3, max = 3)
  private String code;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String sign;
}
