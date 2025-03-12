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

        try {
            ArrayList<String> fileLines = readFileLinesToArrayList(filePath);

            if (!fileLines.isEmpty()) {
                System.out.println("Строки файла: " + fileLines);
            } else {
                System.out.println("Файл пуст");
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл \"%s\" не найден%n", filePath);
        } catch (IOException e) {
            System.out.println("При обработке файла произошла ошибка: " + e.getMessage());
        }

        // список без четных чисел
        ArrayList<Integer> numbers1 = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            numbers1.add(random.nextInt(100) + 1);
        }

        System.out.println("Список содержит: " + numbers1);
        removeEvenNumbersFromArrayList(numbers1);
        System.out.println("Список без четных чисел: " + numbers1);

        // список без повторов числа
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 4, 2, 2, 3, 4, 5, 9, 11, 10));
        System.out.println("Список c повторами: " + numbersList);

        ArrayList<Integer> numbersListWithNoRepeats = getArrayListWithNoRepeats(numbersList);
        System.out.println("Список без повторов: " + numbersListWithNoRepeats);

        // список без повторов со строками
        ArrayList<String> stringsList = new ArrayList<>(Arrays.asList("раз", "два", "раз", "два", "три", "пять", "семь", "семь", "ноль", "ноль"));
        System.out.println("Список c повторами: " + stringsList);

        ArrayList<String> stringsListWithNoRepeats = getArrayListWithNoRepeats(stringsList);
        System.out.println("Список без повторов: " + stringsListWithNoRepeats);

    }

    public static ArrayList<String> readFileLinesToArrayList(String filePath) throws IOException {
        // Мы должны выполнить загрузку, но можем сломаться
        // что-то должны вернуть в случаем слома, пусть будем возвращать пустой список
        // для этого объявим его в начале и в самом конце сделаем return
        ArrayList<String> fileLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // читаем пока читается и добавляем в список строки
            while ((line = bufferedReader.readLine()) != null) {
                fileLines.add(line);
            }
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

    // делаем метод рабочим для всех типов элементов в массиве
    // не совсем понял принцип объявления в уже существующих классах
    // почему <E> до ArrayList а не после, а Idea вообще предлагает убрать <E>,
    // но нам то надо что бы тип была на весь ArrayList, в не ArrayList Object,
    // запутался в объявлении generic
    // public static ArrayList<E> getArrayListWithNoRepeats(ArrayList<E> elements) не работает, E не узнает
    // public static <E> ArrayList getArrayListWithNoRepeats(ArrayList<E> elements) так будут warning raw
    // вот так ушли все warning, но эт чисто методом тыка дошел, не понял зачем тут <E> в самом начале
    public static <E> ArrayList<E> getArrayListWithNoRepeats(ArrayList<E> arrayList) {
        ArrayList<E> arrayListWithNoRepeats = new ArrayList<>(arrayList.size());

        for (E element : arrayList) {
            if (!arrayListWithNoRepeats.contains(element)) {
                arrayListWithNoRepeats.add(element);
            }
        }

        return arrayListWithNoRepeats;
    }

    /* Пример от Object
    // Если написать так, получим кучу warning raw и т.д.
    public static ArrayList getArrayListWithNoRepeats(ArrayList arrayList) {
        ArrayList arrayListWithNoRepeats = new ArrayList<>(arrayList.size());

        for (Object item : arrayList) {
            if (!arrayListWithNoRepeats.contains(item)) {
                arrayListWithNoRepeats.add(item);
            }
        }

        return arrayListWithNoRepeats;
    }
    */
}