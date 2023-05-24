package com.example.currencyconverter.controller;

import com.example.currencyconverter.dto.CreateCurrencyRequest;
import com.example.currencyconverter.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/currencies")
public class CurrencyController {
  private final CurrencyService service;

  @PostMapping
  public ResponseEntity<?> createCurrency(@RequestBody CreateCurrencyRequest request) {
    return service.createCurrency(request);
  }

  @GetMapping
  public ResponseEntity<?> getAllCurrencies(@RequestParam int page, @RequestParam int pageSize) {
    return service.getAllCurrencies(page, pageSize);
  }

  @GetMapping("/{code}")
  public ResponseEntity<?> findCurrencyByCode(@PathVariable String code) {
    return service.findCurrency(code);
  }
}
