package com.sree.basics;

import com.sree.common.DemoSupport;

public final class BasicsMain {
    private BasicsMain() {
    }

    public static void main(String[] args) {
        DemoSupport.printHeader("Language basics");

        int total = 0;
        for (int value = 1; value <= 5; value++) {
            total += value;
        }

        System.out.println("Sum from 1 through 5: " + total);
    }
}
