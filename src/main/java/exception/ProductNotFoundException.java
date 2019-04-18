package com.vironit.vinylRecordsStore.exception;

/**
 * Запрошенный товар не найден.
 */
public class ProductNotFoundException extends Exception {
    
    public ProductNotFoundException() {
        super("");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
