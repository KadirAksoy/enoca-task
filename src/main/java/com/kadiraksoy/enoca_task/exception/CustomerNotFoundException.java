package com.kadiraksoy.enoca_task.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Bu id ile customer bulunamadı: " + id);
    }
}
