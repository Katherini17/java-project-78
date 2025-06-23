package hexlet.code.schemas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> conditions;

    public BaseSchema() {
        conditions = new HashMap<>();
    }

    public boolean isValid(T obj) {
        Collection<Predicate<T>> predicates = conditions.values();

        for (Predicate<T> predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }

        return true;
    }

    public void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }

}
