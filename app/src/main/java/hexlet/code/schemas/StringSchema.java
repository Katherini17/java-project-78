package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> testRequired = value -> {
            return value != null && !value.isEmpty();
        };
        addCondition("required", testRequired);

        return this;
    }

    public StringSchema minLength(int minLength) {
        Predicate<String> testMinLength = text -> {
            return text != null && text.length() >= minLength;
        };
        addCondition("minLength", testMinLength);

        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> testContains = text -> {
            return text != null && text.contains(substring);
        };

        addCondition("contains", testContains);

        return this;
    }
}
