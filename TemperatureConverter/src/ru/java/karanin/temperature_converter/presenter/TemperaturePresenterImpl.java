package ru.java.karanin.temperature_converter.presenter;

import ru.java.karanin.temperature_converter.model.TemperatureModel;
import ru.java.karanin.temperature_converter.model.TemperatureScale;
import ru.java.karanin.temperature_converter.view.TemperatureView;

public class TemperaturePresenterImpl implements TemperaturePresenter {
    private final TemperatureModel model;
    private final TemperatureView view;

    public TemperaturePresenterImpl(TemperatureModel model, TemperatureView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void start() {
        view.start();
        view.setPresenterCallbacks(this::onConvertClicked, this::onTemperatureChange);
    }

    private void onConvertClicked() {
        try {
            TemperatureScale scaleFrom = view.getScaleFrom();
            TemperatureScale scaleTo = view.getScaleTo();

            double inputTemperature = view.getTemperature();

            model.convertTemperature(inputTemperature, scaleFrom, scaleTo);

            view.showResults(model.getConvertedTemperature());
        } catch (NumberFormatException e) {
            view.showError("Значение температуры должно быть числом!");
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void onTemperatureChange() {
        view.checkTemperatureValue();
    }
}