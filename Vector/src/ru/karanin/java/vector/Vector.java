package ru.karanin.java.vector;

import java.util.Arrays;

public class Vector {
    private double[] numbersArray;

    public Vector(int size) {
        checkSize(size);
        numbersArray = new double[size];
    }

    // Вектор из вектора
    public Vector(Vector vector) {
        numbersArray = Arrays.copyOf(vector.numbersArray, vector.numbersArray.length);
    }

    // Вектор из массива
    public Vector(double[] numbersArray) {
        checkSize(numbersArray.length);
        this.numbersArray = Arrays.copyOf(numbersArray, numbersArray.length);
    }

    // Вектор из массива, с размером
    public Vector(int size, double[] numbers) {
        checkSize(size);
        this.numbersArray = Arrays.copyOf(numbers, size);
    }

    @Override
    public String toString() {
        // грех не воспользоваться
        return Arrays.toString(numbersArray).replace("[", "{").replace("]", "}");
    }

    // размер вектора
    public int getSize() {
        return numbersArray.length;
    }

    // длина вектора
    public double getLength() {
        double length = 0;

        for (double number : numbersArray) {
            length += number * number;
        }

        return Math.sqrt(length);
    }

    public double getArrayNumberByIndex(int index) {
        int maxIndex = numbersArray.length - 1;
        if (index < 0 || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Ошибка ввода! Индекс может быть в диапазоне [0, %d], вы ввели %d", maxIndex, index));
        }

        return numbersArray[index];
    }

    public void setArrayNumberByIndex(int index, double number) {
        int maxIndex = numbersArray.length - 1;
        if (index < 0 || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Ошибка ввода! Индекс может быть в диапазоне [0, %d], вы ввели %d", maxIndex, index));
        }

        numbersArray[index] = number;
    }

    // разворот
    public void reverse() {
        multiply(-1);
    }

    public void add(Vector vector) {
        if (numbersArray.length < vector.numbersArray.length) {
            numbersArray = Arrays.copyOf(numbersArray, vector.numbersArray.length);
        }

        for (int i = 0; i < numbersArray.length; i++) {
            if (i == vector.numbersArray.length) {
                return;
            }

            numbersArray[i] += vector.numbersArray[i];
        }
    }

    public void subtract(Vector vector) {
        if (numbersArray.length < vector.numbersArray.length) {
            numbersArray = Arrays.copyOf(numbersArray, vector.numbersArray.length);
        }

        for (int i = 0; i < numbersArray.length; i++) {
            if (i == vector.numbersArray.length) {
                return;
            }

            numbersArray[i] -= vector.numbersArray[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < numbersArray.length; i++) {
            numbersArray[i] *= scalar;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Arrays.hashCode(numbersArray);

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
        return Arrays.equals(vector.numbersArray, numbersArray);
    }

    private static void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Размер вектора должен быть больше нуля [Вы ввели %d]!", size));
        }
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        int newSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector newVector = new Vector(newSize);
        // прибавили в пустой вектор 1
        newVector.add(vector1);
        // прибавили в пустой вектор 2
        newVector.add(vector2);

        return newVector;
    }

    public static Vector geDifference(Vector vector1, Vector vector2) {
        int newSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector newVector = new Vector(newSize);

        newVector.add(vector1);
        newVector.subtract(vector2);

        return newVector;
    }

    public static double getProduct(Vector vector1, Vector vector2) {
        double result = 0;
        int minSize = Math.min(vector1.numbersArray.length, vector2.numbersArray.length);

        for (int i = 0; i < minSize; i++) {
            result += vector1.numbersArray[i] * vector2.numbersArray[i];
        }

        return result;
    }
}
