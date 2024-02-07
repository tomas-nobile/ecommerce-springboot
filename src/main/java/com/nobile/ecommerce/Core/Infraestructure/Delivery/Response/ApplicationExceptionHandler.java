package com.nobile.ecommerce.Core.Infraestructure.Delivery.Response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nobile.ecommerce.Core.Infraestructure.Delivery.Response.CustomException.EntityNotFoundException;
import com.stripe.exception.StripeException;


@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value 
    = {  EntityNotFoundException.class })
  protected ResponseEntity<Object> handleEntityNotFoundException(
    Exception ex, WebRequest request) {
      String bodyOfResponse = ex.getMessage() ;
      return handleExceptionInternal(ex, bodyOfResponse, 
        new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = {  StripeException.class })
protected ResponseEntity<Object> handleStripeException(
  Exception ex, WebRequest request) {
    String bodyOfResponse = "ERROR on PAYMENT SERVICE: " + ex.getMessage() ;
    return handleExceptionInternal(ex, bodyOfResponse, 
      new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
}

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return ExceptionUtil.errorStatusMessage(errorList, HttpStatus.BAD_REQUEST);
    }



}





