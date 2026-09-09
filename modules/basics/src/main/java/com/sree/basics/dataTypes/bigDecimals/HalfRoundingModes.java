package com.sree.basics.dataTypes.bigDecimals;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class HalfRoundingModes {
    private static final List<BigDecimal> EXAMPLE_VALUES = Collections.unmodifiableList(Arrays.asList(
            new BigDecimal("0.121"),
            new BigDecimal("0.125"),
            new BigDecimal("0.127"),
            new BigDecimal("0.135"),
            new BigDecimal("-0.121"),
            new BigDecimal("-0.125"),
            new BigDecimal("-0.127"),
            new BigDecimal("-0.135")
    ));

    private HalfRoundingModes() {
    }

    public static void main(String[] args) {
        printOriginalValues(System.out, EXAMPLE_VALUES);
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

        printScaledRoundedValues(System.out, EXAMPLE_VALUES, scale, rounding);
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

        printScaledRoundedValues(System.out, EXAMPLE_VALUES, scale, rounding);
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

        printScaledRoundedValues(System.out, EXAMPLE_VALUES, scale, rounding);
    }
    public static List<BigDecimal> roundValues(
            List<BigDecimal> values, int scale, RoundingMode rounding) {
        Objects.requireNonNull(values, "values");
        Objects.requireNonNull(rounding, "rounding");

        List<BigDecimal> roundedValues = new ArrayList<>(values.size());
        for (BigDecimal value : values) {
            roundedValues.add(Objects.requireNonNull(value, "value").setScale(scale, rounding));
        }
        return roundedValues;
    }

    public static void printScaledRoundedValues(
            PrintStream output, List<BigDecimal> values, int scale, RoundingMode rounding) {
        Objects.requireNonNull(output, "output");
        List<BigDecimal> roundedValues = roundValues(values, scale, rounding);

        output.println("RoundingMode → " + rounding);
        output.println("Scale → " + scale + "\n");

        for (int index = 0; index < values.size(); index++) {
            printValue(output, values.get(index), " rounds to → ", roundedValues.get(index));
        }
        output.println("\n-------------------------------\n");
    }

    public static void printOriginalValues(PrintStream output, List<BigDecimal> values) {
        Objects.requireNonNull(output, "output");
        Objects.requireNonNull(values, "values");

        output.println("\n-------------------------------\n");
        output.println("Original values.\n");
        for (BigDecimal value : values) {
            BigDecimal nonNullValue = Objects.requireNonNull(value, "value");
            printValue(output, nonNullValue, " → ", nonNullValue);
        }
        output.println("\n-------------------------------\n");
    }

    private static void printValue(
            PrintStream output, BigDecimal value, String separator, BigDecimal result) {
        String leadingSpace = value.signum() >= 0 ? " " : "";
        String resultPadding = value.signum() >= 0 ? " " : "";
        output.println(
                leadingSpace + value.toPlainString() + separator + resultPadding + result.toPlainString());
    }
}
