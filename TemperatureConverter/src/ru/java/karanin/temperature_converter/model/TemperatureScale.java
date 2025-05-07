package ru.java.karanin.temperature_converter.model;

public enum TemperatureScale {
    CELSIUS("Цельсий") {
        @Override
        public double toCelsius(double temperature) {
            return temperature;
        }

        @Override
        public double fromCelsius(double temperature) {
            return temperature;
        }
    },
    KELVIN("Кельвин") {
        @Override
        public double toCelsius(double temperature) {
            return temperature - 273.15;
        }

        @Override
        public double fromCelsius(double temperature) {
            return temperature + 273.15;
        }
    },
    FAHRENHEIT("Фаренгейт") {
        @Override
        public double toCelsius(double temperature) {
            return (temperature - 32) / 1.8;
        }

        @Override
        public double fromCelsius(double temperature) {
            return temperature * 1.8 + 32;
        }
    };

    private final String displayName;

    TemperatureScale(String displayName) {
        this.displayName = displayName;
    }

    public abstract double toCelsius(double temperature);

    public abstract double fromCelsius(double temperature);

    @Override
    public String toString() {
        return displayName;
    }
}
