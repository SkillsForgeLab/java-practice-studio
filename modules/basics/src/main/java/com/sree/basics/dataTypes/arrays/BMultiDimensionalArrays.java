package com.sree.basics.dataTypes.arrays;

public class BMultiDimensionalArrays {
    // multi-dimensional: array of arrays - can be non-rectangular
    //                    { {1, 2, 3}, {3, 4}, {4, 5, 6, 7) }

    public void declarations() {
        // Java Style
        int[] arrayOfFirstFiveOddNumbers;
        String[][] arrayOfArraysOfString;
        int[][] grid = new int[3][4];

        // C-Style
        int arrayOfFirstFiveEvenNumbers[];
        char[] charArray[];
        char charArray2[][];
    }

    public void initializationUsingLiterals() {

        int[][] triangle = {
                {1},
                {2, 3},
                {4, 5, 6}
        };

        String[][] arrayOfArraysOfString = {
                {"Amy", "Bella", "Charlie", "David",},
                {"Fred", "Sheila", "Jim"},
        };

        char[][] charArray = {
                {'a', 'e', 'i', 'o', 'u'},
                {'c', 'c', 'd', 'f', 'g'}
        };
    }

    public static void main(String[] args) {

    }
}
