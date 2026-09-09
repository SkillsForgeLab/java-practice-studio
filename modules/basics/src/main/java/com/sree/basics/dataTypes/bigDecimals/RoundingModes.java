package com.sree.basics.dataTypes.bigDecimals;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class RoundingModes {
    private static final BigDecimal ONE = BigDecimal.ONE;
    private static final BigDecimal THREE = BigDecimal.valueOf(3);
    private static final int EXAMPLE_SCALE = 4;
    private static final String EXAMPLE_FORMAT = "%20.4f\n";

    private RoundingModes() {
    }

    public static void main(String[] args) {
        floorExamples();
        ceilingExamples();
        downExamples();
        upExamples();
    }

    private static void floorExamples() {
        // -infinity <---------|---|---|---|---|---|---|---|---|---------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //                        <------------------------ Moving towards negative infinity
        //  1.3 ->  1
        // -1.3 -> -2

        printExamples(System.out, "Moving towards negative infinity : FLOOR", RoundingMode.FLOOR);
    }

    private static void ceilingExamples() {
        // -infinity <---------|---|---|---|---|---|---|---|---|---------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //                Moving towards positive infinity ------------------------>
        //  1.3 ->  2
        // -1.3 -> -1

        printExamples(System.out, "Moving towards positive infinity : CEILING", RoundingMode.CEILING);
    }

    private static void downExamples() {
        //               : DOWN
        // -infinity <---------|---|---|---|---|---|---|---|---|--------- + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //         ----------------> Moving towards zero <----------------
        //  1.3 ->  1
        // -1.3 -> -1

        printExamples(System.out, "Moving towards zero : DOWN", RoundingMode.DOWN);
    }

    private static void upExamples() {
        //             : UP
        // -infinity <---------|---|---|---|---|---|---|---|---|---------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //         <---------------- Moving away from zero ---------------->
        //  1.3 ->  2
        // -1.3 -> -2

        printExamples(System.out, "Moving away from zero : UP", RoundingMode.UP);
    }

    public static void printExamples(PrintStream output, String description, RoundingMode rounding) {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(rounding, "rounding");

        output.println("\n\n-----------------------------\n");
        output.println(description + "\n");
        BigDecimals.print(
                output, BigDecimals.divide(ONE, THREE, EXAMPLE_SCALE, rounding), EXAMPLE_FORMAT);
        BigDecimals.print(
                output, BigDecimals.divide(ONE.negate(), THREE, EXAMPLE_SCALE, rounding), EXAMPLE_FORMAT);
    }
}
