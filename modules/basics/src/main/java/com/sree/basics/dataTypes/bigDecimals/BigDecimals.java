package com.sree.basics.dataTypes.bigDecimals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BigDecimals {

    public static void main(String[] args) {
//        createBigDecimal();
//        createBigDecimalWithScale();
        bigDecimalDivision();
    }

    private static void bigDecimalDivision() {
        System.out.println("\n\n-----------------------------\n");
        System.out.println("positive scale -> How many decimal places to preserve\n");
        printfDivisionFloorValue(100_000, 3, 5, "|%10.5f|\n");
        System.out.println("\n-----------------------------\n");

        System.out.println("zero scale -> only integer value;\ndrops all decimal places\n");
        printfDivisionFloorValue(100_000, 3, 0, "|%10.5f|\n");
        System.out.println("\n-----------------------------\n");

        System.out.println("""
                negative scale -> no.of digits to the left of decimal to drop;
                drops all decimal places;
                scale: -1 to -5
                """);
        printfDivisionFloorValue(100_000, 3, -1, "|%10.2f|\n");
        printfDivisionFloorValue(100_000, 3, -2, "|%10.2f|\n");
        printfDivisionFloorValue(100_000, 3, -3, "|%10.2f|\n");
        printfDivisionFloorValue(100_000, 3, -4, "|%10.2f|\n");
        printfDivisionFloorValue(100_000, 3, -5, "|%10.2f|\n");
        System.out.println("\n-----------------------------\n");

    }

    private static void createBigDecimalWithScale() {
        printf(BigDecimal.valueOf(25, 2), "|%20.10f|\n");
        printf(BigDecimal.valueOf(25, 0), "|%20.10f|\n");
        printf(BigDecimal.valueOf(25, -2), "|%20.10f|\n");
        System.out.println("-----------------------------");

    }

    private static void createBigDecimal() {
        printf(new BigDecimal(BigInteger.ONE), "|%20.10f|\n");
        System.out.println("-----------------------------");
        printf(new BigDecimal("1"), "|%20.10f|\n");
        printf(new BigDecimal("1.0"), "|%20.10f|\n");
        System.out.println("-----------------------------");

        printf(BigDecimal.valueOf(123456789), "|%20.10f|\n");
        System.out.println("-----------------------------");

        BigDecimal twentyTwoPointFive = new BigDecimal("22.5");
        BigDecimal threePointOne = new BigDecimal("3.1");

        printf(twentyTwoPointFive.add(threePointOne), "|%20.10f|\n");
        printf(threePointOne.subtract(twentyTwoPointFive), "|%20.10f|\n");
        printf(threePointOne.multiply(twentyTwoPointFive), "|%20.10f|\n");
        System.out.println("-----------------------------");
    }


    private static void printfDivisionFloorValue(int divisor, int dividend, int scale, String format) {
        BigDecimal value2 = BigDecimal.valueOf(divisor)
                .divide(new BigDecimal(dividend), scale, RoundingMode.FLOOR);
        System.out.printf(format, value2);
    }


    private static void printf(BigDecimal value, String format) {
        System.out.printf(format, value);
    }
}


//        BigDecimal oneThird = BigDecimal.ONE.divide(
//                BigDecimal.valueOf(3), 0, RoundingMode.FLOOR
//        );
//        printf(oneThird);
//
//        oneThird = BigDecimal.ONE.divide(
//                BigDecimal.valueOf(3), 4, RoundingMode.FLOOR
//        );
//        printf(oneThird);
//
//        oneThird = BigDecimal.ONE.divide(
//                BigDecimal.valueOf(3), -3, RoundingMode.FLOOR
//        );
//        printf(oneThird);
// divide(3, -3, RoundingMode.FLOOR) means:
// Divide by 3 and represent the result with a scale of -3.

// A scale of -3 means the result is rounded to the nearest 1000:
// the possible values at scale -3 are multiples of 1000:
// ..., -2000, -1000, 0, 1000, 2000, ...
// 0.333... is between 0 and 1000, so FLOOR chooses: 0