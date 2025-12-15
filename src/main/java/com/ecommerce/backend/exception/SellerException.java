package com.ecommerce.backend.exception;

public class SellerException extends RuntimeException{
    public SellerException() {
        super();
    }

    public SellerException(String message) {
        super(message);
    }
}
