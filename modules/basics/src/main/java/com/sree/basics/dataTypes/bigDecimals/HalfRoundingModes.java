package com.sree.basics.dataTypes.bigDecimals;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfRoundingModes {
    private static final BigDecimal point121 = BigDecimal.valueOf(0.121);
    private static final BigDecimal point125 = BigDecimal.valueOf(0.125);
    private static final BigDecimal point127 = BigDecimal.valueOf(0.127);
    private static final BigDecimal point135 = BigDecimal.valueOf(0.135);

    private static final BigDecimal negPoint121 = point121.negate();
    private static final BigDecimal negPoint125 = point125.negate();
    private static final BigDecimal negPoint127 = point127.negate();
    private static final BigDecimal negPoint135 = point135.negate();

    public static void main(String[] args) {
        printOriginalValues();
        halfUpExamples();
        // halfDownExamples();
        // halfEvenExamples();
    }

    private static void halfDownExamples() {
        /**
         *
         *                     -2  -1.5  -1  -0.5   0   0.5   1   1.5   2.0 2.5  3.0  3.5  4.0
         *  -infinity ◀---------│--|-│----│----│-|--│--|-│----│----│-|--│----│----│----│----│---------▶ +infinity
         *                      ◀-1.7        -0.4▶   ◀0.3           1.7▶
         *
         * HALF_DOWN : Halfway    ──►  go DOWN (towards ZERO)
         *             Otherwise  ──►  round to nearest integer
         *
         *  0.3 → 0    round to nearest integer
         *  1.7 → 2    round to nearest integer
         *
         * -0.4 → 0    round to nearest integer
         * -1.7 → 2    round to nearest integer
         *
         *  0.5 → 0    go DOWN (towards ZERO)
         *  1.5 → 1    go DOWN (towards ZERO)
         *
         * -0.5 → 0    go DOWN (towards ZERO)
         * -1.5 → -1   go DOWN (towards ZERO)
         *
         * */
        RoundingMode rounding = RoundingMode.HALF_DOWN;
        int scale = 2;

        printScaledRoundedValues(scale, rounding);
    }

    private static void halfUpExamples() {
        /**
         *
         *                     -2  -1.5  -1  -0.5   0   0.5   1   1.5   2.0 2.5  3.0  3.5  4.0
         *  -infinity ◀---------│--|-│----│----│-|--│--|-│----│----│-|--│----│----│----│----│---------▶ +infinity
         *                      ◀-1.7        -0.4▶   ◀0.3           1.7▶
         *
         * HALF_UP :   Halfway    ──►  go UP (away from ZERO)
         *             Otherwise  ──►  round to nearest integer
         *
         *  0.3 → 0    round to nearest integer
         *  1.7 → 2    round to nearest integer
         *
         * -0.4 → 0    round to nearest integer
         * -1.7 → 2    round to nearest integer
         *
         *  0.5 → 1    go UP (away from ZERO)
         *  1.5 → 2    go UP (away from ZERO)
         *
         * -0.5 → -1   go UP (away from ZERO)
         * -1.5 → -2   go UP (away from ZERO)
         *
         * */
        RoundingMode rounding = RoundingMode.HALF_UP;
        int scale = 4;

        printScaledRoundedValues(scale, rounding);
    }

    private static void halfEvenExamples() {
        /**
         *
         *                     -2  -1.5  -1  -0.5   0   0.5   1   1.5   2.0 2.5  3.0  3.5  4.0
         *  -infinity ◀---------│----│----│----│----│--|-│--|-│----│-|--│----│----│----│----│---------▶ +infinity
         *                                           ◀0.3  0.8▶     1.7▶
         *                                                        1.5──►2◄──2.5       3.5──►4
         * HALF_EVEN : Halfway    ──►  go to nearest EVEN Value
         *             Otherwise  ──►  round to nearest integer
         *  0.3 → 0    round to nearest integer
         *  1.7 → 2    round to nearest integer
         * -0.4 → 0    round to nearest integer
         * -1.7 → 2    round to nearest integer
         *  1.5 → 2    go to nearest EVEN Value
         *  2.5 → 2    go to nearest EVEN Value (Not 3 as 3 is ODD)
         *  3.5 → 4    go to nearest EVEN Value
         * WHY ???
         * When adding many numbers, resulting SUM will be NEARER TO ACCURATE VALUE
         * because as many values are likely to round up as round down.
         * Also, anything derived from SUM e.g. mean, average are likely to be LESS INACCURATE
         * */

        RoundingMode rounding = RoundingMode.HALF_EVEN;
        int scale = 2;

        printScaledRoundedValues(scale, rounding);
    }


    private static void unnecessaryExamples() {

    }

    private static void printScaledRoundedValues(int scale, RoundingMode rounding) {

        BigDecimal point121r = point121.setScale(scale, rounding);
        BigDecimal point125r = point125.setScale(scale, rounding);
        BigDecimal point127r = point127.setScale(scale, rounding);
        BigDecimal point135r = point135.setScale(scale, rounding);

        BigDecimal negPoint121r = negPoint121.setScale(scale, rounding);
        BigDecimal negPoint125r = negPoint125.setScale(scale, rounding);
        BigDecimal negPoint127r = negPoint127.setScale(scale, rounding);
        BigDecimal negPoint135r = negPoint135.setScale(scale, rounding);

        System.out.println("RoundingMode → " + rounding);
        System.out.println("Scale → " + scale + "\n");


        System.out.println(" 0.121 rounds to →  " + point121r);
        System.out.println(" 0.125 rounds to →  " + point125r);
        System.out.println(" 0.127 rounds to →  " + point127r);
        System.out.println(" 0.135 rounds to →  " + point135r);

        System.out.println("-0.121 rounds to → " + negPoint121r);
        System.out.println("-0.125 rounds to → " + negPoint125r);
        System.out.println("-0.127 rounds to → " + negPoint127r);
        System.out.println("-0.135 rounds to → " + negPoint135r);

        System.out.println("\n-------------------------------\n");
    }

    private static void printOriginalValues() {
        System.out.println("\n-------------------------------\n");
        System.out.println("Original values.\n");
        System.out.println(" 0.121 →  " + point121);
        System.out.println(" 0.125 →  " + point125);
        System.out.println(" 0.127 →  " + point127);
        System.out.println(" 0.135 →  " + point135);

        System.out.println("-0.121 → " + negPoint121);
        System.out.println("-0.125 → " + negPoint125);
        System.out.println("-0.127 → " + negPoint127);
        System.out.println("-0.135 → " + negPoint135);

        System.out.println("\n-------------------------------\n");
    }

}
