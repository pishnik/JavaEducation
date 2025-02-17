package ru.java.karanin.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Ширина
    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // Высота
    @Override
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    // Площадь
    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник c высотой %.2f и шириной %.2f [S=%.2f P=%.2f]", height, width, getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);

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
        Rectangle rectangle = (Rectangle) object;

        return width == rectangle.width && height == rectangle.height;
    }
}