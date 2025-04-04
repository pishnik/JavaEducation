package ru.java.karanin.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class List<E> {
    // начало списка
    private ListItem<E> head;

    // размер списка
    private int size;

    // пустой конструктор
    public List() {
    }

    // размер списка
    public int getSize() {
        return size;
    }

    private ListItem<E> getItemByIndex(int index) {
        ListItem<E> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNextItem();
        }

        return currentItem;
    }

    // тупо вставка в конец списка, все это можно упростить заведя ссылку на конец списка tail
    // где можно хранить ссылку на конечный элемент
    public void add(E data) {
        // сейчас мы просто оборачиваем вставку по индексу длинны
        addByIndex(size, data);
    }

    // получить значение 1 элемента
    public E getFirst() {
        checkListEmpty();

        return head.getData();
    }

    private void checkListEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст");
        }
    }

    // проверка индекса элемента
    private void checkIndex(int index) {
        int maxIndex = size - 1;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Элемента с индексом %d не существует, индекс может быть в диапазоне [0, %d]", index, maxIndex));
        }
    }

    // получить значение элемента по индексу
    public E getDataByIndex(int index) {
        // проверили границы
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    // установить значение элемента по индексу
    public E setDataByIndex(int index, E newData) {
        // проверили границы
        checkIndex(index);

        ListItem<E> item = getItemByIndex(index);
        E oldData = item.getData();
        item.setData(newData);

        return oldData;
    }

    public void addFirst(E data) {
        // создали элемент, началом устанавливаем новый элемент со ссылкой на старый head
        head = new ListItem<>(data, head);
        // увеличили размер списка
        size++;
    }

    public void addByIndex(int index, E data) {
        // проверили границы
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Элемента с индексом %d не существует, индекс может быть в диапазоне [0, %d]", index, size));
        }

        // если передали начало добавим в начало
        if (index == 0) {
            addFirst(data);
            return;
        }

        // берем предыдущий
        ListItem<E> previousItem = getItemByIndex(index - 1);
        // создали элемент, у него ссылка на следующий, а на него ссылается предыдущий
        previousItem.setNextItem(new ListItem<>(data, previousItem.getNextItem()));
        // увеличили размер
        size++;
    }

    // удаление первого элемента
    public E deleteFirst() {
        checkListEmpty();

        // получили значение головы
        E data = head.getData();
        // переписали ссылку на голову из ссылки головы
        head = head.getNextItem();
        // уменьшили размер
        size--;
        // вернули значение
        return data;
    }

    // удалить элемент по индексу
    public E deleteByIndex(int index) {
        // проверили границы
        checkIndex(index);

        // удаляем первый элемент
        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem = getItemByIndex(index - 1);
        // получили значение
        E data = previousItem.getNextItem().getData();
        // ставим ссылку предыдущему на следующий за удаляемым
        previousItem.setNextItem(previousItem.getNextItem().getNextItem());
        // укорачиваем список
        size--;
        // возвращаем значение
        return data;
    }

    // удалить элемент по значению
    public boolean deleteByData(E data) {
        if (size == 0) {
            return false;
        }

        // начинаем проход по списку с начала
        ListItem<E> item = head;
        // предыдущий у начала пустой
        ListItem<E> previousItem = null;

        // пока не пусто
        while (item != null) {
            // получаем значение
            E itemData = item.getData();

            // если значение нужное
            if (Objects.equals(itemData, data)) {
                // если предыдущего нет
                if (previousItem == null) {
                    // переписываем голову
                    head = head.getNextItem();
                } else {
                    // иначе в предыдущий пишем следующий удаляемого
                    previousItem.setNextItem(item.getNextItem());
                }

                // уменьшаем размер
                size--;

                // возвращаем успех
                return true;
            }

            // переходим на следующие элементы
            previousItem = item;
            item = item.getNextItem();
        }

        // ничего не обнаружили
        return false;
    }

    public List<E> copy() {
        // создали пустой список
        List<E> copyList = new List<>();

        // если там ничего, пустоту и вернем
        if (size == 0) {
            return copyList;
        }

        // размер
        copyList.size = size;
        // создаем копию головы
        copyList.head = new ListItem<>(head.getData());
        // в новом списке встаем на первый элемент
        ListItem<E> copyListItem = copyList.head;
        // получаем следующий элемент оригинала
        ListItem<E> item = head.getNextItem();

        // пока элемент оригинала не пустой
        while (item != null) {
            // элементу нового списка присваиваем значение из оригинала
            copyListItem.setNextItem(new ListItem<>(item.getData()));
            // переходим на следующий элемент копии
            copyListItem = copyListItem.getNextItem();
            // переходим на следующий элемент оригинала
            item = item.getNextItem();
        }

        return copyList;
    }

    public void reverse() {
        // нужно более одного элемента
        if (size <= 1) {
            return;
        }

        // начинаем с первого элемента
        ListItem<E> currentItem = head;
        // предыдущий с начала будет пустым
        ListItem<E> previousItem = null;

        // пока в списке есть элементы
        while (currentItem != null) {
            // следующий элемент
            ListItem<E> nextItem = currentItem.getNextItem();
            // устанавливаем предыдущий
            currentItem.setNextItem(previousItem);
            // предыдущий  = текущему
            previousItem = currentItem;
            // текущий = следующий
            currentItem = nextItem;
        }

        head = previousItem;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");

        for (ListItem<E> item = head; item != null; item = item.getNextItem()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}