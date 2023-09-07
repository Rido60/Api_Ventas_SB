package com.posgrado.ecommerce.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.posgrado.ecommerce.exception.response.ErrorResponse;
import com.posgrado.ecommerce.exception.response.FieldErrorModel;
import com.posgrado.ecommerce.exception.response.StatusResponse;
import com.posgrado.ecommerce.exception.response.ValidationErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception ex){
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse error = ErrorResponse.builder()
        .code(status.value())
        .error(status.name())
        .message(ex.getMessage())
        .build();
    return ResponseEntity
        .status(status)
        .body(error);
  }
  @ExceptionHandler(EmailAlreadyTaken.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyTaken(Exception ex){
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse error = ErrorResponse.builder()
        .code(status.value())
        .error(status.name())
        .message(ex.getMessage())
        .build();
    return ResponseEntity
        .status(status)
        .body(error);
  }

  @ExceptionHandler(RoleAlreadyTaken.class)
  public ResponseEntity<StatusResponse> handleRoleAlreadyTaken(Exception ex){
    HttpStatus status = HttpStatus.CONFLICT;
    StatusResponse sta = StatusResponse.builder()
        .status(status.value())
        .error("Role Name Already Exists")
        .message(ex.getMessage())
        .build();
    return ResponseEntity
        .status(status)
        .body(sta);
  }


  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex){
    HttpStatus status = HttpStatus.UNAUTHORIZED;
    ErrorResponse error = ErrorResponse.builder()
        .code(status.value())
        .error(status.name())
        .message(ex.getMessage())
        .build();
    return ResponseEntity
        .status(status)
        .body(error);
  }

  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<ErrorResponse> handleJWTVerificationException(Exception ex){
    HttpStatus status = HttpStatus.FORBIDDEN;
    ErrorResponse error = ErrorResponse.builder()
        .code(status.value())
        .error(status.name())
        .message(ex.getMessage())
        .build();
    return ResponseEntity
        .status(status)
        .body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

    HttpStatus status = HttpStatus.BAD_REQUEST;
    List<FieldErrorModel> errors = ex.getBindingResult().getAllErrors().stream().map(fieldError ->{
      FieldErrorModel fieldErrorModel = new FieldErrorModel();
      fieldErrorModel.setField(((FieldError)fieldError).getField());
      fieldErrorModel.setMesssage(fieldError.getDefaultMessage());
      fieldErrorModel.setCode(fieldError.getCode());
      return fieldErrorModel;
    }).toList();

    ValidationErrorResponse response =new ValidationErrorResponse();
    response.setCode(status.value());
    response.setError(status.name());
    response.setErrors(errors);

    return ResponseEntity
        .status(status)
        .body(response);
  }




}
