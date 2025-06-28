package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<Object, Object>> {

    public MapSchema required() {
        Predicate<Map<Object, Object>> testRequired = value -> value != null;
        addCondition("required", testRequired);

        return this;
    }

    public MapSchema sizeOf(int size) {
        Predicate<Map<Object, Object>> testSizeOf = value -> value.size() >= size;
        addCondition("sizeOf", testSizeOf);

        return this;
    }
}
