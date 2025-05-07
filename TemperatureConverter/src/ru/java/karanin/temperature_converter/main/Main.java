package ru.java.karanin.temperature_converter.main;

import ru.java.karanin.temperature_converter.model.TemperatureModel;
import ru.java.karanin.temperature_converter.model.TemperatureModelImpl;
import ru.java.karanin.temperature_converter.presenter.TemperaturePresenter;
import ru.java.karanin.temperature_converter.presenter.TemperaturePresenterImpl;
import ru.java.karanin.temperature_converter.view.TemperatureDesktopView;
import ru.java.karanin.temperature_converter.view.TemperatureView;

public class Main {
    public static void main(String[] args) {
        TemperatureModel temperatureModel = new TemperatureModelImpl();
        TemperatureView temperatureView = new TemperatureDesktopView();
        TemperaturePresenter temperaturePresenter = new TemperaturePresenterImpl(temperatureModel, temperatureView);
        temperaturePresenter.start();
    }
}
