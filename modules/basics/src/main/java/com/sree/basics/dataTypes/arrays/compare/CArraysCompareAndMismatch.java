package com.sree.basics.dataTypes.arrays.compare;

import java.util.Arrays;

public class CArraysCompareAndMismatch {

    // uses lexicographic order

    // ant comes before(less than) bee - a comes before b
    // {2, 3, 4, 5} is greater that {1, 2, 3, 4, 5} -> 2 is greater than 1
    // ant comes before(less than) anthony

    // mismatch could be due to
    //     -> an element:   {1,2,3}, {1,4,4}   - Elements at index 1 are different
    //     -> array length: {3,7,1}, {3,7,1,6} - First three elements are a identical.
    //                                           Second array has additional elements.

    // return value
    // **** element mismatch **** (array size doesn't matter - Larger array size does not necessarily mean that array is greater)
    //        +1: first array comes after (greater than) second array
    //        -1: indicates first array comes before (smaller than) second array
    // **** size mismatch ****
    //        exact elements of the smaller array must be present in the larger array before additional elements appear
    //        +ve: indicates number of additional elements in array1 compared to array2
    //        -ve: indicates number of fewer elements in array1 compared to array2
    //         0: if both are identical
    private static void dotCompare(int[] intArray1, int[] intArray2) {
        System.out.println("\n+++++++++++ comparing arrays +++++++++++");

        System.out.println("Printing contents of Array1: " + Arrays.toString(intArray1));
        System.out.println("Printing contents of Array2: " + Arrays.toString(intArray2));
        int arrayCompare = Arrays.compare(intArray1, intArray2);

        String displayMessage = "Comparing arrays -> " + arrayCompare +
                (arrayCompare > 0
                        ? ((intArray1.length == intArray2.length) ? "\nArray1 comes after Array2." : "\nArray1 has more elements than Array2.")
                        : (intArray1.length == intArray2.length) ? "\nArray1 comes before Array2" : "\nArray1 has fewer elements than Array2.");

        System.out.println(displayMessage);
        System.out.println("\n------------------------------------");
    }

    // uses lexicographic order
    // returns first index at which mismatch is found (Array size does not affect the outcome like in compare function)
    // Does not say anything about the relative order of the items
    private static void dotMismatch(int[] intArray1, int[] intArray2) {
        System.out.println("\n+++++++++++ finding the first mismatch between two arrays +++++++++++");

        System.out.println("Printing contents of Array1: " + Arrays.toString(intArray1));
        System.out.println("Printing contents of Array2: " + Arrays.toString(intArray2));
        System.out.println("First mismatch at index -> " + Arrays.mismatch(intArray1, intArray2));
        System.out.println("\n------------------------------------");
    }

    public static void main(String[] args) {

        dotCompare(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 6, 5, 7});
        dotCompare(new int[]{1, 2, 6, 4, 5}, new int[]{1, 2, 3, 5, 7});
        dotCompare(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        dotCompare(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1, 2, 3, 4, 5});

        System.out.println("\n------------------------------------");

        dotMismatch(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 6, 5, 7});
        dotMismatch(new int[]{1, 2, 6, 4, 5}, new int[]{1, 2, 3, 5, 7});
        dotMismatch(new int[]{1, 2, 3, 4, 5}, new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        dotMismatch(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{1, 2, 3, 4, 5});
    }
}