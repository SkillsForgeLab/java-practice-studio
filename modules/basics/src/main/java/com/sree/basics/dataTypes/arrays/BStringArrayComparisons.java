package com.sree.basics.dataTypes.arrays;

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
public class BStringArrayComparisons {

    public static void main(String[] args) {
        printComparisonMemoryAid();
        compareStringObjectArrays();
    }

    private static void compareStringObjectArrays() {
        printHeading("9. Compare string object arrays (String[])");

        // Object arrays store references. These are different String objects
        // containing the same text. "new String" is used only for this lesson.
        String[] first = {"Amy", "Bella"};
        String[] sameText = {"Amy", "Bella"};

        System.out.println("One extra rule for object arrays:");
        System.out.println("== on elements asks: same object?");
        System.out.println("equals on elements asks: same logical value?\n");

        System.out.println("first == sameText: " + (first == sameText));
        System.out.println("first[0] == sameText[0]: " + (first[0] == sameText[0]));
        System.out.println("first[0].equals(sameText[0]): "
                + first[0].equals(sameText[0]));

        System.out.println("\nThe four array questions still work the same way:");
        System.out.println("Arrays.equals    same contents?     "
                + Arrays.equals(first, sameText));
        System.out.println("Arrays.compare   which comes first? "
                + Arrays.compare(first, sameText) + " (0 means tied)");
        System.out.println("Arrays.mismatch  first difference?  "
                + Arrays.mismatch(first, sameText) + " (-1 means none)");

        sameText[1] = "Cara";

        System.out.println("\nAfter changing Bella to Cara:");
        System.out.println("first    = " + Arrays.toString(first));
        System.out.println("sameText = " + Arrays.toString(sameText));
        System.out.println("Arrays.equals    same contents?     "
                + Arrays.equals(first, sameText));
        System.out.println("Arrays.compare   which comes first? "
                + Arrays.compare(first, sameText) + " (negative means first)");
        System.out.println("Arrays.mismatch  first difference?  "
                + Arrays.mismatch(first, sameText) + " (index 1)");
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
