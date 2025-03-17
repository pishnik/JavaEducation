package ru.java.karanin.list_main;

import ru.java.karanin.list.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Односвязный список!");

        List<Integer> list1 = new List<>();
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(1001);

        System.out.printf("Список: %s%n", list1);
        System.out.printf("Длина списка %d%n", list1.getSize());
        System.out.printf("Значение первого элемент списка: %s%n", list1.getFirst());

        List<String> list2 = new List<>();
        list2.add("One");
        list2.add("Two");
        list2.add("Three");
        list2.add("Four");
        // раз уже была возможность вставлять null,
        // но это не верно, так как в списке должен ожидаем тот тип, что указан при создании
        // лучше запрещать добавлять null и не искать его даже при удалении
        list2.add(null);
        list2.add("Five");
        System.out.printf("Список: %s%n", list2);
        System.out.printf("Длина списка %d%n", list2.getSize());
        System.out.printf("Значение последнего элемент списка: %s%n", list2.getDataByIndex(list2.getSize() - 1));

        list2.deleteByData("Three");
        System.out.printf("Список: %s%n", list2);

        list2.deleteByData("Five");
        list2.deleteByData("One");
        System.out.printf("Список: %s%n", list2);

        if (!list2.deleteByData("Six")) {
            System.out.printf("В списке: %s нет элемента %s%n", list2, "Six");
        }

        String data1 = list2.deleteByIndex(0);
        System.out.printf("Удалили элемент списка по индексу %d, его значение было %s%n", 0, data1);
        System.out.printf("Список: %s%n", list2);

        list2.add("Раз");
        list2.add("Два");
        list2.add("Три");
        list2.add("Десять");
        list2.add("Сорок");
        System.out.printf("Список: %s%n", list2);
        String data2 = list2.setDataByIndex(0, "Ноль");
        System.out.printf("Старое значение по индексу %d было %s%n", 0, data2);
        list2.addFirst("Супер ноль");
        System.out.printf("Список: %s%n", list2);
        String data3 = list2.deleteFirst();
        System.out.printf("Старое значение первого элемента было %s%n", data3);
        System.out.printf("Список: %s%n", list2);

        List<String> list3 = list2.copy();
        System.out.printf("Копия списка: %s%n", list3);

        list3.reverse();
        list3.deleteByData(null);
        System.out.printf("Список: %s%n", list3);
    }
}