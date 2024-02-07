package com.nobile.ecommerce.Core.Infraestructure.Delivery.Response;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ExceptionUtil <T>{
        private final Class<T> genericClass;

    public static ResponseEntity<Object> errorStatusMessage(List<String> errors, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails(
                status,errors);
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    public static ResponseEntity<Object> errorStatusMessage(String error, HttpStatus status) {
        ErrorDetails errorDetails = new ErrorDetails(
                status,error);
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    public T verifyPayloadStruct(HttpServletRequest request){
        try {
            T obj = new ObjectMapper().readValue(request.getInputStream(), genericClass);
            return obj; 
        } catch (Exception e) {
            throw new RuntimeException("Bad Request. Please check request's body out");
        }
    }
}
