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
        double[] userArray1 = new double[5];

        for (int i = 0; i < 5; i++) {
            userArray1[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        System.out.println("Массив: " + Arrays.toString(userArray1));
        Vector vector2 = new Vector(userArray1);
        System.out.println("Вектор из массива: " + vector2);

        vector2.setNumberByPosition(0, Math.floor(random.nextDouble() * 10000) / 100);
        Vector vector3 = new Vector(vector2);
        System.out.println("Вектор из вектора (с подменой 0 элемента): " + vector3);

        Vector vector4 = new Vector(userArray1.length + 2, userArray1);
        System.out.println("Вектор 4: " + vector4);
        Vector vector5 = new Vector(userArray1.length - 2, userArray1);
        System.out.println("Вектор 5: " + vector5);
        System.out.printf("В векторе 5, на позиции 0: %.2f%n", vector5.getNumberByPosition(0));

        if (vector2.equals(vector3)) {
            System.out.println("Вектор 2 и 3 равны!");
        }

        if (vector2.equals(vector3)) {
            System.out.println("Вектор: " + vector2 + " и вектор " + vector3 + " равны!");
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

        double[] userArray2 = new double[5];

        for (int i = 0; i < 5; i++) {
            userArray2[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        double[] userArray3 = new double[5];

        for (int i = 0; i < 5; i++) {
            userArray3[i] = Math.floor(random.nextDouble() * 10000) / 100;
        }

        Vector vector7 = new Vector(userArray2);
        Vector vector8 = new Vector(userArray3);
        System.out.println("Вектор 7: " + vector7);
        System.out.println("Вектор 8: " + vector8);
        vector8.subtract(vector7);
        System.out.println("Вектор 8 (8-7): " + vector8);

        System.out.print("Введите скалярную величину: ");
        double n = scanner.nextDouble();
        vector8.multiplication(n);
        System.out.println("Вектор 8 (8*n): " + vector8);

        Vector vector9 = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector10 = new Vector(new double[]{1, 2, 3});

        Vector vector11 = Vector.getAddition(vector9, vector10);
        System.out.println("Вектор 11: " + vector11);

        Vector vector12 = Vector.getSubtraction(vector9, vector10);
        System.out.println("Вектор 12: " + vector12);

        Vector vector13 = Vector.getMultiplication(vector9, vector10);
        System.out.println("Вектор 13: " + vector13);

        Vector vector14 = new Vector(new double[]{1, 2, 3});
        Vector vector15 = new Vector(new double[]{1, 2, 3, 4, 5});
        System.out.println("Вектор 11: " + Vector.getAddition(vector14, vector15));
        System.out.println("Вектор 12: " + Vector.getSubtraction(vector14, vector15));
        System.out.println("Вектор 13: " + Vector.getMultiplication(vector14, vector15));


    }
}