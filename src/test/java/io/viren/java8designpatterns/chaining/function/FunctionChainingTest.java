package io.viren.java8designpatterns.chaining.function;

import io.viren.java8designpatterns.util.Function;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

import static org.assertj.core.api.Assertions.assertThat;

public class FunctionChainingTest {

    @Test
    void test_function_chaining() {

        Function<Thermostat, Float> readTemperatureInCelsius = Thermostat::getTemperature;
        Function<Float, Double> convertCelsiusToFahrenheit = celsius -> (celsius * 9d / 5d) + 32d;

        Function<Thermostat, Double> inFahrenheit = readTemperatureInCelsius.andThen(convertCelsiusToFahrenheit);
        assertThat(inFahrenheit.apply(new Thermostat(98.0f))).isEqualTo(208.4);
    }

    @Test
    void test_function_compose() {
        Function<Thermostat, Float> readTemperatureInCelsius = Thermostat::getTemperature;
        Function<Float, Double> convertCelsiusToFahrenheit = celsius -> (celsius * 9d / 5d) + 32d;

        Function<Thermostat, Double> inFahrenheit = convertCelsiusToFahrenheit.compose(readTemperatureInCelsius);
        assertThat(inFahrenheit.apply(new Thermostat(98.0f))).isEqualTo(208.4);
    }
}
