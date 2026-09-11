package com.sree.basics.dataTypes.enumeration;

import java.util.Objects;

    public enum DayOfWeekEnum {
        MONDAY("MON", 1, false ,"Back to work!"),
        TUESDAY("TUE"),
        WEDNESDAY("WED", 3),
        THURSDAY("THUR", 4),
        FRIDAY("FRI", 5, false,"Time to start partying!"),
        SATURDAY("SAT", 6, true),
        SUNDAY("SUN", 7, true);

        private final String label;
        private int dayOfWeek;
        private boolean isWeekEnd;
        private String funFact;

        static {
            System.out.println("\n5. Executing static initializer!");
        }


        {
            System.out.println("\n1. Executing instance initializer!");
        }


        DayOfWeekEnum(String label) {
            this.label = label;

            System.out.println("2A. Executing constructor for "
                    + "name: " + label
                    + "!");
        }

        DayOfWeekEnum(String label, int dayOfWeek) {
            this.label = label;
            this.dayOfWeek = dayOfWeek;

            System.out.println("2B. Executing constructor for "
                    + "name: " + label
                    + " and dayOfWeek " + dayOfWeek
                    + "!");
        }

        DayOfWeekEnum(String label, int dayOfWeek, boolean isWeekEnd) {
            this(label, dayOfWeek);
            this.isWeekEnd = isWeekEnd;

            System.out.println("3. Executing constructor for "
                    + "label: " + label
                    + ", dayOfWeek " + dayOfWeek
                    + " and isWeekEnd " + isWeekEnd
                    + "!");
        }

        DayOfWeekEnum(String label, int dayOfWeek, boolean isWeekEnd,  String funFact) {
            this(label, dayOfWeek, isWeekEnd);
            this.funFact = Objects.requireNonNullElse(funFact, "");

            System.out.println("4. Executing constructor for "
                    + "label: " + label
                    + ", dayOfWeek " + dayOfWeek
                    + ", isWeekEnd " + isWeekEnd
                    + " and funFact " + funFact
                    + "!");
        }


        public static DayOfWeekEnum byLabel(String label) {
            for (DayOfWeekEnum e : values()) {
                if (e.label.equals(label))
                    return e;
            }
            return null;
        }

        public static void main(String[] args) {
            System.out.println("\n6. Executing main!");

            DayOfWeekEnum monday = byLabel("MON");
            assert monday != null;
            System.out.println("7. (from main) Monday dayInWeek: " + monday.dayOfWeek);
            System.out.println("8. (from main) Monday is: " + monday.isWeekEnd);
        }
}
