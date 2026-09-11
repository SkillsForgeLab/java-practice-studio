package com.sree.basics.dataTypes.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public enum DayOfWeekEnum {
    // Enum constants are implicitly public, static, and final.
    MONDAY("MON", 1, false, "Back to work!"),
    TUESDAY("TUE"),
    WEDNESDAY("WED", 3),
    THURSDAY("THUR", 4),
    FRIDAY("FRI", 5, false, "Time to start partying!"),
    SATURDAY("SAT", 6, true),
    SUNDAY("SUN", 7, true);
    // The semicolon is required because fields, constructors, and methods follow.

    private final String label;
    private final int dayOfWeek;
    private final boolean isWeekend;
    private final String funFact;

    static {
        InitializationLog.print("STATIC INITIALIZER",
                "All enum constants have now been constructed.");
    }

    {
        InitializationLog.print("INSTANCE INITIALIZER",
                "Initializing " + name() + " (declared at ordinal " + ordinal() + ").");
    }

    // Enum constructors are implicitly private.
    DayOfWeekEnum(String label) {
        // This one-argument overload is used by TUESDAY and supplies its remaining data.
        this(label, 2, false, "Almost halfway there!");
        InitializationLog.print("CONSTRUCTOR: 1 ARGUMENT",
                "Finished the convenience constructor for " + name() + ".");
    }

    DayOfWeekEnum(String label, int dayOfWeek) {
        this(label, dayOfWeek, false, "No fun fact provided.");
        InitializationLog.print("CONSTRUCTOR: 2 ARGUMENTS",
                "Finished the convenience constructor for " + name() + ".");
    }

    DayOfWeekEnum(String label, int dayOfWeek, boolean isWeekend) {
        this(label, dayOfWeek, isWeekend, "No fun fact provided.");
        InitializationLog.print("CONSTRUCTOR: 3 ARGUMENTS",
                "Finished the convenience constructor for " + name() + ".");
    }

    DayOfWeekEnum(String label, int dayOfWeek, boolean isWeekend, String funFact) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        this.dayOfWeek = dayOfWeek;
        this.isWeekend = isWeekend;
        this.funFact = Objects.requireNonNull(funFact, "funFact must not be null");

        InitializationLog.print("CONSTRUCTOR: 4 ARGUMENTS",
                "Assigned all fields for " + name() + ".");
    }

    public String getLabel() {
        return label;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public String getFunFact() {
        return funFact;
    }

    /**
     * Type 1: the caller supplies any condition that can be tested against a day.
     */
    public static List<DayOfWeekEnum> findBy(Predicate<DayOfWeekEnum> condition) {
        Objects.requireNonNull(condition, "condition must not be null");

        return Arrays.stream(values())
                .filter(condition)
                .toList();
    }

    /**
     * Type 2: the field name is selected with an enum, so misspelled or unsupported
     * field names are rejected by the compiler. The value type is checked at runtime.
     */
    public static List<DayOfWeekEnum> findBy(SearchField field, Object value) {
        Objects.requireNonNull(field, "field must not be null");
        field.validateValue(value);

        return findBy(day -> field.matches(day, value));
    }

    /**
     * Type 3: useful when a field name comes from text input, configuration, or an API.
     */
    public static List<DayOfWeekEnum> findBy(String fieldName, Object value) {
        return findBy(SearchField.fromExternalName(fieldName), value);
    }

    public static Optional<DayOfWeekEnum> byLabel(String label) {
        for (DayOfWeekEnum day : values()) {
            if (day.label.equals(label)) {
                return Optional.of(day);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        InitializationLog.print("MAIN", "Starting the enum usage examples.");

        System.out.println("\nBuilt-in enum features:");
        System.out.println("- name(): " + MONDAY.name());
        System.out.println("- ordinal(): " + MONDAY.ordinal()
                + " (zero-based declaration position, not a business value)");
        System.out.println("- valueOf(): " + DayOfWeekEnum.valueOf("MONDAY"));
        System.out.println("- values():");
        for (DayOfWeekEnum day : values()) {
            System.out.println("  " + day.name() + " -> " + day.label);
        }

        DayOfWeekEnum monday = byLabel("MON")
                .orElseThrow(() -> new IllegalArgumentException("Unknown day label: MON"));

        System.out.println("\nCustom data and lookup:");
        System.out.println(monday.name() + " is day " + monday.getDayOfWeek() + ".");
        System.out.println(monday.name() + " is a weekend: " + monday.isWeekend());
        System.out.println(monday.name() + " fun fact: " + monday.getFunFact());
        System.out.println("Unknown label lookup: " + byLabel("INVALID"));

        System.out.println("\nThree custom lookup styles:");
        System.out.println("1. Predicate: " + findBy(day -> day.isWeekend()));
        System.out.println("2. Enum field: " + findBy(SearchField.DAY_OF_WEEK, 3));
        System.out.println("3. String field: " + findBy("label", "MON"));
    }

    public enum SearchField {
        LABEL("label", String.class),
        DAY_OF_WEEK("dayOfWeek", Integer.class),
        IS_WEEKEND("isWeekend", Boolean.class),
        FUN_FACT("funFact", String.class);

        private final String externalName;
        private final Class<?> valueType;

        SearchField(String externalName, Class<?> valueType) {
            this.externalName = externalName;
            this.valueType = valueType;
        }

        private boolean matches(DayOfWeekEnum day, Object value) {
            return switch (this) {
                case LABEL -> day.label.equals(value);
                case DAY_OF_WEEK -> day.dayOfWeek == (Integer) value;
                case IS_WEEKEND -> day.isWeekend == (Boolean) value;
                case FUN_FACT -> day.funFact.equals(value);
            };
        }

        private void validateValue(Object value) {
            if (!valueType.isInstance(value)) {
                String actualType = value == null ? "null" : value.getClass().getSimpleName();
                throw new IllegalArgumentException(
                        externalName + " requires " + valueType.getSimpleName()
                                + " but received " + actualType);
            }
        }

        private static SearchField fromExternalName(String fieldName) {
            Objects.requireNonNull(fieldName, "fieldName must not be null");

            for (SearchField field : values()) {
                if (field.externalName.equals(fieldName)) {
                    return field;
                }
            }
            throw new IllegalArgumentException("Unknown search field: " + fieldName);
        }
    }

    /**
     * Kept in a nested class so its counter can be initialized safely while the enum
     * constants themselves are still being constructed.
     */
    private static final class InitializationLog {
        private static int nextSequenceNumber = 1;

        private static void print(String stage, String message) {
            System.out.printf("%02d. [%s] %s%n", nextSequenceNumber++, stage, message);
        }
    }
}
