package ru.java.karanin.temperature_converter.view;

import ru.java.karanin.temperature_converter.model.TemperatureScale;

public interface TemperatureView {
    void start();

    void showResults(double resultTemperature);

    void showError(String errorMessage);

    double getTemperature();

    TemperatureScale getScaleFrom();

    TemperatureScale getScaleTo();

    void checkTemperatureValue();

    void setPresenterCallbacks(Runnable onConvertClicked, Runnable onTemperatureChange);
}
