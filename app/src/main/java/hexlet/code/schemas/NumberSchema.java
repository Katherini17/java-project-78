package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> testRequired = value -> value != null;
        addCondition("required", testRequired);

        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> testPositive = value -> value == null || value > 0;
        addCondition("posotive", testPositive);

        return this;
    }

    public NumberSchema range(int min, int max) {
        Predicate<Integer> testRange = value -> {
            return value == null
                    || value >= min
                    && value <= max;
        };
        addCondition("range", testRange);

        return this;
    }

}
