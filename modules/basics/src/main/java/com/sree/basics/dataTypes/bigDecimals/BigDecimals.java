package com.sree.basics.dataTypes.bigDecimals;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;

public final class BigDecimals {

    private BigDecimals() {
    }

    public static void main(String[] args) {
//        createBigDecimal();
//        createBigDecimalWithScale();
        bigDecimalDivision(System.out);
    }

    private static void bigDecimalDivision(PrintStream output) {
        output.println("\n\n-----------------------------\n");
        output.println("positive scale -> How many decimal places to preserve\n");
        printDivisionValue(output, 100_000, 3, 5, RoundingMode.FLOOR, "|%10.5f|\n");
        output.println("\n-----------------------------\n");

        output.println("zero scale -> only integer value;\ndrops all decimal places\n");
        printDivisionValue(output, 100_000, 3, 0, RoundingMode.FLOOR, "|%10.5f|\n");
        output.println("\n-----------------------------\n");

        output.println("""
                negative scale -> no.of digits to the left of decimal to drop;
                drops all decimal places;
                scale: -1 to -5
                """);
        for (int scale = -1; scale >= -5; scale--) {
            printDivisionValue(output, 100_000, 3, scale, RoundingMode.FLOOR, "|%10.2f|\n");
        }
        output.println("\n-----------------------------\n");

    }

    private static void createBigDecimalWithScale() {
        print(System.out, BigDecimal.valueOf(25, 2), "|%20.10f|\n");
        print(System.out, BigDecimal.valueOf(25, 0), "|%20.10f|\n");
        print(System.out, BigDecimal.valueOf(25, -2), "|%20.10f|\n");
        System.out.println("-----------------------------");

    }

    private static void createBigDecimal() {
        print(System.out, new BigDecimal(BigInteger.ONE), "|%20.10f|\n");
        System.out.println("-----------------------------");
        print(System.out, new BigDecimal("1"), "|%20.10f|\n");
        print(System.out, new BigDecimal("1.0"), "|%20.10f|\n");
        System.out.println("-----------------------------");

        print(System.out, BigDecimal.valueOf(123456789), "|%20.10f|\n");
        System.out.println("-----------------------------");

        BigDecimal twentyTwoPointFive = new BigDecimal("22.5");
        BigDecimal threePointOne = new BigDecimal("3.1");

        print(System.out, twentyTwoPointFive.add(threePointOne), "|%20.10f|\n");
        print(System.out, threePointOne.subtract(twentyTwoPointFive), "|%20.10f|\n");
        print(System.out, threePointOne.multiply(twentyTwoPointFive), "|%20.10f|\n");
        System.out.println("-----------------------------");
    }

    public static BigDecimal divide(
            BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode rounding) {
        Objects.requireNonNull(dividend, "dividend");
        Objects.requireNonNull(divisor, "divisor");
        Objects.requireNonNull(rounding, "rounding");
        return dividend.divide(divisor, scale, rounding);
    }

    public static BigDecimal divide(long dividend, long divisor, int scale, RoundingMode rounding) {
        return divide(BigDecimal.valueOf(dividend), BigDecimal.valueOf(divisor), scale, rounding);
    }

    public static void printDivisionValue(
            PrintStream output,
            long dividend,
            long divisor,
            int scale,
            RoundingMode rounding,
            String format) {
        print(output, divide(dividend, divisor, scale, rounding), format);
    }

    public static void print(PrintStream output, BigDecimal value, String format) {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(format, "format");
        output.printf(format, value);
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
