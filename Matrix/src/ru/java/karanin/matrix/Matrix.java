package ru.java.karanin.matrix;

import ru.karanin.java.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    // по размерам
    public Matrix(int rowsCount, int columnsCount) {
        // проверяем аргументы
        if (rowsCount <= 0) {
            throw new IllegalArgumentException(String.format("Количество строк матрицы должно быть больше нуля, передано значение %d", rowsCount));
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException(String.format("Количество столбцов матрицы должно быть больше нуля, передано значение %d", columnsCount));
        }

        // создаем
        rows = new Vector[rowsCount];

        // заполнили массив векторами
        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
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
        // проверяем размеры
        if (numbersArray.length == 0) {
            throw new IllegalArgumentException(String.format("Количество строк матрицы должно быть больше нуля, передано значение %d", numbersArray.length));
        }

        // а тут засада numbersArray[0].length == 0 не катит, так как у других массивов могут быть элементы
        int arrayMaxSize = 0;

        for (double[] array : numbersArray) {
            if (array.length > arrayMaxSize) {
                arrayMaxSize = array.length;
            }
        }

        // проверяем что там
        if (arrayMaxSize == 0) {
            throw new IllegalArgumentException(String.format("Количество столбцов матрицы должно быть больше нуля, передано значение %d", numbersArray[0].length));
        }

        rows = new Vector[numbersArray.length];

        for (int i = 0; i < numbersArray.length; i++) {
            rows[i] = new Vector(arrayMaxSize, numbersArray[i]);
        }
    }

    // из массива векторов-строк
    public Matrix(Vector[] vectorsArray) {
        // копируем в массив векторов сами векторы
        if (vectorsArray.length == 0) {
            throw new IllegalArgumentException(String.format("Количество строк матрицы должно быть больше нуля, передано значение %d", vectorsArray.length));
        }

        // проверять длину векторов не нужно, там всегда больше ноля,
        // но максимальную надо посчитать
        int maxVectorSize = 0;

        for (Vector vector : vectorsArray) {
            if (vector.getSize() > maxVectorSize) {
                maxVectorSize = vector.getSize();
            }
        }

        rows = new Vector[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            // метода добивки вектора нет, пойдем через массив - пробегаем по вектору, собираем массив
            double[] rowItems = new double[vectorsArray[i].getSize()];

            for (int j = 0; j < vectorsArray[i].getSize(); j++) {
                rowItems[j] = vectorsArray[i].getComponentByIndex(j);
            }

            rows[i] = new Vector(maxVectorSize, rowItems);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRowByIndex(int index) {
        int maxIndex = rows.length - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс строки должен быть в диапазоне [0, %d], передано значение %d", maxIndex, index));
        }

        return new Vector(rows[index]);
    }

    public void setRowByIndex(int index, Vector vector) {
        int maxIndex = rows.length - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс строки должен быть в диапазоне [0, %d], передано значение %d", maxIndex, index));
        }

        // важно что бы размеры векторов совпадали
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException(String.format("Количество элементов вектора [%d] не совпадает с количество столбцов в матрице [%d]!", vector.getSize(), getColumnsCount()));
        }

        // новый вектор созданный из переданного
        // rows[index] = new Vector(vector);
        // но лучше конечно что бы не забивать память, затрем оригинал на значения из переданного
        for (int i = 0; i < vector.getSize(); i++) {
            rows[index].setComponentByIndex(i, vector.getComponentByIndex(i));
        }
    }

    public Vector getColumnByIndex(int index) {
        int maxIndex = getColumnsCount() - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс должен быть в диапазоне [0, %d], передано значение %d", maxIndex, index));
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
            stringBuilder.append(rows[i]).append(", ");
        }

        stringBuilder.append(rows[rows.length - 1]).append('}');

        return stringBuilder.toString();
    }

    // транспонирование
    public void transpose() {
        // по количеству столбцов
        Vector[] newRows = new Vector[getColumnsCount()];

        // столбцы пишем в строки
        for (int i = 0; i < getColumnsCount(); i++) {
            newRows[i] = getColumnByIndex(i);
        }

        rows = newRows;
    }

    // умножение на скаляр
    public void multiplyByScalar(double scalar) {
        for (Vector matrixRow : rows) {
            matrixRow.multiply(scalar);
        }
    }

    // умножение на вектор
    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException(String.format("Количество элементов вектора [%d] должно совпадать с количеством столбцов матрицы [%d]", vector.getSize(), rows[0].getSize()));
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
        checkSizesEqual(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    // отнять
    public void subtract(Matrix matrix) {
        checkSizesEqual(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    // расчет минора
    private Matrix getMinor(int rowIndex, int columnIndex) {
        // создаем минорную матрицу
        int minorLength = rows.length - 1;

        double[][] minor = new double[minorLength][minorLength];
        // эти переменные для того, чтобы пропускать строку и столбец
        int deltaI = 0;

        for (int i = 0; i <= minorLength; i++) {
            int deltaJ = 0;

            for (int j = 0; j <= minorLength; j++) {
                if (i == rowIndex) {
                    deltaI = 1;
                } else {
                    if (j == columnIndex) {
                        deltaJ = 1;
                    } else {
                        minor[i - deltaI][j - deltaJ] = rows[i].getComponentByIndex(j);
                    }
                }
            }
        }

        return new Matrix(minor);
    }

    // определитель
    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            // RuntimeException
            throw new ArithmeticException(String.format("Количество строк %d не равно количеству столбцов %d, вычисление определителя невозможно", rows.length, getColumnsCount()));
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
        checkSizesEqual(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.add(matrix2);

        return resultMatrix;
    }

    // вычитание матрицы из матрицы
    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkSizesEqual(matrix1, matrix2);

        Matrix resultMatrix = new Matrix(matrix1);
        resultMatrix.subtract(matrix2);

        return resultMatrix;
    }

    private static void checkSizesEqual(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException(String.format("У матриц не совпадают размеры: [%dX%d]/[%dX%d]", matrix1.getRowsCount(), matrix1.getColumnsCount(),  matrix2.getRowsCount(), matrix2.getColumnsCount()));
        }
    }

    // умножение матрицы на матрицу
    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException(String.format("Количество столбцов в первой матрице %d должно совпадать с количеством строк второй матрицы %d", matrix1.getColumnsCount(), matrix2.rows.length));
        }

        double[][] result = new double[matrix1.rows.length][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                result[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumnByIndex(j));
            }
        }

        return new Matrix(result);
    }

    // проверка равенства
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
        Matrix matrix = (Matrix) object;

        // размеры проверим, что бы зря по элементам не бегать
        if (matrix.rows.length != rows.length || getColumnsCount() != matrix.getColumnsCount()) {
            return false;
        }

        // если размеры ок, надо сверять элементы
        // штатно сверяем массивы векторов
        return Arrays.equals(rows, matrix.rows);
    }

    // хэш
    @Override
    public int hashCode() {
        // воспользуемся базовым методом массивов
        return Arrays.hashCode(rows);
    }
}