package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        String exampleText1 = "Example text";
        String exampleText2 = "Text";
        String exampleText3 = "The longest example";
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
        assertFalse(schema.isValid(negativeNumber2));

        schema.required();

        assertFalse(schema.isValid(null));
    }
}
