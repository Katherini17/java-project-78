package hexlet.code.schemas;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
public class NumberSchema {
    private boolean isRequired;
    private boolean isPositive;
    private Map<String, Integer> numberRange;

    public NumberSchema() {
        isRequired = false;
        isPositive = false;
        numberRange = new HashMap<>(Map.of(
                "min", Integer.MIN_VALUE,
                "max", Integer.MAX_VALUE
        ));
    }

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        setPositive(true);
        return this;
    }

    public NumberSchema range(int min, int max) {
        numberRange.put("min", min);
        numberRange.put("max", max);
        return this;
    }

    public boolean isValid(Integer number) {

        if (number == null) {
            return !isRequired;
        }

        boolean checkPositive = true;
        if (isPositive) {
            checkPositive = number > 0;
        }

        return checkPositive
                && number > numberRange.get("min")
                && number < numberRange.get("max");
    }
}