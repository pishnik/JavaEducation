package ru.java.karanin.temperature_converter_model;

public interface TemperatureModel {
    void convertTemperature(double temperature, TemperatureScale fromScale, TemperatureScale toScale);

    double getConvertedTemperature();
}
