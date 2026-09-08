package com.sree.common;

/** Small utilities shared by the independently runnable practice modules. */
public final class DemoSupport {
    private DemoSupport() {
    }

    public static void printHeader(String moduleName) {
        System.out.println("\n******************************\n");

        System.out.println("Hello and welcome!");

        System.out.println("\n=== " + moduleName + " ===\n");

        System.out.println("Java version : " + System.getProperty("java.version"));
        System.out.println("Java vendor  : " + System.getProperty("java.vendor"));
        System.out.println("Java home    : " + System.getProperty("java.home"));
        System.out.println("\n******************************\n\n");


    }
}
