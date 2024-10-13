package com.thullyoo.owner_catalog.exceptions;

public class CategoryNotExistsException extends RuntimeException{

    public CategoryNotExistsException(String message) {
        super(message);
    }
}
