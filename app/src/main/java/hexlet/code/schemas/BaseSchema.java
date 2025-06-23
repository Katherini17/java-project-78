package hexlet.code.schemas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private Set<Predicate<T>> conditions = new HashSet<>();

    public boolean isValid(T obj) {
        for (Predicate<T> condition : conditions) {
            if (!condition.test(obj)) {
                return false;
            }
        }
        return true;
    }

    public void addCondition(Predicate<T> condition) {
        conditions.add(condition);
    }
}
