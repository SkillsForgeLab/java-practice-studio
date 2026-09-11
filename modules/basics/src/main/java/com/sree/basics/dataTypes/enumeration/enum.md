# Java enum guide

[`DayOfWeekEnum.java`](DayOfWeekEnum.java) demonstrates how a Java `enum` can
contain fields, overloaded constructors, instance and static initializers, and
methods in addition to its constants.

## Declaring enum constants

Each constant is a single instance of `DayOfWeekEnum`:

```java
MONDAY("MON", 1, false, "Back to work!"),
TUESDAY("TUE"),
WEDNESDAY("WED", 3),
SATURDAY("SAT", 6, true);
```

Enum constants are implicitly `public`, `static`, and `final`. They cannot be
replaced with new instances after the enum class has been initialized.

The constants may supply different numbers of arguments when the enum declares
matching overloaded constructors. In this example:

- `TUESDAY` selects the one-argument constructor.
- `WEDNESDAY` and `THURSDAY` select the two-argument constructor.
- `SATURDAY` and `SUNDAY` select the three-argument constructor.
- `MONDAY` and `FRIDAY` select the four-argument constructor.

The shorter constructors delegate to the four-argument constructor and provide
the omitted values. As a result, every constant receives complete data while
the example still demonstrates constructor overloading.

The semicolon after the last constant is required when fields, constructors, or
methods follow the constant list.

## Initialization and execution order

Running `DayOfWeekEnum.main()` produces numbered log messages. The numbers are
generated at runtime so they show the actual order in which the logged code
executes.

When the enum class is first used, Java performs these steps:

1. It creates the enum constants in their declaration order, beginning with
   `MONDAY` and ending with `SUNDAY`.
2. For each constant, the instance initializer executes before the body of the
   constructor that ultimately initializes the fields.
3. If a shorter constructor delegates with `this(...)`, the delegated
   constructor completes before the body of the shorter constructor resumes.
4. After every enum constant has been constructed, the enum's static
   initializer executes.
5. The `main()` method then executes.

For example, `TUESDAY("TUE")` follows this visible sequence:

```text
[INSTANCE INITIALIZER] Initializing TUESDAY ...
[CONSTRUCTOR: 4 ARGUMENTS] Assigned all fields for TUESDAY.
[CONSTRUCTOR: 1 ARGUMENT] Finished the convenience constructor for TUESDAY.
```

The instance initializer therefore runs before the constructor bodies for each
constant. It should not be described as running before everything in the enum:
the enum's class initialization has already started, and Java performs some
implicit object-construction work that is not represented by these log
statements.

The static initializer appears after the constant-construction messages because
enum constants are static fields declared before that initializer. Java
initializes static fields and static initializer blocks in textual order.

## Constructor chaining

An enum constructor cannot be called directly from application code. Enum
constructors are implicitly `private`, even when the `private` keyword is
omitted.

The shorter constructors use `this(...)` to delegate to the complete
four-argument constructor:

```java
DayOfWeekEnum(String label, int dayOfWeek, boolean isWeekend) {
    this(label, dayOfWeek, isWeekend, "No fun fact provided.");
    // This body continues after the four-argument constructor completes.
}
```

This pattern centralizes field assignment and validation. It also allows each
field to remain `final`, making every enum constant immutable after
construction.

## Built-in enum methods

All Java enums inherit useful methods from `java.lang.Enum` and receive two
compiler-generated methods.

### `name()`

Returns the exact identifier used in the declaration:

```java
MONDAY.name(); // "MONDAY"
```

This differs from the custom label `"MON"` stored by the example.

### `valueOf(String)`

Looks up a constant by its exact, case-sensitive declaration name:

```java
DayOfWeekEnum.valueOf("MONDAY"); // MONDAY
```

It does not search custom labels, so `valueOf("MON")` throws an
`IllegalArgumentException`.

### `values()`

Returns all constants in declaration order:

```java
for (DayOfWeekEnum day : DayOfWeekEnum.values()) {
    System.out.println(day.name());
}
```

### `ordinal()`

Returns a constant's zero-based declaration position:

```java
MONDAY.ordinal();    // 0
TUESDAY.ordinal();   // 1
WEDNESDAY.ordinal(); // 2
```

An ordinal is a position, not a business or domain value. A business value has
meaning to the application—for example, Monday's ISO day number is `1`.

Declaration positions are fragile. Inserting or rearranging constants changes
their ordinals:

```java
HOLIDAY,
MONDAY,
TUESDAY
```

After this change, `MONDAY.ordinal()` is `1` rather than `0`. Code that stored
the old ordinal in a database or file could now interpret it as a different
constant.

Therefore, do not use `ordinal()` as:

- a database value or persistent identifier;
- a day number or other domain-specific number;
- a value exchanged through an API;
- array or list data that must remain compatible after the enum changes.

Use a dedicated field when a stable value is required:

```java
MONDAY.getDayOfWeek(); // 1
MONDAY.getLabel();     // "MON"
```

Use `ordinal()` only when the current declaration position is specifically what
the code needs.

## Custom lookup with `Optional`

The example's `byLabel` method searches the custom labels:

```java
Optional<DayOfWeekEnum> result = DayOfWeekEnum.byLabel("MON");
```

It returns `Optional.empty()` for an unknown label instead of returning `null`.
The caller must then choose how to handle a missing result:

```java
DayOfWeekEnum monday = DayOfWeekEnum.byLabel("MON")
        .orElseThrow(() -> new IllegalArgumentException("Unknown label"));
```

This is safer than relying on `assert` for input validation. Java assertions
are disabled by default and should primarily be used to check internal
assumptions during development, not to validate values required for normal
program execution.

## Three general lookup styles

All three general-purpose `findBy` methods return a `List<DayOfWeekEnum>`.
Returning a list is important because some searches can match several
constants. For example, both `SATURDAY` and `SUNDAY` match `isWeekend == true`.

### 1. Predicate-based lookup

A `Predicate<DayOfWeekEnum>` examines a constant and returns `true` when that
constant should be included in the result:

```java
List<DayOfWeekEnum> weekendDays = DayOfWeekEnum.findBy(
        day -> day.isWeekend());

List<DayOfWeekEnum> daysAfterWednesday = DayOfWeekEnum.findBy(
        day -> day.getDayOfWeek() > 3);
```

This is the most flexible and type-safe option because the compiler checks the
methods and comparisons used by the predicate. It is a good choice when the
condition is written directly in Java code.

### 2. Enum-field lookup

`SearchField` restricts the field selector to the supported choices:

```java
List<DayOfWeekEnum> result = DayOfWeekEnum.findBy(
        DayOfWeekEnum.SearchField.DAY_OF_WEEK, 3);
```

The compiler rejects a misspelled or nonexistent enum constant such as
`SearchField.DAYOFWEEK`. The lookup also checks the value type at runtime. For
example, `DAY_OF_WEEK` requires an `Integer`, while `IS_WEEKEND` requires a
`Boolean`. Passing `"3"` instead of `3` produces a descriptive exception.

This makes the field selection compile-time-safe, although the value is an
`Object` and therefore requires runtime validation. Java enums cannot have a
different generic value type for each individual constant.

The supported selectors are:

| Selector | Field name | Required value type |
| --- | --- | --- |
| `LABEL` | `label` | `String` |
| `DAY_OF_WEEK` | `dayOfWeek` | `Integer` |
| `IS_WEEKEND` | `isWeekend` | `Boolean` |
| `FUN_FACT` | `funFact` | `String` |

### 3. String-field lookup

The string overload supports field names received from user input,
configuration, or an external API:

```java
List<DayOfWeekEnum> result = DayOfWeekEnum.findBy("label", "MON");
```

Supported names are `"label"`, `"dayOfWeek"`, `"isWeekend"`, and
`"funFact"`. The names are exact and case-sensitive. An unknown field name or
incorrect value type produces an `IllegalArgumentException` with a descriptive
message.

The string form is flexible but does not provide compile-time checking. A typo
such as `"dayOfWek"` compiles successfully and is discovered only when the code
runs. Prefer the predicate or `SearchField` form when the field is known while
writing the Java code.

All three overloads eventually use the predicate-based implementation. This
keeps the iteration logic in one place and gives every lookup style consistent
behavior.

## Enum design guidelines illustrated here

- Keep enum state immutable with `private final` fields.
- Validate constructor arguments when the constants are created.
- Expose data through clearly named accessor methods.
- Use explicit stable fields for domain values instead of `ordinal()`.
- Use `valueOf()` for declaration names and a custom method for custom labels.
- Handle unsuccessful custom lookups explicitly rather than returning `null`.
