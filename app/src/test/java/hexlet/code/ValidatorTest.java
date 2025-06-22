package hexlet.code;

import hexlet.code.schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    public void testStringValidator() {
        Validator validator = new Validator();
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

        assertFalse(schema.isValid(exampleText2));
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
}
