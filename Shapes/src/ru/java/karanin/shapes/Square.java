package ru.java.karanin.shapes;

public class Square implements Shape {
    private final double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    //Ширина
    @Override
    public double getWidth() {
        return sideLength;
    }

    //Высота
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
        return String.format("Квадрат [S=%.2f P=%.2f]", getArea(), getPerimeter());
    }

    public int hashCode() {
        final int prime = 12;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Square)) {
            return false;
        }

        if (hashCode() != object.hashCode()) {
            return false;
        }

        return (object == this) || (sideLength == ((Square) object).sideLength);
    }
}
