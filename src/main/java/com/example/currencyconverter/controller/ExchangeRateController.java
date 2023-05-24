package com.example.currencyconverter.controller;

import com.example.currencyconverter.dto.CreateExchangeRequest;
import com.example.currencyconverter.dto.UpdateExchangeRateRequest;
import com.example.currencyconverter.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchangeRates")
public class ExchangeRateController {
  private final ExchangeRateService service;

  @GetMapping
  public ResponseEntity<?> getAllExchangeRates() {
    return service.getAllExchangeRates();
  }


  @GetMapping("/exchangeRate/{pair}")
  public ResponseEntity<?> findExchangeRateByPair(@PathVariable String pair) {
    return service.findExchangeRate(pair);
  }

  @PostMapping
  public ResponseEntity<?> createExchangeRate(@RequestBody CreateExchangeRequest request) {
    return service.createExchangeRate(request);
  }

  @PatchMapping("/exchangeRate/{pair}")
  public ResponseEntity<?> updateExchangeRate(@PathVariable String pair, @RequestBody UpdateExchangeRateRequest request) {
    return service.updateExchangeRate(pair, request);
  }

  @GetMapping("/exchange")
  public ResponseEntity<?> makeExchange(
          @RequestParam String from,
          @RequestParam String to,
          @RequestParam int amount
  ) {
    return service.makeExchange(from, to, amount);
  }
}
