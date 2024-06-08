package com.kadiraksoy.enoca_task.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Bu id ile ürün bulunamadı: " + id);
    }
}
