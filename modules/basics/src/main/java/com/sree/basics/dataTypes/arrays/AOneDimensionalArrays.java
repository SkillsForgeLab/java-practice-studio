package com.sree.basics.dataTypes.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A small, runnable lesson about one-dimensional arrays.
 *
 * <p>For each section: predict the output, run the program, and then change one
 * value or statement to check your understanding.</p>
 * <p>
 * **********  Useful Arrays methods  **********
 * Arrays.sort(numbers);
 * Arrays.binarySearch(numbers, 20);  // expects the array to be sorted first.
 * Arrays.fill(numbers, 0);
 * Arrays.copyOf(numbers, newLength);
 * Arrays.copyOfRange(numbers, from, to);
 * Arrays.equals(first, second);
 * Arrays.mismatch(first, second);
 * <p>
 * *************  Copying details  *************
 * Assignment creates an alias: second = first.
 * clone(), Arrays.copyOf(), and System.arraycopy() make shallow copies.
 * Primitive elements become independent values.
 * Object references remain shared unless you deep-copy each object.
 * System.arraycopy() can copy only part of an array and safely handles overlapping regions within the same array.
 * <p>
 * ***************  Common traps  **************
 * first == second                 // compares array identity
 * Arrays.equals(first, second)    // compares elements
 * <p>
 * System.out.println(numbers);                  // unreadable identity text
 * System.out.println(Arrays.toString(numbers)); // readable contents
 * <p>
 * For nested arrays, use Arrays.deepToString() and Arrays.deepEquals().
 * *********************************************
 *
 */
public class AOneDimensionalArrays {

    public static void main(String[] args) {
//        creatingArrays();
//        readingAndUpdatingElements();
//        fillingAnArrayWithAForLoop();
//        traversingAndCalculating();
//        copyUsingSystemArraycopy();
//        partialCopyUsingSystemArraycopy();
//        shallowCopy();
//        partialShallowCopyUsingSystemArraycopy();
//        deepCopy();
//        printPracticeChallenges();
//        compareArraysOfPrimitives();
        compareArraysOfNonPrimitives();
    }

    private static void creatingArrays() {
        printHeading("1. Create arrays");

        // Java's preferred declaration style places [] beside the element type.
        int[] defaultValues = new int[5];
        int[] oddNumbers = {1, 3, 5, 7, 9};
        String[] names = new String[]{"Fred", "Sheila", "Jim"};

        System.out.println("new int[5]        = " + Arrays.toString(defaultValues));
        System.out.println("Array literal     = " + Arrays.toString(oddNumbers));
        System.out.println("new String[]{...} = " + Arrays.toString(names));
        System.out.println("Number of odd numbers = " + oddNumbers.length);

        // Array elements receive default values: 0 for int and null for String.
        // A local array variable itself must be assigned before it can be used.
    }

    private static void readingAndUpdatingElements() {
        printHeading("2. Read and update elements");

        int[] scores = {82, 91, 76};

        // Indexes start at 0, so the last valid index is length - 1.
        System.out.println("First score = " + scores[0]);
        System.out.println("Last score  = " + scores[scores.length - 1]);

        scores[2] = 88;
        System.out.println("After updating index 2 = " + Arrays.toString(scores));

        // scores[scores.length] would throw ArrayIndexOutOfBoundsException.
    }

    private static void fillingAnArrayWithAForLoop() {
        printHeading("3. Fill an array with a for loop");

        int[] evenNumbers = new int[5];

        for (int index = 0; index < evenNumbers.length; index++) {
            evenNumbers[index] = (index + 1) * 2;
        }

        System.out.println("First five positive even numbers = "
                + Arrays.toString(evenNumbers));
    }

    private static void traversingAndCalculating() {
        printHeading("4. Traverse and calculate");

        int[] temperatures = {72, 75, 68, 80};

        // Use a traditional for loop when the index is useful.
        for (int index = 0; index < temperatures.length; index++) {
            System.out.println("temperatures[" + index + "] = " + temperatures[index]);
        }

        // Use an enhanced for loop when only the values are needed.
        int total = 0;
        for (int temperature : temperatures) {
            total += temperature;
        }

        double average = (double) total / temperatures.length;
        System.out.println("Total   = " + total);
        System.out.println("Average = " + average);
    }

    private static void copyUsingSystemArraycopy() {
        printHeading("5a. Copy using System.arraycopy");

        int[] source = {10, 20, 30, 40};
        int[] destination = new int[source.length];

        // Parameters: source, source start, destination, destination start, count.
        System.arraycopy(source, 0, destination, 0, source.length);
        destination[0] = 99;

        System.out.println("Source      = " + Arrays.toString(source));
        System.out.println("Destination = " + Arrays.toString(destination));
        System.out.println("Primitive values were copied into a separate array.");
    }

    private static void partialCopyUsingSystemArraycopy() {
        printHeading("5b. Copy using System.arraycopy");

        int[] source = {10, 20, 30, 40};
        int[] destination = new int[2];

        // Parameters: source, source start, destination, destination start, count.
        System.arraycopy(source, 1, destination, 0, 2);
        destination[1] = 99;

        System.out.println("Source      = " + Arrays.toString(source));
        System.out.println("Destination = " + Arrays.toString(destination));
        System.out.println("Primitive values were copied into a separate array.");
    }

    private static void shallowCopy() {
        printHeading("6a. Shallow copy");

        Student[] original = {
                new Student("Amy", 80),
                new Student("Bella", 90)
        };

        // A new array is created, but both arrays refer to the same Student objects.
        Student[] shallowCopy = Arrays.copyOf(original, original.length);
        shallowCopy[0].score = 100;

        System.out.println("Original     = " + Arrays.toString(original));
        System.out.println("Shallow copy = " + Arrays.toString(shallowCopy));
        System.out.println("Same array? " + (original == shallowCopy));
        System.out.println("Same first Student? " + (original[0] == shallowCopy[0]));
        System.out.println("Changing the shared Student changed both arrays.");
    }

    private static void partialShallowCopyUsingSystemArraycopy() {
        printHeading("6b. Shallow copy using System.arraycopy");

        Student[] original = {
                new Student("Amy", 80),
                new Student("Bella", 60),
                new Student("Charlie", 70),
                new Student("David", 90)
        };

        Student[] shallowCopy = new Student[2];

        // Parameters: source, source start, destination, destination start, count.
        System.arraycopy(original, 1, shallowCopy, 0, 2);

        // A new array is created, but both arrays refer to the same Student objects.
        shallowCopy[0].score = 100;

        System.out.println("Original     = " + Arrays.toString(original));
        System.out.println("Shallow copy = " + Arrays.toString(shallowCopy));
        System.out.println("Same array? " + (original == shallowCopy));
        System.out.println("Same first Student? " + (original[1] == shallowCopy[0]));
        System.out.println("Changing the shared Student changed both arrays.");
    }

    private static void deepCopy() {
        printHeading("7. Deep copy");

        Student[] original = {
                new Student("Amy", 80),
                new Student("Bella", 90)
        };
        Student[] deepCopy = new Student[original.length];

        // Copy each Student as well as the array that contains the references.
        for (int index = 0; index < original.length; index++) {
            Student student = original[index];
            deepCopy[index] = new Student(student.name, student.score);
        }

        deepCopy[0].score = 100;

        System.out.println("Original  = " + Arrays.toString(original));
        System.out.println("Deep copy = " + Arrays.toString(deepCopy));
        System.out.println("Same array? " + (original == deepCopy));
        System.out.println("Same first Student? " + (original[0] == deepCopy[0]));
        System.out.println("Changing the copied Student did not change the original.");
    }

    private static void compareArraysOfPrimitives() {
        System.out.println("\n-----------------------------");

        printHeading("8. Compare arrays");

        int[] source = {10, 20, 30, 40};
        int[] alias = source;
        int[] copied = Arrays.copyOf(source, source.length);

        System.out.println("source == alias: " + (source == alias));
        System.out.println("source == copied: " + (source == copied));

        System.out.println("\n-----------------------------\n");
        System.out.println(
                "source and alias have equal contents:  "
                        + Arrays.equals(source, alias)
        );

        System.out.println(
                "source and copied have equal contents: "
                        + Arrays.equals(source, copied)
        );

        System.out.println(
                "Lexicographical comparison of source and copied: "
                        + Arrays.compare(source, copied)
        );

        copied[0] = 99;

        System.out.println("\n-----------------------------\n");
        System.out.println("After changing copied[0]:");
        System.out.println("Source = " + Arrays.toString(source));
        System.out.println("Copied = " + Arrays.toString(copied));
        System.out.println("Equal contents: " + Arrays.equals(source, copied));
        System.out.println("First mismatch: " + Arrays.mismatch(source, copied));
        System.out.println("\n-----------------------------\n");
    }

    private static void compareArraysOfNonPrimitives() {
        System.out.println("\n-----------------------------");

        printHeading("9. Compare arrays of objects");

        Student[] source = {
                new Student("Amy", 80),
                new Student("Bella", 90),
                new Student("Charlie", 60)
        };
        Student[] alias = source;
        Student[] shallowCopied = Arrays.copyOf(source, source.length);
        Student[] deepCopied = new Student[source.length];

        for (int index = 0; index < source.length; index++) {
            Student student = source[index];
            deepCopied[index] = new Student(student.name, student.score);
        }

        Comparator<Student> byNameThenScore = Comparator
                .comparing((Student student) -> student.name)
                .thenComparingInt(student -> student.score);

        System.out.println("source == alias: " + (source == alias));
        System.out.println("source == shallowCopied: " + (source == shallowCopied));
        System.out.println("source == deepCopied: " + (source == deepCopied));

        System.out.println("\n-----------------------------\n");
        System.out.println("source[0] == alias[0]: " + (source[0] == alias[0]));
        System.out.println("source[0] == shallowCopied[0]: "
                + (source[0] == shallowCopied[0]));
        System.out.println("source[0] == deepCopied[0]: "
                + (source[0] == deepCopied[0]));

        System.out.println("\n-----------------------------\n");
        System.out.println(
                "source and shallowCopied have equal field values: "
                        + Arrays.equals(source, shallowCopied, byNameThenScore)
        );
        System.out.println(
                "source and deepCopied have equal field values: "
                        + Arrays.equals(source, deepCopied, byNameThenScore)
        );
        System.out.println(
                "Lexicographical comparison of source and deepCopied: "
                        + Arrays.compare(source, deepCopied, byNameThenScore)
        );

        shallowCopied[0].score = 100;

        System.out.println("\n-----------------------------\n");
        System.out.println("After changing shallowCopied[0].score:");
        System.out.println("Source         = " + Arrays.toString(source));
        System.out.println("Shallow copied = " + Arrays.toString(shallowCopied));
        System.out.println("Deep copied    = " + Arrays.toString(deepCopied));
        System.out.println("Source equals shallow copy: "
                + Arrays.equals(source, shallowCopied, byNameThenScore));
        System.out.println("Source equals deep copy: "
                + Arrays.equals(source, deepCopied, byNameThenScore));
        System.out.println("First mismatch with deep copy: "
                + Arrays.mismatch(source, deepCopied, byNameThenScore));
        System.out.println("\n-----------------------------\n");
    }

    private static void printPracticeChallenges() {
        printHeading("Practice: predict, change, and run again");

        System.out.println("1. Change the loop to create the first five odd numbers.");
        System.out.println("2. Find the largest value in the temperatures array.");
        System.out.println("3. Create a reversed copy of {1, 2, 3, 4}.");
        System.out.println("4. Explain the difference between a shallow copy and a deep copy.");
    }

    private static void printHeading(String heading) {
        System.out.println("\n--- " + heading + " ---\n");
    }

    private static final class Student {
        private final String name;
        private int score;

        private Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + "=" + score;
        }
    }
}
