package com.sree.common;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DemoSupportTest {
    @Test
    void printsTheModuleName() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;

        try {
            System.setOut(new PrintStream(output));
            DemoSupport.printHeader("Test module");
        } finally {
            System.setOut(originalOutput);
        }

        assertTrue(output.toString().contains("Test module"));
    }
}
