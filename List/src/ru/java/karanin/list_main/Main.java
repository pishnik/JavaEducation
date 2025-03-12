package ru.java.karanin.list_main;

import ru.java.karanin.list.List;
import ru.java.karanin.list.ListItem;

public class Main {
    public static void main(String[] args) {
        System.out.println("Односвязный список!");

        List<Integer> list1 = new List<>();
        list1.addItemByIndex(list1.getSize(), new ListItem<>(0));
        list1.addItemByIndex(list1.getSize(), new ListItem<>(1));
        list1.addItemByIndex(list1.getSize(), new ListItem<>(2));
        list1.addItemByIndex(list1.getSize(), new ListItem<>(3));
        list1.addItemByIndex(list1.getSize(), new ListItem<>(1001));

        System.out.printf("Список: %s%n", list1);
        System.out.printf("Длина списка %d%n", list1.getSize());
        System.out.printf("Значение первого элемент списка: %s%n", list1.getFirstData());

        List<String> list2 = new List<>();
        list2.extend("One");
        list2.extend("Two");
        list2.extend("Three");
        list2.extend("Four");
        list2.extend("Five");
        System.out.printf("Список: %s%n", list2);
        System.out.printf("Длина списка %d%n", list2.getSize());
        System.out.printf("Значение последнего элемент списка: %s%n", list2.getDataByIndex(list2.getSize() - 1));

        list2.deleteItemByData("Three");
        System.out.printf("Список: %s%n", list2);

        list2.deleteItemByData("Five");
        list2.deleteItemByData("One");
        System.out.printf("Список: %s%n", list2);

        if (!list2.deleteItemByData("Six")) {
            System.out.printf("В списке: %s нет элемента %s%n", list2, "Six");
        }

        String data1 = list2.deleteItemByIndex(0);
        System.out.printf("Удалили элемент списка по индексу %d, его значение было %s%n", 0, data1);
        System.out.printf("Список: %s%n", list2);

        list2.extend("Раз");
        list2.extend("Два");
        list2.extend("Три");
        list2.extend("Десять");
        list2.extend("Сорок");
        System.out.printf("Список: %s%n", list2);
        String data2 = list2.setDataByIndex(0, "Ноль");
        System.out.printf("Старое значение по индексу %d было %s%n", 0, data2);
        list2.addFirstItem(new ListItem<>("Супер ноль"));
        System.out.printf("Список: %s%n", list2);
        String data3 = list2.deleteFirstItem();
        System.out.printf("Старое значение первого элемента было %s%n", data3);
        System.out.printf("Список: %s%n", list2);

        List<String> list3 = list2.copy();
        System.out.printf("Копия списка: %s%n", list3);

        list3.reverse();
        System.out.printf("Список: %s%n", list3);

        // !!! все летит к чертям
        // мы набили массив элементов со связями,
        // и при попытке пихнуть их в список мы пишем первый элемент и у него ставим следующим пустой "оригинал", теряя все связи
        // тут наверное нужен отдельный метод AddItems (который пробежит по цепочке и пихнет в конец ссылку из оригинала)
        // либо конструктор списка их элементов
        ListItem<Integer> list4Item0 = new ListItem(0,null);
        ListItem<Integer> list4Item1 = new ListItem(1,list4Item0);
        ListItem<Integer> list4Item2 = new ListItem(2,list4Item1);
        ListItem<Integer> list4Item3 = new ListItem(3,list4Item2);
        ListItem<Integer> list4Item4 = new ListItem(4,list4Item3);
        ListItem<Integer> list4Item5 = new ListItem(5,list4Item4);

        List<Integer> list4 = new List<>();
        list4.addFirstItem(list4Item5);
        System.out.printf("Список: %s%n", list4);
    }
}