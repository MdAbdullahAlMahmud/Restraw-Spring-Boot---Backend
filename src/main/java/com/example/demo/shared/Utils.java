package com.example.demo.shared;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {


    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz";

    public String generateUserId(int length){
        return  generateString(length);
    }
    public String generateFoodId(int length){
        return  generateString(length);
    }

    private String generateString(int length) {
        StringBuilder userId = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            userId.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return  userId.toString();
    }




}
