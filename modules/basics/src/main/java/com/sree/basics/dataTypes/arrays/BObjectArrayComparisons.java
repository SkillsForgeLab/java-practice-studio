package com.sree.basics.dataTypes.arrays;

import java.util.Arrays;
import java.util.Objects;

/**
 * Compares arrays of Student objects using four different questions.
 *
 * <p>Remember:</p>
 * <ol>
 *     <li>{@code ==}: Are these variables pointing to the same array?</li>
 *     <li>{@code Arrays.equals}: Do both arrays contain logically equal students?</li>
 *     <li>{@code Arrays.compare}: Which array comes first, or are they tied?</li>
 *     <li>{@code Arrays.mismatch}: At which index do they first differ?</li>
 * </ol>
 */
public class BObjectArrayComparisons {

    public static void main(String[] args) {
        printComparisonMemoryAid();
        compareStudentArrays();
    }

    private static void compareStudentArrays() {
        Student[] first = {
                new Student("Amy", 80),
                new Student("Bella", 90)
        };

        // Alias: another variable pointing to the same array.
        Student[] alias = first;

        // A different array containing different Student objects with equal values.
        Student[] sameContents = {
                new Student("Amy", 80),
                new Student("Bella", 90)
        };

        // The Student at index 1 has a different score.
        Student[] differentContents = {
                new Student("Amy", 80),
                new Student("Bella", 95)
        };

        showComparison("First versus alias", first, alias);
        showComparison("First versus same contents", first, sameContents);
        showComparison("First versus different contents", first, differentContents);

        printHeading("Array identity versus Student identity");
        System.out.println("first == sameContents: " + (first == sameContents));
        System.out.println("first[0] == sameContents[0]: "
                + (first[0] == sameContents[0]));
        System.out.println("first[0].equals(sameContents[0]): "
                + first[0].equals(sameContents[0]));
        System.out.println("Different Student objects can still have equal values.");
    }

    private static void showComparison(String heading, Student[] first, Student[] second) {
        printHeading(heading);
        System.out.println("first  = " + Arrays.toString(first));
        System.out.println("second = " + Arrays.toString(second));
        System.out.println();
        System.out.println("1. ==              same array object?  " + (first == second));
        System.out.println("2. Arrays.equals   same contents?      "
                + Arrays.equals(first, second));
        System.out.println("3. Arrays.compare  which comes first?  "
                + Arrays.compare(first, second) + comparisonMeaning(first, second));
        System.out.println("4. Arrays.mismatch first difference?   "
                + Arrays.mismatch(first, second) + mismatchMeaning(first, second));
    }

    private static String comparisonMeaning(Student[] first, Student[] second) {
        int result = Arrays.compare(first, second);

        if (result == 0) {
            return " (0: tied)";
        }
        if (result < 0) {
            return " (negative: first comes before second)";
        }
        return " (positive: first comes after second)";
    }

    private static String mismatchMeaning(Student[] first, Student[] second) {
        int index = Arrays.mismatch(first, second);
        return index == -1 ? " (-1: no difference)" : " (different at this index)";
    }

    private static void printComparisonMemoryAid() {
        System.out.println("\n-----------------------------\n");
        System.out.println("Remember these four questions:\n");
        System.out.println("1. ==              Same array object?");
        System.out.println("2. Arrays.equals   Same contents?");
        System.out.println("3. Arrays.compare  Which array comes first?");
        System.out.println("4. Arrays.mismatch Where is the first difference?");
        System.out.println("\n-----------------------------");

    }

    private static void printHeading(String heading) {
        System.out.println("\n--- " + heading + " ---");
    }

    /**
     * Arrays.equals uses Student.equals, while Arrays.compare uses
     * Student.compareTo. Both methods therefore use name and score.
     */
    private static final class Student implements Comparable<Student> {
        private final String name;
        private final int score;

        private Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof Student student)) {
                return false;
            }
            return score == student.score && name.equals(student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }

        @Override
        public int compareTo(Student other) {
            int nameComparison = name.compareTo(other.name);
            if (nameComparison != 0) {
                return nameComparison;
            }
            return Integer.compare(score, other.score);
        }

        @Override
        public String toString() {
            return name + "=" + score;
        }
    }
}
