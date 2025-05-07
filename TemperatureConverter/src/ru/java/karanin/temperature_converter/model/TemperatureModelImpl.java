package ru.java.karanin.temperature_converter.model;

public class TemperatureModelImpl implements TemperatureModel {
    private double convertedTemperature;

    @Override
    public void convertTemperature(double temperature, TemperatureScale fromScale, TemperatureScale toScale) {
        convertedTemperature = toScale.fromCelsius(fromScale.toCelsius(temperature));
    }

    @Override
    public double getConvertedTemperature() {
        return convertedTemperature;
    }
}