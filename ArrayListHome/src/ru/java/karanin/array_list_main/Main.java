package ru.java.karanin.array_list_main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Работа со списком!");
        // создание списка
        ArrayList<String> lines = new ArrayList<>();
        // заполнение
        lines.add("Первая строка");
        lines.add("Вторая строка");
        lines.add("Третья строка");

        // вывод
        System.out.println(lines);

        //
        ArrayList<String> fileLines = new ArrayList<>();

        System.out.print("Введите имя файла (путь к файлу): ");
        String filePath = scanner.nextLine();

        try (FileReader fileReader = new FileReader(filePath); Scanner fileScanner = new Scanner(fileReader)) {
            // читаем и добавляем в список строки
            while (fileScanner.hasNextLine()) {
                fileLines.add(fileScanner.nextLine());
            }

            // выводим строки
            System.out.println(fileLines);
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден!%n", filePath);
        } catch (IOException e) {
            // сомнительный перехват, а что там может сломаться?
            System.out.println("При обработке файла произошла ошибка!%d");
        }

        ArrayList<Integer> numbers1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers1.add(random.nextInt(100) + 1);
        }

        System.out.println("Список содержит: " + numbers1);

        for (int i = 0; i < numbers1.size(); i++) {
            if (numbers1.get(i) % 2 == 0) {
                numbers1.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));
        System.out.println("Список c повторами: " + numbers2);

        // в лоб были бы доп массив или список - не оптимально так будем хранить часть списка
        // сделаем проще, в цикле перебираем элементы списка и сравниваем между собой
        for (int i = 0; i < numbers2.size(); i++) {
            // внутренний цикл от последующего до конца
            for (int j = i + 1; j < numbers2.size(); j++) {
                // если i элемент = j
                if (numbers2.get(i).equals(numbers2.get(j))) {
                    // удаляем элемент
                    numbers2.remove(j);
                    // уменьшаем индекс
                    j--;
                }
            }
        }

        System.out.println("Список без  повторов: " + numbers2);

        // решение через indexOf и lastIndexOf
        numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));

        for (int i = 0; i < numbers2.size(); i++) {
            // в цикле сравниваем текущий индекс (фактически первый) с последним
            if (numbers2.indexOf(numbers2.get(i)) != numbers2.lastIndexOf(numbers2.get(i))) {
                // если они не совпали, удаляем последний
                numbers2.remove(numbers2.lastIndexOf(numbers2.get(i)));
                // пока "удаляется" стоим на элементе
                i--;
            }
        }

        System.out.println("Список без  повторов: " + numbers2);
    }
}