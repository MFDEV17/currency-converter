package com.example.currencyconverter.exceptionhandler;

import com.example.currencyconverter.exception.CurrencyNotFoundException;
import com.example.currencyconverter.util.ServiceUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@RestControllerAdvice
public class DatabaseExceptionHandler {

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  protected ResponseEntity<?> onUniqueViolation(DataIntegrityViolationException ex) {
    return ResponseEntity.status(409)
            .body(getUniquePropertiesFromDataIntegrityException(ex));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> onConstraintViolation(ConstraintViolationException ex) {
    return ResponseEntity.badRequest()
            .body(ex.getConstraintViolations()
                    .stream().map(ConstraintViolation::getMessage)
                    .findFirst().orElse("")
            );
  }

  @ExceptionHandler(CurrencyNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<?> onCurrencyNotFound(CurrencyNotFoundException ex) {
    return ResponseEntity.status(400).body(ex.getMessage());
  }

  private Map<String, Map<String, String>> getUniquePropertiesFromDataIntegrityException(DataIntegrityViolationException ex) {
    String message = requireNonNull(ex.getRootCause()).getMessage();

    String key = ServiceUtil.substringBetween(message, "Key (", ")");
    String value = ServiceUtil.substringBetween(message, "=(", ")");

    Map<String, Map<String, String>> errMap = new HashMap<>();

    if (!isNull(key) && !isNull(value)) {
      errMap.put("UNIQUENESS_VIOLATION", Map.of(key, value));
    }

    return errMap;
  }
}
