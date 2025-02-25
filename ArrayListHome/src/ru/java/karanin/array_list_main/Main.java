package ru.java.karanin.array_list_main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Работа со списком!");

        // блоки ДЗ = отдельные методы
        readFileToArrayList();
        removeEvenNumbersFromArrayList();
        removeDoublesFromArrayList();
    }

    public static void readFileToArrayList() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя файла (путь к файлу): ");
        String filePath = scanner.nextLine();

        try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            ArrayList<String> fileLines = new ArrayList<>();

            String line;

            // читаем пока читается и добавляем в список строки
            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }

            // выводим строки
            System.out.println(fileLines);
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден", filePath);
        } catch (IOException e) {
            // сомнительный перехват, а что там может сломаться?
            System.out.println("При обработке файла произошла ошибка: " + e.getMessage());
        }
    }

    public static void removeEvenNumbersFromArrayList() {
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            numbers.add(random.nextInt(100) + 1);
        }

        System.out.println("Список содержит: " + numbers);

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Список без четных чисел: " + numbers);
    }

    public static void removeDoublesFromArrayList() {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));
        System.out.println("Список c повторами: " + numbers);

        ArrayList<Integer> numbersWithoutDoubles = new ArrayList<>();

        for (Integer number : numbers) {
            if (!numbersWithoutDoubles.contains(number)) {
                numbersWithoutDoubles.add(number);
            }
        }

        System.out.println("Список без  повторов: " + numbersWithoutDoubles);
    }
}