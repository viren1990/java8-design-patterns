package io.viren.java8designpatterns.chaining.function;

import java.io.Serializable;

public class Thermostat implements Serializable, Comparable<Thermostat> {

    private final float temperature;

    public Thermostat(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Thermostat{" +
                "temperature=" + temperature +
                '}';
    }

    @Override
    public int compareTo(Thermostat o) {
        return Float.compare(this.temperature, o.temperature);
    }
}
