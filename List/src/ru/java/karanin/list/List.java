package ru.java.karanin.list;

public class List<T> {
    // начало списка
    private ListItem<T> head;

    // размер списка
    private int size;

    public List() {
        head = null;
        size = 0;
    }

    // размер списка
    public int getSize() {
        return size;
    }

    // хочу что бы можно было просто накидывать значения
    public void add(T itemData) {
        ListItem<T> newItem = new ListItem<>(itemData);
        addItemByIndex(size, newItem);
    }

    // получить значение 1 элемента
    public T getFirstData() {
        if (head != null) {
            return head.getData();
        } else {
            return null;
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
        // встали на первый элемент списка
        ListItem<T> current = head;

        // пошли с начала списка до нужного элемента
        for (int i = 0; i < index; i++) {
            current = current.getNextItem();
        }

        return current.getData();
    }

    // установить значение элемента по индексу
    public T setDataByIndex(int index, T newData) {
        // проверили границы
        checkIndex(index);

        ListItem<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNextItem();
        }

        T oldData = current.getData();
        current.setData(newData);

        return oldData;
    }

    public void addFirstItem(ListItem<T> item) {
        //элементу присваиваем ссылку на начало списка
        item.setNextItem(head);
        // началом устанавливаем новый элемент
        head = item;
        // увеличили размер списка
        size++;
    }

    public void addItemByIndex(int index, ListItem<T> item) {
        // проверили границы
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.format("Элемента с индексом %d не существует, индекс может быть в диапазоне [0, %d]", index, size));
        }

        // если передали начало добавим в начало
        if (index == 0) {
            addFirstItem(item);
            return;
        }

        // встаем на начало
        ListItem<T> previousItem = head;

        // идем до предыдущего
        for (int i = 0; i < index - 1; i++) {
            previousItem = previousItem.getNextItem();
        }

        // перебили ссылки
        item.setNextItem(previousItem.getNextItem());
        previousItem.setNextItem(item);
        size++;
    }

    // удаление первого элемента
    public T deleteFirstItem() {
        // пустой список
        if (head == null) {
            return null;
        }

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
    public T deleteItemByIndex(int index) {
        // проверили границы
        checkIndex(index);

        // удаляем первый элемент
        if (index == 0) {
            return deleteFirstItem();
        }

        // начинаем проход по списку с начала
        ListItem<T> previousItem = head;

        // идем в цикле до предыдущего элемента
        // нам в нем надо будет поменять ссылку на следующий за удаляемым
        for (int i = 0; i < index - 1; i++) {
            previousItem = previousItem.getNextItem();
        }

        // получили значение
        T data = previousItem.getNextItem().getData();
        // ставим ссылку предыдущему на следующий за удаляемым
        previousItem.setNextItem(previousItem.getNextItem().getNextItem());
        // укорачиваем список
        size--;
        // возвращаем значение
        return data;
    }

    // удалить элемент по индексу
    public boolean deleteItemByData(T data) {
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
            if (itemData.equals(data)) {
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
        List<T> newList = new List<>();

        // если там ничего, пустоту и вернем
        if (size == 0) {
            return newList;
        }

        // размер
        newList.size = size;
        // создаем копию головы
        newList.head = new ListItem<>(head.getData());

        ListItem<T> newItem = newList.head;
        ListItem<T> item = head.getNextItem();

        // пока элемент оригинала не пустой
        while (item != null) {
            // элементу нового списка присваиваем значение из оригинала
            newItem.setNextItem(new ListItem<>(item.getData()));
            // переходим на следующий элемент
            newItem = newItem.getNextItem();
            // переходим на следующий элемент оригинала
            item = item.getNextItem();
        }

        return newList;
    }

    public void reverse() {
        ListItem<T> item = head;
        ListItem<T> previousItem = null;
        ListItem<T> nextItem;

        // пока есть элементы
        while (item != null) {
            // запоминаем следующий
            nextItem = item.getNextItem();
            // перебиваем следующий текущего на предыдущий
            item.setNextItem(previousItem);
            // в предыдущий кладем текущий
            previousItem = item;
            // в текущий следующий
            item = nextItem;
        }

        head = previousItem;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        ListItem<T> item = head;

        while (item != null) {
            stringBuilder.append(item.getData().toString());
            item = item.getNextItem();

            if (item != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }
}