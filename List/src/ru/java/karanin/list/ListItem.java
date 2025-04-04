package ru.java.karanin.list;

class ListItem<E> {
    // значение
    private E data;

    // ссылка на следующий элемент списка
    private ListItem<E> nextItem;

    public ListItem(E data) {
        this.data = data;
    }

    public ListItem(E data, ListItem<E> nextItem) {
        this.data = data;
        this.nextItem = nextItem;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public ListItem<E> getNextItem() {
        return nextItem;
    }

    public void setNextItem(ListItem<E> nextItem) {
        this.nextItem = nextItem;
    }
}
