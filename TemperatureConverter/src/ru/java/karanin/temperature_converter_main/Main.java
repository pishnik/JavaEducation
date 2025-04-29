package ru.java.karanin.temperature_converter_main;

import ru.java.karanin.temperature_converter_model.TemperatureModel;
import ru.java.karanin.temperature_converter_model.TemperatureModelImpl;
import ru.java.karanin.temperature_converter_presenter.TemperaturePresenter;
import ru.java.karanin.temperature_converter_presenter.TemperaturePresenterImpl;
import ru.java.karanin.temperature_converter_view.TemperatureDesktopView;
import ru.java.karanin.temperature_converter_view.TemperatureView;

public class Main {
    public static void main(String[] args) {
        TemperatureModel temperatureModel = new TemperatureModelImpl();
        TemperatureView temperatureView = new TemperatureDesktopView();
        TemperaturePresenter temperaturePresenter = new TemperaturePresenterImpl(temperatureModel, temperatureView);
        temperaturePresenter.start();
    }
}
