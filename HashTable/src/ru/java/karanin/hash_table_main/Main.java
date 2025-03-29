package ru.java.karanin.hash_table_main;

import ru.java.karanin.hash_table.HashTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HashTable<String> strings = new HashTable<>();

        strings.add("Раз");
        strings.add("Раз");
        strings.add("Раз");
        strings.add("Два");
        strings.add("Три");
        strings.add("Три");
        strings.add("Четыре");
        strings.add("Пять");
        strings.add(null);
        strings.add("Шесть");
        strings.add("Семь");
        strings.add(null);
        strings.add("Восемь");
        strings.add("Девять");
        strings.add("Десять");

        System.out.println("Содержимое Hash Table:");
        System.out.println(strings);
        System.out.println("Размер Hash Table: " + strings.size());

        Object[] elements = strings.toArray();

        for (Object element : elements) {
            System.out.println(element);
        }

        System.out.println("Размер массива: " + strings.toArray().length);

        String[] array = new String[12];
        strings.toArray(array);

        for (Object element : array) {
            System.out.println(element);
        }

        strings.remove("Десять");
        System.out.println(strings);

        ArrayList<String> list = new ArrayList<>();
        list.add("Раз");
        list.add("Три");

        System.out.println(strings.containsAll(list));

        if (strings.retainAll(list)) {
            System.out.println(strings);
        }

        System.out.println("Размер Hash Table: " + strings.size());
        list.clear();
        list.add("Сто");
        list.add("Двести");
        strings.addAll(list);
        System.out.println(strings);

        System.out.println(strings.hashCode());

        for (String string : strings) {
            System.out.println(string);
        }
    }
}