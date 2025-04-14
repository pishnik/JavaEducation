package ru.java.karanin.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MyArrayList extends ArrayList {
/*
    public void loadFromFile() {
        Object[] objectArray = numbers2.toArray();

        // внутри списка объекты -> инты
        for (Object object : objectArray) {
            // в цикле сравниваем текущий индекс (фактически первый) с последним
            if (numbers2.indexOf(object) != numbers2.lastIndexOf(object)) {
                // если они не совпали, удаляем последний
                numbers2.remove(numbers2.lastIndexOf(object));
            }
        }


        // доп способ через поток: переводим список в поток, у потока используем distinct
        numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));
        numbers2 = (ArrayList<Integer>) numbers2.stream().distinct().collect(Collectors.toList());
        System.out.println("Список без  повторов: " + numbers2);

    private Matrix getMinor(int rowIndex, int columnIndex) {
        // создаем минорную матрицу
        int minorLength = rows.length - 1;
        double[][] minor = new double[minorLength][minorLength];

        for (int rowI = 0, minorRowI = 0; rowI < rows.length; rowI++, minorRowI++) {
            if (rowI == rowIndex) {
                minorRowI--;
                continue;
            }

            for (int columnI = 0, minorColumnI = 0; columnI < rows.length; columnI++, minorColumnI++) {
                if (columnI == columnIndex) {
                    minorColumnI--;
                    continue;
                }

                minor[minorRowI][minorColumnI] = rows[rowI].getComponentByIndex(columnI);
            }
        }

        return new Matrix(minor);
    }


    }
*/
}
