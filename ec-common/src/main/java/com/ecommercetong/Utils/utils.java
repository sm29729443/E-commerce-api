package com.ecommercetong.Utils;

import java.util.Random;

public class utils {

    public static String generateRandomCode(int length) {
        if(length < 1) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        StringBuilder code = new StringBuilder();
        for(int d: new Random().ints(length, 0, 10).toArray()) {
            code.append(d);
        }
        return code.toString();
    }
}
