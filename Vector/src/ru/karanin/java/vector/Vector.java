package ru.karanin.java.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        checkSize(size);
        components = new double[size];
    }

    // Вектор из вектора
    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    // Вектор из массива
    public Vector(double[] components) {
        checkSize(components.length);
        this.components = Arrays.copyOf(components, components.length);
    }

    // Вектор из массива, с размером
    public Vector(int size, double[] components) {
        checkSize(size);
        this.components = Arrays.copyOf(components, size);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < components.length - 1; i++) {
            stringBuilder.append(components[i]).append(", ");
        }

        stringBuilder.append(components[components.length - 1]);

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    // размер вектора
    public int getSize() {
        return components.length;
    }

    // длина вектора
    public double getLength() {
        double componentSquaresSum = 0;

        for (double component : components) {
            componentSquaresSum += component * component;
        }

        return Math.sqrt(componentSquaresSum);
    }

    public double getComponentByIndex(int index) {
        int maxIndex = components.length - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс должен быть в диапазоне [0, %d], передано %d", maxIndex, index));
        }

        return components[index];
    }

    public void setComponentByIndex(int index, double component) {
        int maxIndex = components.length - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс должен быть в диапазоне [0, %d], передано %d", maxIndex, index));
        }

        components[index] = component;
    }

    // разворот
    public void reverse() {
        multiply(-1);
    }

    public void add(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // сам объект
        if (object == this) {
            return true;
        }

        // null или другой класс
        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        // привели класс
        Vector vector = (Vector) object;
        // проверили равенство ссылок и полей, спец методом
        return Arrays.equals(vector.components, components);
    }

    private static void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Размер вектора должен быть больше нуля, передано %d", size));
        }
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int resultSize = Math.max(vector1.components.length, vector2.components.length);
        // в новый вектор записали вектор 1
        Vector resultVector = new Vector(resultSize, vector1.components);
        // прибавили в пустой вектор 2
        resultVector.add(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        int resultSize = Math.max(vector1.components.length, vector2.components.length);
        // в новый вектор записали вектор 1
        Vector resultVector = new Vector(resultSize, vector1.components);
        // отняли от результата вектор 2
        resultVector.subtract(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);

        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
