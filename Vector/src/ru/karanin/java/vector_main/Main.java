package ru.karanin.java.vector_main;

import ru.karanin.java.vector.Vector;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Чтение значений с консоли
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размер вектора: ");
        int size = scanner.nextInt();

        Vector vector1 = new Vector(size);
        System.out.println("Нулевой вектор: " + vector1);

        // создали массив
        double[] array1 = new double[5];

        for (int i = 0; i < 5; i++) {
            array1[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        System.out.println("Массив: " + Arrays.toString(array1));
        Vector vector2 = new Vector(array1);
        System.out.println("Вектор 2 из массива: " + vector2);

        vector2.setComponentByIndex(0, Math.floor(random.nextDouble() * 10000) / 100);
        Vector vector3 = new Vector(vector2);
        System.out.println("Вектор 3 из вектора 2 (с подменой 0 элемента): " + vector3);

        Vector vector4 = new Vector(array1.length + 2, array1);
        System.out.println("Вектор 4: " + vector4);
        Vector vector5 = new Vector(array1.length - 2, array1);
        System.out.println("Вектор 5: " + vector5);
        System.out.printf("В векторе 5, на позиции 0: %.2f%n", vector5.getComponentByIndex(0));

        System.out.print("Введите индекс элемента: ");
        int index = scanner.nextInt();

        System.out.printf("В векторе 5, на позиции %d : %.2f%n", index, vector5.getComponentByIndex(index));

        if (vector2.equals(vector3)) {
            System.out.println("Вектор 2: " + vector2 + " и вектор 3: " + vector3 + " равны!");
        }

        // развернем 5 вектор
        vector5.reverse();
        System.out.println("Развернутый вектор 5: " + vector5);

        System.out.printf("Размер вектора 3: %d%n", vector3.getSize());

        System.out.println("Вектор 4: " + vector4);
        System.out.println("Вектор 5: " + vector5);
        vector4.add(vector5);
        System.out.println("Вектор 4 (4+5): " + vector4);
        vector5.add(vector4);
        System.out.println("Вектор 5 (5+4): " + vector5);

        double[] array2 = new double[5];

        for (int i = 0; i < 5; i++) {
            array2[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        double[] array3 = new double[5];

        for (int i = 0; i < 5; i++) {
            array3[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        Vector vector7 = new Vector(array2);
        Vector vector8 = new Vector(array3);
        System.out.println("Вектор 7: " + vector7);
        System.out.println("Вектор 8: " + vector8);
        vector8.subtract(vector7);
        System.out.println("Вектор 8 (8-7): " + vector8);

        System.out.print("Введите скалярную величину: ");
        double scalar = scanner.nextDouble();

        vector8.multiply(scalar);
        System.out.println("Вектор 8 (8*scalar): " + vector8);

        Vector vector9 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector10 = new Vector(new double[]{1, 2, 3});
        System.out.println("Вектор 9: " + vector9);
        System.out.println("Вектор 10: " + vector10);

        Vector vector11 = Vector.getSum(vector9, vector10);
        System.out.println("Вектор 11: " + vector11);

        Vector vector12 = Vector.getDifference(vector9, vector10);
        System.out.println("Вектор 12: " + vector12);
        System.out.printf("Длина вектора 12: %.2f%n", vector12.getLength());

        double scalarProduct = Vector.getScalarProduct(vector9, vector10);
        System.out.printf("Скалярное произведение вектора 9 и 10: %.2f%n", scalarProduct);

        Vector vector14 = new Vector(new double[]{1, 2, 3});
        Vector vector15 = new Vector(new double[]{1, 2, 3, 4, 5});
        System.out.println("Вектор 16: " + Vector.getSum(vector14, vector15));
        System.out.println("Вектор 17: " + Vector.getDifference(vector14, vector15));
        System.out.println("Вектор 18: " + Vector.getScalarProduct(vector14, vector15));
    }
}