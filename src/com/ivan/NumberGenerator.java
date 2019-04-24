package com.ivan;

public class NumberGenerator {

    static String generateNumber() {
        int result = 0;
        int rate = 1;
        for(int i = 0; i < 3; i++) {
            result += ((int)(Math.random() * 9)) * rate;
            rate *= 10;
        }
        result += 1 + ((int)(Math.random() * 9)) * rate;

        return Integer.toString(result);
    }
}
