package ru.java.karanin.temperature_converter_view;

import ru.java.karanin.temperature_converter_model.TemperatureScale;

public interface TemperatureView {
    void start();

    void showResults(double resultTemperature);

    void showError(String errorMessage);

    Double getTemperature();

    TemperatureScale getScaleFrom();

    TemperatureScale getScaleTo();

    void checkTemperatureValue();

    void setPresenterCallbacks(Runnable onConvertClicked, Runnable onTemperatureChange);
}
