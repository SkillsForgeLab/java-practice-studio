package com.sree.basics.dataTypes.arrays.compare;

import java.util.Arrays;

/**
 * Arrays.equals(first, second);
 * Arrays.mismatch(first, second);
 * <p>
 * *************  Copying details  *************
 * Primitive elements become independent values.
 * Object references remain shared unless you deep-copy each object.
 *
 * <p>
 * ***************  Common traps  **************
 * first == second                 // compares array identity
 * Arrays.equals(first, second)    // compares elements
 * *********************************************
 *
 */
public class BPrimitiveArrayComparisons {

    public static void main(String[] args) {
        comparePrimitiveArrays();
//        compareObjectArrays();
    }

    private static void comparePrimitiveArrays() {
        printHeading("8. Compare primitive arrays (int[])");

        int[] first = {10, 20, 30};
        int[] sameValues = {10, 20, 30};
        int[] differentValues = {10, 25, 30};

        printComparisonMemoryAid();

        System.out.println("first           = " + Arrays.toString(first));
        System.out.println("sameValues      = " + Arrays.toString(sameValues));
        System.out.println("differentValues = " + Arrays.toString(differentValues));

        System.out.println("\nCompare first with sameValues:");
        System.out.println("==               same array?       " + (first == sameValues));
        System.out.println("Arrays.equals    same contents?     "
                + Arrays.equals(first, sameValues));
        System.out.println("Arrays.compare   which comes first? "
                + Arrays.compare(first, sameValues) + " (0 means tied)");
        System.out.println("Arrays.mismatch  first difference?  "
                + Arrays.mismatch(first, sameValues) + " (-1 means none)");

        System.out.println("\nCompare first with differentValues:");
        System.out.println("Arrays.equals    same contents?     "
                + Arrays.equals(first, differentValues));
        System.out.println("Arrays.compare   which comes first? "
                + Arrays.compare(first, differentValues) + " (negative means first)");
        System.out.println("Arrays.mismatch  first difference?  "
                + Arrays.mismatch(first, differentValues) + " (index 1)");
    }

    private static void printComparisonMemoryAid() {
        System.out.println("Remember these four questions:");
        System.out.println("1. ==              → Same array object?");
        System.out.println("2. Arrays.equals   → Same contents?");
        System.out.println("3. Arrays.compare  → Which array comes first?");
        System.out.println("4. Arrays.mismatch → Where is the first difference?\n");
    }

    private static void printHeading(String heading) {
        System.out.println("\n--- " + heading + " ---\n");
    }

}
