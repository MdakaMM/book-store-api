package com.bookstore.bookmanagement.util;

import java.util.Random;

public class IsbnGenerator {
    public static String generateIsbn13() {
        String prefix = "978";
        String base = prefix + String.format("%09d", new Random().nextInt(1_000_000_000));
        int checkDigit = calculateCheckDigit(base);
        return base + checkDigit;
    }

    private static int calculateCheckDigit(String base) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            int digit = Character.getNumericValue(base.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        return (10 - (sum % 10)) % 10;
    }
}
