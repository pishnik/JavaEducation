package ru.java.karanin.temperature_converter.model;

public class TemperatureModelImpl implements TemperatureModel {
    public double convertTemperature(double temperature, TemperatureScale fromScale, TemperatureScale toScale) {
        return toScale.fromCelsius(fromScale.toCelsius(temperature));
    }
}