package ru.java.karanin.shapes;

public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    //Ширина
    @Override
    public double getWidth() {
        return width;
    }

    //Высота
    @Override
    public double getHeight() {
        return height;
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
        return String.format("Прямоугольник [S=%.2f P=%.2f]", getArea(), getPerimeter());
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
        if (!(object instanceof Rectangle)) {
            return false;
        }

        if (hashCode() != object.hashCode()) {
            return false;
        }

        return (object == this) || (width == ((Rectangle) object).width && height == ((Rectangle) object).height);
    }
}