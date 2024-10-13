package com.thullyoo.owner_catalog.exceptions;

public class ProductNotExistsException extends RuntimeException{
    public ProductNotExistsException(String message) {
        super(message);
    }
}
