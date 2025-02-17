package ru.java.karanin.shapes;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
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
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Круг c радиусом %.2f [S=%.2f P=%.2f]", radius, getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);

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
        Circle circle = (Circle) object;

        return radius == circle.radius;
    }
}