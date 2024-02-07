package com.nobile.ecommerce.Core.Infraestructure.Delivery.Response;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorDetails {
    private HttpStatus status;
    private List<String> errors;

    public ErrorDetails(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ErrorDetails(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }
}
