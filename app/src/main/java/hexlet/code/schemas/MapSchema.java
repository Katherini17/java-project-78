package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema required() {
        Predicate<Map<K, V>> testRequired = value -> value != null;
        addCondition("required", testRequired);

        return this;
    }

    public MapSchema sizeOf(int size) {
        Predicate<Map<K, V>> testSizeOf = value -> value.size() == size;
        addCondition("sizeOf", testSizeOf);

        return this;
    }

    public MapSchema shape(Map<K, BaseSchema<V>>  schemas) {
        Predicate<Map<K, V>> testShape = complicatedMap -> {
            return schemas.keySet()
                    .stream()
                    .map(key -> {

                        V value = complicatedMap.get(key);
                        var schema = schemas.get(key);

                        return schema.isValid(value);
                    })
                    .allMatch(value -> value);
        };

        addCondition("shape", testShape);

        return this;
    }
}
