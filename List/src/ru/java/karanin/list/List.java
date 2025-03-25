package ru.java.karanin.list;

import java.util.Objects;

public class List<T> {
    // начало списка
    private ListItem<T> head;

    // размер списка
    private int size;

    // пустой конструктор
    public List() {
    }

    // размер списка
    public int getSize() {
        return size;
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> currentItem = head;

        for (int i = 0; i < index; i++) {
            currentItem = currentItem.getNextItem();
        }

        return currentItem;
    }

    // тупо вставка в конец списка, все это можно упростить заведя ссылку на конец списка tail
    // где можно хранить ссылку на конечный элемент
    public void add(T data) {
        // сейчас мы просто оборачиваем вставку по индексу длинны
        addByIndex(size, data);
    }

    // получить значение 1 элемента
    public T getFirst() {
        isEmpty();

        return head.getData();
    }

    // проверка индекса элемента
    private void isEmpty() {
        if (size == 0) {
            throw new IllegalStateException("Список пуст");
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
    public T getDataByIndex(int index) {
        // проверили границы
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    // установить значение элемента по индексу
    public T setDataByIndex(int index, T newData) {
        // проверили границы
        checkIndex(index);

        ListItem<T> item = getItemByIndex(index);
        T oldData = item.getData();
        item.setData(newData);

        return oldData;
    }

    public void addFirst(T data) {
        // создали элемент, началом устанавливаем новый элемент со ссылкой на старый head
        head = new ListItem<>(data, head);
        // увеличили размер списка
        size++;
    }

    public void addByIndex(int index, T data) {
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
        ListItem<T> previousItem = getItemByIndex(index - 1);
        // создали элемент, у него ссылка на следующий, а на него ссылается предыдущий
        previousItem.setNextItem(new ListItem<>(data, previousItem.getNextItem()));
        // увеличили размер
        size++;
    }

    // удаление первого элемента
    public T deleteFirst() {
        isEmpty();

        // получили значение головы
        T data = head.getData();
        // переписали ссылку на голову из ссылки головы
        head = head.getNextItem();
        // уменьшили размер
        size--;
        // вернули значение
        return data;
    }

    // удалить элемент по индексу
    public T deleteByIndex(int index) {
        // проверили границы
        checkIndex(index);

        // удаляем первый элемент
        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        // получили значение
        T data = previousItem.getNextItem().getData();
        // ставим ссылку предыдущему на следующий за удаляемым
        previousItem.setNextItem(previousItem.getNextItem().getNextItem());
        // укорачиваем список
        size--;
        // возвращаем значение
        return data;
    }

    // удалить элемент по значению
    public boolean deleteByData(T data) {
        if (size == 0) {
            return false;
        }

        // начинаем проход по списку с начала
        ListItem<T> item = head;
        // предыдущий у начала пустой
        ListItem<T> previousItem = null;

        // пока не пусто
        while (item != null) {
            // получаем значение
            T itemData = item.getData();

            // если значение нужное
            if (Objects.equals(itemData, data)) {
                // если предыдущего нет
                if (previousItem == null) {
                    // переписываем голову
                    head = item.getNextItem();
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

    public List<T> copy() {
        // создали пустой список
        List<T> copyList = new List<>();

        // если там ничего, пустоту и вернем
        if (size == 0) {
            return copyList;
        }

        // размер
        copyList.size = size;
        // создаем копию головы
        copyList.head = new ListItem<>(head.getData());
        // в новом списке встаем на первый элемент
        ListItem<T> itemCopyList = copyList.head;
        // получаем следующий элемент оригинала
        ListItem<T> item = head.getNextItem();

        // пока элемент оригинала не пустой
        while (item != null) {
            // элементу нового списка присваиваем значение из оригинала
            itemCopyList.setNextItem(new ListItem<>(item.getData()));
            // переходим на следующий элемент копии
            itemCopyList = itemCopyList.getNextItem();
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
        ListItem<T> currentItem = head;
        // предыдущий с начала будет пустым
        ListItem<T> previousItem = null;

        // пока в списке есть элементы
        while (currentItem != null){
            // следующий элемент
            ListItem<T> nextItem = currentItem.getNextItem();
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
        StringBuilder stringBuilder = new StringBuilder("{");

        ListItem<T> item;

        //for (item = head; item.getNextItem() != null; item = item.getNextItem()) {
        for (item = head; item != null; item = item.getNextItem()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        if (size > 0) {
            stringBuilder.replace(stringBuilder.length() - 2, stringBuilder.length(), "");
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}