package ru.java.karanin.list;

public class ListItem<T> {
    // значение
    private T data;

    // ссылка на следующий элемент списка
    private ListItem<T> nextItem;

    public ListItem(T data) {
        this.data = data;
    }

    public ListItem(T data, ListItem<T> nextItem) {
        this.data = data;
        this.nextItem = nextItem;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListItem<T> getNextItem() {
        return nextItem;
    }

    public void setNextItem(ListItem<T> nextItem) {
        this.nextItem = nextItem;
    }
}
