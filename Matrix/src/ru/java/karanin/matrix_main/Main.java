package ru.java.karanin.matrix_main;

import ru.java.karanin.matrix.Matrix;
import ru.karanin.java.vector.Vector;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Начинам работу с матрицами");

        System.out.print("Введите количество строк матрицы: ");
        int rowsCount = scanner.nextInt();

        System.out.print("Введите количество столбцов матрицы: ");
        int columnsCount = scanner.nextInt();

        Matrix matrix1 = new Matrix(rowsCount, columnsCount);
        System.out.println("Нулевая матрица: " + matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Скопированная матрица:");
        System.out.println(matrix2);

        // создали массив
        rowsCount = 3;
        columnsCount = 5;
        double[][] numbersArray1 = getNumbersArray(rowsCount, columnsCount);
        Matrix matrix3 = new Matrix(numbersArray1);
        System.out.println("Матрица из массива чисел: " + matrix3);

        // создали массив векторов
        Vector[] vectorsArray1 = new Vector[rowsCount];

        // в цикле начинам заполнение
        for (int i = 0; i < vectorsArray1.length; i++) {
            // векторы будем создавать из массива чисел
            double[] numbers = new double[columnsCount];

            // заполняем массив чисел
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = Math.floor(random.nextDouble() * 10000) / 100;
            }

            // записываем вектор в массив
            vectorsArray1[i] = new Vector(numbers);
        }

        // создаем матрицу из массива векторов
        Matrix matrix4 = new Matrix(vectorsArray1);
        System.out.println("Матрица №4 из массива векторов: " + matrix4);

        // получаем размер матрицы
        System.out.println("Размер матрицы из массива векторов (матрица №4): " + matrix4.getRowsCount() + "X" + matrix4.getColumnsCount());

        Vector matrixVector = matrix4.getRowByIndex(1);
        System.out.println("Вектор строка матрицы №4 на позиции 1: " + matrixVector);
        matrixVector.setComponentByIndex(0, 4.5);
        System.out.println("Вектор строка матрицы №4 на позиции 1: " + matrix4.getRowByIndex(1));

        Vector newVector = new Vector(matrix4.getRowByIndex(0));
        System.out.println("Вектор строка матрицы №4 на позиции 0: " + matrix4.getRowByIndex(0));
        newVector.setComponentByIndex(0, 3.14);
        matrix4.setRowByIndex(0, newVector);
        System.out.println("Вектор строка матрицы №4 на позиции 0: " + matrix4.getRowByIndex(0));

        System.out.println("Вектор столбец матрицы №4 на позиции 0: " + matrix4.getColumnByIndex(2));

        matrix4.transpose();
        System.out.println("Транспонирование матрицы №4: " + matrix4);

        matrix4.multiplyByScalar(2);
        System.out.println("Матрица №4 * 2: " + matrix4);

        Vector vector = matrix4.multiplyByVector(matrix4.getRowByIndex(0));
        System.out.println("Матрица №4 * на ее 0 вектор: " + vector);

        // можно и так создать
        Vector[] vectorsArray2 = new Vector[]{
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{4, 5, 5})
        };
        Matrix matrix5 = new Matrix(vectorsArray2);

        Vector[] vectorsArray3 = new Vector[]{
                new Vector(new double[]{1, 2}),
                new Vector(new double[]{3, 4}),
                new Vector(new double[]{5, 6})
        };
        Matrix matrix6 = new Matrix(vectorsArray3);

        Matrix matrix7 = Matrix.getMultiplication(matrix5, matrix6);
        System.out.println("Матрица №7 (матрица №5 * матрица №6)): " + matrix7);

        rowsCount = 1;
        columnsCount = 2;
        double[][] numbersArray2 = getNumbersArray(rowsCount, columnsCount);
        Matrix matrix8 = new Matrix(numbersArray2);
        System.out.println("Матрица №8: " + matrix8);

        double[][] numbersArray3 = getNumbersArray(rowsCount, columnsCount);
        Matrix matrix9 = new Matrix(numbersArray3);
        System.out.println("Матрица №9: " + matrix9);

        System.out.println("Матрица №8 + матрица №9: " + Matrix.getSum(matrix8, matrix9));

        System.out.println("Матрица №8: " + matrix8);
        System.out.println("Матрица №9: " + matrix9);
        System.out.println("Матрица №8 - матрица №9: " + Matrix.getDifference(matrix8, matrix9));

        rowsCount = 4;
        columnsCount = 4;
        double[][] numbersArray4 = getNumbersArray(rowsCount, columnsCount);
        Matrix matrix10 = new Matrix(numbersArray4);
        System.out.println("Матрица №10: " + matrix10);
        double determinant = matrix10.getDeterminant();
        System.out.printf("Определитель матрицы №10: %.2f%n", determinant);

        Vector[] vectorArray4 = new Vector[]{
                new Vector(new double[]{1, 2, 3}),
                new Vector(new double[]{5, 5, 6}),
                new Vector(new double[]{8, 8, 9})
        };
        Matrix matrix11 = new Matrix(vectorArray4);
        System.out.println("Матрица №11: " + matrix11);
        determinant = matrix11.getDeterminant();
        System.out.printf("Определитель матрицы №11: %.2f%n", determinant);

        double[][] numbersArray5 = new double[][]{
                new double[] {},
                new double[] {1},
                new double[] {2,3}
        };
        Matrix matrix12 = new Matrix(numbersArray5);
        System.out.println("Матрица №12: " + matrix12);

        Vector[] vectorsArray5 = new Vector[]{
                new Vector(new double[]{1}),
                new Vector(new double[]{2, 3}),
                new Vector(new double[]{4, 5, 6})
        };
        Matrix matrix13 = new Matrix(vectorsArray5);
        System.out.println("Матрица №13: " + matrix13);
    }

    public static double[][] getNumbersArray(int rowsCount, int columnsCount) {
        Random random = new Random();
        double[][] numbersArray = new double[rowsCount][columnsCount];

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                numbersArray[i][j] = Math.floor(random.nextDouble() * 1000) / 100;
            }
        }

        return numbersArray;
    }
}