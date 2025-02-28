package ru.java.karanin.matrix;

import ru.karanin.java.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    // по размерам
    public Matrix(int n, int m) {
        // проверяем аргументы
        if (n <= 0) {
            throw new IllegalArgumentException(String.format("Количество строк матрицы должно быть больше нуля, введено %d", n));
        }

        if (m <= 0) {
            throw new IllegalArgumentException(String.format("Количество элементов строки матрицы должно быть больше нуля, введено %d", m));
        }

        // создаем
        rows = new Vector[m];

        // заполнили массив векторами
        for (int i = 0; i < m; i++) {
            rows[i] = new Vector(m);
        }
    }

    // из матрицы
    public Matrix(Matrix matrix) {
        // копируем массив элементами которого являются векторы
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    // из двумерного массива чисел
    public Matrix(double[][] numbersArray) {
        rows = new Vector[numbersArray.length];

        for (int i = 0; i < numbersArray.length; i++) {
            rows[i] = new Vector(numbersArray[i]);
        }
    }

    // из массива векторов-строк
    public Matrix(Vector[] vectorsArray) {
        // копируем в массив векторов сами векторы
        rows = Arrays.copyOf(vectorsArray, vectorsArray.length);
    }

    // из массива векторов-строк
    public String getSize() {
        return String.format("%dx%d", rows.length, rows[0].getSize());
    }

    public Vector getRowByIndex(int index) {
        int maxIndex = rows.length - 1;
        if (index < 0 || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Индекс строки должен быть в диапазоне [0, %d], введено %d", maxIndex, index));
        }

        return rows[index];
    }

    public void setRowByIndex(int index, Vector vector) {
        int maxIndex = rows.length - 1;

        if (index < 0 || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Индекс строки должен быть в диапазоне [0, %d], введено %d", maxIndex, index));
        }

        // важно что бы размеры векторов совпадали
        if (vector.getSize() != rows[index].getSize()) {
            throw new ArrayIndexOutOfBoundsException(String.format("Размер вектора [%d] не совпадает с размеров строки в матрице [%d]!", vector.getSize(), rows[index].getSize()));
        }

        // новый вектор созданный из переданного
        // rows[index] = new Vector(vector);
        // но лучше конечно что бы не забивать память, затрем оригинал на значения из переданного
        for (int i = 0; i < vector.getSize(); i++) {
            rows[index].setComponentByIndex(i, vector.getComponentByIndex(i));
        }
    }

    public Vector getColumnByIndex(int index) {
        int maxIndex = rows[0].getSize() - 1;

        if (index < 0 || index > maxIndex) {
            throw new ArrayIndexOutOfBoundsException(String.format("Индекс должен быть в диапазоне [0, %d], введено %d", maxIndex, index));
        }

        double[] numbers = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            numbers[i] = rows[i].getComponentByIndex(index);
        }

        return new Vector(numbers);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < rows.length - 1; i++) {
            stringBuilder.append(rows[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.append(rows[rows.length - 1]);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    // метод для вывода по строкам
    public void print() {
        for (Vector element : rows) {
            System.out.println(element);
        }
    }

    // транспонирование
    public void transpose() {
        // запоминаем исходные размеры
        int n = rows.length;
        int m = rows[0].getSize();
        // создаем темповый массив векторов, на него потом заменим оригинал
        Vector[] tempRows = new Vector[m];

        for (int j = 0; j < m; j++) {
            // для создания вектора берем новый массив чисел
            double[] tempArray = new double[n];

            // заполняем массив
            for (int i = 0; i < n; i++) {
                tempArray[i] = rows[i].getComponentByIndex(j);
            }

            // кладем новый вектор в массив
            tempRows[j] = new Vector(tempArray);
        }

        // перебили массив
        rows = tempRows;
    }

    // умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (Vector element : rows) {
            element.multiply(scalar);
        }
    }

    // умножение на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != rows[0].getSize()) {
            throw new IllegalArgumentException(String.format("Размер вектора [%d] должен совпадать с размером строки матрицы [%d]", vector.getSize(), rows[0].getSize()));
        }

        // массив элементов
        double[] result = new double[rows.length];

        // расчет значений
        for (int i = 0; i < rows.length; i++) {
            result[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(result);
    }

    // прибавить
    public void add(Matrix matrix) {
        if (rows.length != matrix.rows.length) {
            throw new IllegalArgumentException(String.format("Количество строк в прибавляемой матрице (matrix.rows.length = %d) не совпадает с количеством строк исходной матрицы %d", matrix.rows.length, rows.length));
        }

        if (rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException(String.format("Длина строки прибавляемой матрицы (matrix.rows[0].getSize() = %d) не совпадает с длиной строки исходной матрицы %d", matrix.rows[0].getSize(), rows[0].getSize()));
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // отнять
    public void subtract(Matrix matrix) {
        if (rows.length != matrix.rows.length) {
            throw new IllegalArgumentException(String.format("Количество строк в вычитаемой матрице (matrix.rows.length = %d) не совпадает с количеством строк исходной матрицы %d", matrix.rows.length, rows.length));
        }

        if (rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new IllegalArgumentException(String.format("Длина строки вычитаемой матрицы (matrix.rows[0].getSize() = %d) не совпадает с длиной строки исходной матрицы %d", matrix.rows[0].getSize(), rows[0].getSize()));
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    // расчет минора
    private Matrix getMinor(int rowIndex, int columnIndex) {
        // создаем минорную матрицу
        int minorLength = rows.length - 1;
        double[][] minor = new double[minorLength][minorLength];
        //эти переменные для того, чтобы пропускать строку и столбец
        int ignoredRow = 0;
        int ignoredColumn;

        for (int i = 0; i <= minorLength; i++) {
            ignoredColumn = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == rowIndex) {
                    ignoredRow = 1;
                } else {
                    if (j == columnIndex) {
                        ignoredColumn = 1;
                    } else {
                        minor[i - ignoredRow][j - ignoredColumn] = rows[i].getComponentByIndex(j);
                    }
                }
            }
        }

        return new Matrix(minor);
    }

    // определитель
    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new IllegalArgumentException("Матрица должна быть квадратной");
        }

        // можно конечно побегать по столбцам и строкам в поисках повторов/пропорций и проверки других свойств определителя, но это выглядит не эффективно
        // одна строка/один столбец
        if (rows.length == 1) {
            return rows[0].getComponentByIndex(0);
        }

        // 2x2 по формуле определителя
        if (rows.length == 2) {
            return rows[0].getComponentByIndex(0) * rows[1].getComponentByIndex(1) - rows[0].getComponentByIndex(1) * rows[1].getComponentByIndex(0);
        }

        // раскладываем через миноры по первой строке матрицы
        double determinant = 0;

        for (int i = 0; i < rows.length; i++) {
            // рекурсия
            determinant += Math.pow(-1, i) * rows[0].getComponentByIndex(i) * getMinor(0, i).getDeterminant();
        }

        return determinant;
    }


    // сложение матриц
    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    //  вычитание матрицы из матрицы
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix resultMatrix = new Matrix(matrix1);

        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    // умножение матрицы на матрицу
    public static Matrix getMultiplyProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows[0].getSize() != matrix2.rows.length) {
            throw new IllegalArgumentException(String.format("Размер строки в первой матрице %d должен совпадать с количеством строк второй матрицы %d", matrix1.rows[0].getSize(), matrix2.rows.length));
        }

        double[][] result = new double[matrix1.rows.length][matrix2.rows[0].getSize()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.rows[0].getSize(); j++) {
                result[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumnByIndex(j));
            }
        }

        return new Matrix(result);
    }
}


