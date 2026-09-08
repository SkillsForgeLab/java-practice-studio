package com.sree.basics.bigDecimals;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalRoundingModes {
    public static void main(String[] args) {
        floorExamples();
        ceilingExamples();
        downExamples();
        upExamples();
    }

    private static void floorExamples() {
        // -infinity <---------|---|---|---|---|---|---|---|---|-------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //                        <------------------------ Moving towards negative infinity
        //  1.3 ->  1
        // -1.3 -> -2

        BigDecimal oneThird = BigDecimal.ONE
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.FLOOR);

        BigDecimal negativeOneThird = BigDecimal.ONE.negate()
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.FLOOR);

        System.out.println("\n\n-----------------------------\n");

        System.out.println("Moving towards negative infinity : FLOOR\n");
        System.out.printf("%20.4f\n", oneThird);
        System.out.printf("%20.4f\n", negativeOneThird);

    }

    private static void ceilingExamples() {
        // -infinity <---------|---|---|---|---|---|---|---|---|-------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //                Moving towards positive infinity ------------------------>
        //  1.3 ->  2
        // -1.3 -> -1

        BigDecimal oneThird = BigDecimal.ONE
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.CEILING);

        BigDecimal negativeOneThird = BigDecimal.ONE.negate()
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.CEILING);

        System.out.println("\n\n-----------------------------\n");

        System.out.println("Moving towards positive infinity : CEILING\n");
        System.out.printf("%20.4f\n", oneThird);
        System.out.printf("%20.4f\n", negativeOneThird);
    }

    private static void downExamples() {
        //               : DOWN
        // -infinity <---------|---|---|---|---|---|---|---|---|-------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //         ----------------> Moving towards zero <----------------
        //  1.3 ->  1
        // -1.3 -> -1

        BigDecimal oneThird = BigDecimal.ONE
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.DOWN);

        BigDecimal negativeOneThird = BigDecimal.ONE.negate()
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.DOWN);

        System.out.println("\n\n-----------------------------\n");

        System.out.println("Moving towards zero : DOWN\n");
        System.out.printf("%20.4f\n", oneThird);
        System.out.printf("%20.4f\n", negativeOneThird);
    }

    private static void upExamples() {
        //             : UP
        // -infinity <---------|---|---|---|---|---|---|---|---|-------> + infinity
        //                   -.4 -.3 -.2 -.1   0  .1  .2  .3  .4
        //         <---------------- Moving away from zero ---------------->
        //  1.3 ->  2
        // -1.3 -> -2

        BigDecimal oneThird = BigDecimal.ONE
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.UP);

        BigDecimal negativeOneThird = BigDecimal.ONE.negate()
                .divide(BigDecimal.valueOf(3), 4, RoundingMode.UP);

        System.out.println("\n\n-----------------------------\n");

        System.out.println("Moving away from zero : UP\n");
        System.out.printf("%20.4f\n", oneThird);
        System.out.printf("%20.4f\n", negativeOneThird);
    }
}
