package ru.java.karanin.array_list_main;

import ru.java.karanin.array_list.ArrayList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Начинам работу со списком на массиве");

        ArrayList<String> strings1 = new ArrayList<>();

        System.out.print("Введите строку для добавления в список: ");
        String userString = scanner.nextLine();

        strings1.add("1");
        strings1.add("2");
        strings1.add(userString);
        strings1.add("4");
        strings1.add("5");
        System.out.println("Список: " + strings1);

        strings1.remove(1);
        System.out.println("Список: " + strings1);

        strings1.remove(userString);
        System.out.println("Список: " + strings1);

        // проверка итератора
        for (String string : strings1) {
            System.out.println(string);
        }

        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("Раз");
        strings2.add("Два");
        strings2.add("Три");

        strings1.addAll(strings2);
        System.out.println("Список: " + strings1);

        strings1.addAll(0, strings2);
        System.out.println("Список: " + strings1);


        // как только мы усекаем размерность массива,
        // items[size] в remove будет приводить к ошибке,
        // так как не будет элемента на позиции size (size = length)
        strings1.trimToSize();

        ArrayList<String> strings3 = new ArrayList<>();
        strings3.add("Три");
        strings3.add("Пять");

        strings1.removeAll(strings3);
        System.out.println("Список: " + strings1);

        strings1.retainAll(strings2);
        System.out.println("Список: " + strings1);

        strings2.clear();
        strings1.retainAll(strings2);
        System.out.println("Список: " + strings1);
    }
}