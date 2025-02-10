package ru.java.karanin.shapes;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    // Ширина
    @Override
    public double getWidth() {
        return sideLength;
    }

    // Высота
    @Override
    public double getHeight() {
        return sideLength;
    }

    // Площадь
    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return String.format("Квадрат со стороной %.2f [S=%.2f P=%.2f]", getSideLength(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        // привели класс
        Square square = (Square) object;

        return sideLength == square.sideLength;
    }
}
