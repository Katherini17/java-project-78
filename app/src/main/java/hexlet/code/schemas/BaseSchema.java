package hexlet.code.schemas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Base class for all schemas. Provides basic validation functionality.
 *
 * @param <T> The type of the value to be validated.
 */
public class BaseSchema<T> {
    private final Map<String, Predicate<T>> conditions;

    public BaseSchema() {
        conditions = new HashMap<>();
    }

    /**
     * Checks if the given value is valid according to the schema's defined conditions.
     *
     * @param obj The value to validate.
     * @return True if the value is valid, false otherwise.
     */
    public final boolean isValid(T obj) {
        Collection<Predicate<T>> predicates = conditions.values();

        for (Predicate<T> predicate : predicates) {
            if (!predicate.test(obj)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds a validation condition to the schema.
     *
     * @param name The name of the condition.
     * @param condition The validation condition to add.
     */
    protected final void addCondition(String name, Predicate<T> condition) {
        conditions.put(name, condition);
    }

}
