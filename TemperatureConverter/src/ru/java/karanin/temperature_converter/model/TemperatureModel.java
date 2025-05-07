package ru.java.karanin.temperature_converter.model;

public interface TemperatureModel {
    void convertTemperature(double temperature, TemperatureScale fromScale, TemperatureScale toScale);

    double getConvertedTemperature();
}
