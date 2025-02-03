package ru.java.karanin.shapes;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Круг [S=%.2f P=%.2f]", getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 10;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Circle)) {
            return false;
        }

        if (hashCode() != object.hashCode()) {
            return false;
        }

        return (object == this) || (radius == ((Circle) object).radius);
    }
}