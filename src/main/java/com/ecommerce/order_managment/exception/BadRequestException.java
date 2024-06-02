package com.ecommerce.order_managment.exception;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Error, Exception";

    public BadRequestException(String detail) {
        super(String.format("%s - %s", DESCRIPTION, detail));
    }
}
