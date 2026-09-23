package com.sree.basics.optional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class OptionalExamples {

    public static void main(String[] args) {
        List<Student> students = createStudents();
        printStudents(students);

//        demonstrateOrElseThrow(students);
//        demonstrateOrElse(students);
        demonstrateIsPresent(students);
        demonstrateMap(students);
    }

    private static void demonstrateMap(List<Student> students) {
        Optional<List<Student>> studentsBelowC = getStudents(
                students,
                student -> student.getScore() < 70
        );

        Optional<List<Student>> studentsNameStartingWithZ = getStudents(
                students,
                student -> student.getName().toLowerCase().startsWith("z")
        );

        Function<List<Student>, List<Integer>> getScores = matchingStudents ->
                matchingStudents.stream()
                        .map(Student::getScore)
                        .toList();

        runExample(
                "map - value present",
                studentsBelowC,
                optional -> optional.map(getScores)
        );

        runExample(
                "map - value absent",
                studentsNameStartingWithZ,
                optional -> optional.map(getScores)
        );
    }

    private static void demonstrateIsPresent(List<Student> students) {
        Optional<List<Student>> studentsBelowC = getStudents(
                students,
                student -> student.getScore() < 70
        );

        Optional<List<Student>> studentsNameStartingWithZ = getStudents(
                students,
                student -> student.getName().toLowerCase().startsWith("z")
        );

        studentsBelowC
                .ifPresent(students1 -> System.out.println("present"));

        studentsNameStartingWithZ
                .ifPresent(students1 -> System.out.println("present"));

        runExample(
                "isPresent - value present",
                studentsBelowC,
                Optional::isPresent
        );

        runExample(
                "isPresent - value absent",
                studentsNameStartingWithZ,
                Optional::isPresent
        );

    }

    private static void demonstrateOrElseThrow(List<Student> students) {
        Optional<List<Student>> studentsWithA = getStudents(
                students,
                student -> student.getScore() >= 90
        );
        Optional<List<Student>> studentsWithTwoPartNames = getStudents(
                students,
                student -> student.getName().split(" ").length > 1
        );

        runExample(
                "orElseThrow - value present",
                studentsWithA,
                Optional::orElseThrow
        );
        runExample(
                "orElseThrow - value absent",
                studentsWithTwoPartNames,
                Optional::orElseThrow
        );
        runExample(
                "orElseThrow - custom exception",
                studentsWithTwoPartNames,
                optional -> optional.orElseThrow(
                        () -> new IllegalArgumentException("No students found!")
                )
        );
    }

    private static void demonstrateOrElse(List<Student> students) {
        Student defaultStudent = new Student("Jon Doe", 0);
        Optional<List<Student>> studentsWithTwoPartNames = getStudents(
                students,
                student -> student.getName().split(" ").length > 1
        );

        runExample(
                "orElse - value absent",
                studentsWithTwoPartNames,
                optional -> optional.orElse(List.of(defaultStudent))
        );
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static <T, R> void runExample(
            String title,
            Optional<T> input,
            Function<Optional<T>, R> operation
    ) {
        System.out.println("--- " + title + " ---");
        System.out.println("input: " + input);

        try {
            R result = operation.apply(input);
            System.out.println("result: " + result);
        } catch (RuntimeException exception) {
            System.out.println(
                    "exception: " + exception.getClass().getSimpleName()
                            + " - " + exception.getMessage()
            );
        }

        System.out.println("--------------------------------\n");
    }

    private static Optional<List<Student>> getStudents(List<Student> students, Predicate<Student> condition) {
        return Optional.of(
                students.stream()
                        .filter(condition)
                        .toList()
        ).filter(matchingStudents -> !matchingStudents.isEmpty());
    }

    private static List<Student> createStudents() {
        return List.of(
                new Student("Amy", 90),
                new Student("Brenda", 85),
                new Student("Charlie", 95),
                new Student("David", 70),
                new Student("Desi", 70),
                new Student("Ester", 60)
        );
    }

    private static void printStudents(List<Student> students) {
        System.out.println("--- Students list ---");
        students.forEach(System.out::println);
        System.out.println("---------------------\n");
    }

    private static final class Student {
        private final String name;
        private final int score;

        private Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Student: "
                    + "name = " + name
                    + " and score = " + score;
        }
    }
}
