package ru.java.karanin.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;
    // стороны
    private double sideABLength;
    private double sideACLength;
    private double sideBCLength;


    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private void calculateSides() {
        sideABLength = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        sideACLength = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        sideBCLength = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }

    @Override
    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }

    @Override
    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }

    @Override
    public double getArea() {
        calculateSides();
        double semiPerimeter = (sideABLength + sideACLength + sideBCLength) / 2;

        return Math.sqrt(semiPerimeter * (semiPerimeter - sideABLength) * (semiPerimeter - sideACLength) * (semiPerimeter - sideBCLength));
    }

    @Override
    public double getPerimeter() {
        calculateSides();

        return sideABLength + sideACLength + sideBCLength;
    }

    @Override
    public String toString() {
        return String.format("Треугольник [S=%.2f P=%.2f]", getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Triangle)) {
            return false;
        }

        if (hashCode() != object.hashCode()) {
            return false;
        }

        return (object == this) || (x1 == ((Triangle) object).x1
                && y1 == ((Triangle) object).y1
                && x2 == ((Triangle) object).x2
                && y2 == ((Triangle) object).y2
                && x3 == ((Triangle) object).x3
                && y3 == ((Triangle) object).y3);
    }
}