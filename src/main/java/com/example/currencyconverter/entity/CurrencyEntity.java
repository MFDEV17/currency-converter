package com.example.currencyconverter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "currency")
@DynamicUpdate
public class CurrencyEntity extends BaseEntity {

  @Column(unique = true)
  @Size(
          min = 3,
          max = 3,
          message = "Размер кода должен состоять из 3 символов"
  )
  @NotNull(message = "Код валюты не должен быть пустым")
  private String code;

  @NotNull(message = "Имя валюты не должно быть пустым")
  private String name;

  @NotNull(message = "Символ валюты не должен быть пустым")
  private String sign;
}
