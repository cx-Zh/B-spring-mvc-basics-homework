package com.thoughtworks.capacity.gtb.mvc;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorResult> handle(UserAlreadyExistException ex) {
    String code = "400";
    ErrorResult errorResult = new ErrorResult(code, ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
    String message = ex.getBindingResult().getFieldError().getDefaultMessage();
    String code = "400";
    ErrorResult errorResult = new ErrorResult(code, message);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

    String message = "";
    for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
      message = constraint.getMessage();
      break;
    }
    String code = "400";
    ErrorResult errorResult = new ErrorResult(code, message);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
  }
}
