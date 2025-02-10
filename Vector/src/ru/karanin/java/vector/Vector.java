package ru.karanin.java.vector;

import java.util.Arrays;

public class Vector {
    private double[] numbers;

    public Vector(int size) {
        checkSize(size);
        numbers = new double[size];
    }

    // Вектор из вектора
    public Vector(Vector vector) {
        this.numbers = new double[vector.getSize()];
        // можно и ручками по циклу пройтись, но там warning лезет - руками говорит, тока слабые копируют
        System.arraycopy(vector.numbers, 0, this.numbers, 0, numbers.length);
    }

    // Вектор из массива
    public Vector(double[] numbers) {
        this.numbers = new double[numbers.length];
        System.arraycopy(numbers, 0, this.numbers, 0, numbers.length);
    }

    // Вектор из массива, с размером
    public Vector(int size, double[] numbers) {
        this.numbers = new double[size];
        // переопределим размер по минимальной длине
        size = Math.min(size, numbers.length);
        // ленивый способ
        System.arraycopy(numbers, 0, this.numbers, 0, size);

        /* Warning на ручное копирование
        for (int i = 0; i < size; i++) {
            this.numbers[i] = numbers[i];
        }
        */
    }

    @Override
    public String toString() {
        // грех не воспользоваться
        return Arrays.toString(numbers);
    }

    public int getSize() {
        return numbers.length;
    }

    public void setNumberByPosition(int position, double number) {
        if (position < 0 || position > numbers.length) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Нет элемента №%d", position));
        }

        numbers[position] = number;
    }

    public double getNumberByPosition(int position) {
        if (position < 0 || position > numbers.length) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Нет элемента №%d", position));
        }

        return numbers[position];
    }

    // разворот
    public void reverse() {
        for (int i = 0; i < numbers.length; i++) {
            this.numbers[i] *= -1;
        }
    }

    public void add(Vector vector) {
        for (int i = 0; i < numbers.length; i++) {
            if (i == vector.numbers.length) {
                break;
            }

            numbers[i] += vector.numbers[i];
        }
    }

    public void subtract(Vector vector) {
        for (int i = 0; i < numbers.length; i++) {
            if (i == vector.numbers.length) {
                break;
            }

            numbers[i] -= vector.numbers[i];
        }
    }

    public void multiplication(double n) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] *= n;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 11;

        int hash = 1;
        hash = prime * hash + Arrays.hashCode(numbers);

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
        Vector vectorObject = (Vector) object;
        // проверили равенство ссылок и полей, спец методом
        return Arrays.compare(vectorObject.numbers, numbers) == 0;
    }

    private static void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Нельзя создать вектор с размером %d!", size));
        }
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        int newSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector newVector = new Vector(newSize);
        double number;

        for (int i = 0; i < newSize; i++) {
            number = 0;

            if (i < vector1.getSize()) {
                number += vector1.getNumberByPosition(i);
            }

            if (i < vector2.getSize()) {
                number += vector2.getNumberByPosition(i);
            }

            newVector.setNumberByPosition(i, number);
        }

        return newVector;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        int newSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector newVector = new Vector(newSize);
        double number;

        for (int i = 0; i < newSize; i++) {
            if (i < vector1.getSize()) {
                number = vector1.getNumberByPosition(i);
            } else {
                number = 0;
            }

            if (i < vector2.getSize()) {
                number -= vector2.getNumberByPosition(i);
            }

            newVector.setNumberByPosition(i, number);
        }

        return newVector;
    }

    public static Vector getMultiplication(Vector vector1, Vector vector2) {
        int newSize = Math.max(vector1.getSize(), vector2.getSize());
        Vector newVector = new Vector(newSize);
        double number;

        for (int i = 0; i < newSize; i++) {
            if (i < vector1.getSize()) {
                number = vector1.getNumberByPosition(i);
            } else {
                number = 0;
            }

            if (i < vector2.getSize()) {
                number *= vector2.getNumberByPosition(i);
            } else {
                number = 0;
            }

            newVector.setNumberByPosition(i, number);
        }

        return newVector;
    }
}
