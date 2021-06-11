package io.viren.java8designpatterns.validator;

import io.viren.java8designpatterns.model.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void test_validator() {

        Person ramesh = new Person("Ramesh", 25);
        Person mahesh = new Person("Mahesh", 1_000);
        Person jignesh = new Person(null, 1_000);


        assertDoesNotThrow(() -> Validator.validate(p -> null != p.getName(), "name should not be null")
                .on(ramesh)
                .validate());
        assertThrows(Validator.ValidationException.class, () -> Validator.validate(p -> null != p.getName(), "name should not be null")
                .thenValidate(p -> p.getAge() > 0, "age should be positive")
                .thenValidate(p -> p.getAge() <= 150, "age can't be beyond 150")
                .on(mahesh)
                .validate(), "This object is not valid");
        assertThrows(Validator.ValidationException.class, () -> Validator.validate(p -> null != p.getName(), "name should not be null")
                .thenValidate(p -> p.getAge() > 0, "age should be positive")
                .thenValidate(p -> p.getAge() <= 150, "age can't be beyond 150")
                .on(jignesh)
                .validate(), "This object is not valid");
    }
}