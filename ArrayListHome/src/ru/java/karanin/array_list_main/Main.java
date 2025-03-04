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
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Работа со списком!");

        // список из файла
        System.out.print("Введите имя файла (путь к файлу): ");
        String filePath = scanner.nextLine();

        ArrayList<String> fileLines = readFileToArrayList(filePath);

        if (!fileLines.isEmpty()) {
            System.out.println("Строки файла: " + fileLines);
        }

        // список без четных чисел
        ArrayList<Integer> numbers1 = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            numbers1.add(random.nextInt(100) + 1);
        }

        System.out.println("Список содержит: " + numbers1);
        removeEvenNumbersFromArrayList(numbers1);
        System.out.println("Список без четных чисел: " + numbers1);

        // список без повторов
        ArrayList<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));
        System.out.println("Список c повторами: " + numbers2);

        ArrayList<Integer> numbers3 = getArrayListWithNoRepeats(numbers2);
        System.out.println("Список без повторов: " + numbers3);
    }

    public static ArrayList<String> readFileToArrayList(String filePath) {
        // Мы должны выполнить загрузку, но можем сломаться.
        // Функция требует return, технически мы ловим слом и превращаем ситуацию в штатную,
        // но что-то должны вернуть в случаем слома, пусть будем возвращать пустой список.
        // Для этого объявим его в начале и в самом конце сделаем return
        // (что бы плодить return в catch)
        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // читаем пока читается и добавляем в список строки
            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", filePath);
        } catch (IOException e) {
            System.out.println("При обработке файла произошла ошибка: " + e.getMessage());
        }

        return fileLines;
    }

    public static void removeEvenNumbersFromArrayList(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getArrayListWithNoRepeats(ArrayList<Integer> numbers) {
        ArrayList<Integer> numbersWithNoRepeats = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!numbersWithNoRepeats.contains(number)) {
                numbersWithNoRepeats.add(number);
            }
        }

        return numbersWithNoRepeats;
    }
}