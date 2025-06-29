package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void prepareValidator() {
        validator = new Validator();
    }

    @Test
    public void testStringValidator() {
        StringSchema schema = validator.string();

        String emptyText = "";
        String exampleText1 = "example text";
        String exampleText2 = "text";
        String exampleText3 = "the longest text";
        String subString1 = "example";
        String subString2 = "longest";

        assertTrue(schema.isValid(emptyText));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(exampleText1));

        schema.required();

        assertFalse(schema.isValid(emptyText));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(exampleText1));

        schema.minLength(5);

        assertFalse(schema.isValid(exampleText2));
        assertTrue(schema.isValid(exampleText1));

        schema.contains(subString1);

        assertFalse(schema.isValid(exampleText2));
        assertTrue(schema.isValid(exampleText1));


        schema.minLength(14).contains(subString2);

        assertFalse(schema.isValid(exampleText1));
        assertTrue(schema.isValid(exampleText3));
    }

    @Test
    public void testNumberValidator() {
        int positiveNumber1 = 5;
        int positiveNumber2 = 200;

        int negativeNumber1 = -7;
        int negativeNumber2 = -3;

        int min = -4;
        int max = 150;

        NumberSchema schema = validator.number();

        assertTrue(schema.isValid(positiveNumber1));
        assertTrue(schema.isValid(negativeNumber1));
        assertTrue(schema.isValid(null));

        schema.positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(positiveNumber1));
        assertFalse(schema.isValid(negativeNumber1));

        schema.range(min, max);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(positiveNumber1));
        assertFalse(schema.isValid(positiveNumber2));
        assertFalse(schema.isValid(negativeNumber2));

        schema.required();

        assertFalse(schema.isValid(null));
    }

    @Test
    public void testSimpleMapValidator() {
        MapSchema<String, Object> schema = validator.map();

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, Object> data = new HashMap<>();
        data.put("key1", null);

        assertTrue(schema.isValid(data));

        schema.sizeOf(2);

        assertFalse(schema.isValid(data));

        data.put("key2", 5);

        assertTrue(schema.isValid(data));
    }

    @Test
    public void testShape() {
        String key1 = "key1";
        String key2 = "key2";

        MapSchema<String, String> schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put(key1, validator.string().required());
        schemas.put(key2, validator.string().required().minLength(5));

        Map<String, String> data = new HashMap<>(Map.of(
                key1, "value1",
                key2, "value2"
        ));
        schema.shape((schemas));
        assertTrue(schema.isValid(data));

        data.put(key2, null);

        assertFalse(schema.isValid(data));
    }
}



