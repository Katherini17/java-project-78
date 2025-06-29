# Validator

Validator - `Validator` is a Java library for data validation.
It provides a simple and flexible way to define validation schemas and check data against those schemas.
The library supports various data types, including strings, numbers, and Map<K, V>, and allows you to define complex validation rules.

## Technologies

*   Java
*   Gradle
*   JUnit 5

## Prerequisites

*   Java Development Kit (JDK) 17 or higher.
*   Gradle.

## Usage (Examples)

### String Validation

```java
import hexlet.code.Validator;
import hexlet.code.StringSchema;

Validator validator = new Validator();

// The string() method creates a StringSchema.
StringSchema schema = validator.string();

schema.isValid("East or West - home is best"); // true
schema.isValid(null); // true

// required() makes data mandatory. 
// It adds a constraint to the schema that prevents the use of null or an empty string as a value
schema.required();

schema.isValid(null);  // false
schema.isValid("");    // false

// minLength(int min) — adds a minimum length constraint for a string to the schema. 
// The string must be equal to or longer than the specified number

schema.minLength(5);
schema.isValid("East"); // false
schema.isValid("East or West - home is best"); // true

// contains() — adds a constraint on the contents of a string to the schema.
// The string must contain a specific substring

schema.contains("East");
schema.isValid("home is best"); // false
schema.isValid("East or West - home is best"); // true
```
### Number Validation

```java
import hexlet.code.Validator;
import hexlet.code.NumberSchema;

Validator validator = new Validator();

// The number() method creates a NumberSchema.
NumberSchema schema = validator.number();

schema.isValid(5); // true
schema.isValid(-5); // true
schema.isValid(null); // true

// required() adds a constraint to the schema that prevents null values from being used.
schema.required();

schema.isValid(null);  // false

// positive() — adds a restriction on the sign of a number. 
// The number must be positive

schema.positive();
schema.isValid(-5); // false
schema.isValid(0); // false

// range(int min, int max) — adds a valid range that the number value must fall within, including the boundaries

schema.range(10, 20);
schema.isValid(5); // false
schema.isValid(15); // true
schema.isValid(25); // false
```
### Number Validation

```java
import hexlet.code.Validator;
import hexlet.code.MapSchema;

import java.util.HashMap;
import java.util.Map;

Validator validator = new Validator();

// The map() method creates a MapSchema<K, V>.
MapSchema<String, String> schema = validator.map();

Map<String, String> data = new HashMap<>(Map.of(
        "key1", "value1",
        "key2", "value2"
));

schema.isValid(data); // true
schema.isValid(new HashMap<>()); // true
schema.isValid(null); // true        
        

// required() adds a constraint to the schema that prevents null values from being used.
schema.required();

schema.isValid(null); // false

// sizeof() — adds a limit to the size of the map. 
// The number of key-value pairs in the Map object must be equal to the specified value

schema.sizeOf(3);

schema.isValid(false); // false

data.put("key3", "value3");
schema.isValid(data); // true

// The shape(Map<K, BaseSchema<V>>  schemas) method is used to define the properties of a Map object and create a schema for validating their values. Each property of a Map object has its own set of constraints, allowing for more precise control over the data.
MapSchema shapeSchema = validator.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>(Map.of(
        "key1", validator.string().required(),
        "key2", validator.string().minLength(10);
        "key3", validator.string().contains("East");
));

shapeSchema.shape(schemas);

shapeSchema.isValid(data); // false

data.put("key2", "Practice makes perfect")
data.put("key3", "East or West - home is best");

shapeSchema.isValid(data); // true
```

### Hexlet tests and linter status:
[![Actions Status](https://github.com/Katherini17/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/Katherini17/java-project-78/actions)
### Github actions status:
[![Java CI with Gradle](https://github.com/Katherini17/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/Katherini17/java-project-78/actions/workflows/main.yml)
### Quality Gate Status:
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Katherini17_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Katherini17_java-project-78)
### Bugs:
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Katherini17_java-project-78&metric=bugs)](https://sonarcloud.io/summary/new_code?id=Katherini17_java-project-78)
### Code smell:
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=Katherini17_java-project-78&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=Katherini17_java-project-78)
### Coverage:
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Katherini17_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Katherini17_java-project-78)
### Duplicated lines:
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=Katherini17_java-project-78&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=Katherini17_java-project-78)