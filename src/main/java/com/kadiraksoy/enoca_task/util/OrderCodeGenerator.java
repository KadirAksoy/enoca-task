package com.kadiraksoy.enoca_task.util;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class OrderCodeGenerator {

    public static String generateOrderCode() {
        long timestamp = System.currentTimeMillis();
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[8];
        random.nextBytes(randomBytes);
        String randomString = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        return "ORDER-" + timestamp + "-" + randomString;
    }
}
