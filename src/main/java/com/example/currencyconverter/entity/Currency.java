package com.example.currencyconverter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
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
@Entity
@Table(indexes = {
        @Index(columnList = "code, name", unique = true)
})
@DynamicUpdate
public class Currency extends BaseEntity {

  @Column(nullable = false)
  @Size(min = 3, max = 3)
  private String code;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String sign;
}
