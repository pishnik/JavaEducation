package ru.java.karanin.temperature_converter.model;

public interface TemperatureModel {
    double convertTemperature(double temperature, TemperatureScale fromScale, TemperatureScale toScale);
}
