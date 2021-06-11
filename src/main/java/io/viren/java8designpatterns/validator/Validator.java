package io.viren.java8designpatterns.validator;

import io.viren.java8designpatterns.model.Person;

import java.util.function.Predicate;
import java.util.function.Supplier;

public interface Validator {

    PersonSupplier on(Person person);

    default Validator thenValidate(Predicate<Person> function, String errorMessage) {
        return p -> {
            try {
                on(p).validate();
                return validateInternal(function, errorMessage, p);
            } catch (ValidationException validationException) {
                if (!function.test(p)) {
                    validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                }
                return () -> {
                    throw validationException;
                };
            }
        };
    }

    private static PersonSupplier validateInternal(Predicate<Person> function, String errorMessage, Person p) {
        if (function.test(p)) {
            return () -> p;
        } else {
            return () -> {
                ValidationException validationException = new ValidationException("This object is not valid.");
                validationException.addSuppressed(new IllegalArgumentException(errorMessage));
                throw validationException;
            };
        }
    }

    static Validator validate(Predicate<Person> function, String errorMessage) {
        return p -> validateInternal(function, errorMessage, p);
    }

    interface PersonSupplier extends Supplier<Person> {
        default Person validate() {
            return get();
        }
    }

    class ValidationException extends RuntimeException {
        public ValidationException(String errorMessage) {
            super(errorMessage);
        }
    }
}
