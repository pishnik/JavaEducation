package ru.java.karanin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] items;

    private int size;

    public HashTable(int itemsCount) {
        if (itemsCount <= 0) {
            throw new IllegalArgumentException(String.format("Размер хеш-таблицы должен быть больше 0, передано %d", itemsCount));
        }

        items = new ArrayList[itemsCount];

        for (int i = 0; i < itemsCount; i++) {
            items[i] = new ArrayList<>();
        }
    }

    public HashTable() {
        this(10);
    }

    private int getItemIndex(E o) {
        if (o == null) {
            throw new IllegalArgumentException("Таблица не поддерживает работу с null");
        }

        return Math.abs(o.hashCode() % items.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return items.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            // у нас нет null
            return false;
        }

        int index = getItemIndex((E) o);

        return items[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        // массив объектов
        Object[] array = new Object[items.length];

        // индекс
        int i = 0;

        // бегаем по массиву списков и набираем элементы в массив объектов
        for (ArrayList<E> item : items) {
            for (int j = 0; j < item.size(); j++, i++) {
                array[i] = item.get(j);
            }
        }

        return array;
    }

    @Override
    public boolean add(E o) {
        // считаем индекс
        int index = getItemIndex(o);

        // проверяем в нет ли в таком списке уже такого элемента
        if (!items[index].contains(o)) {
            // добавили
            items[index].add(o);
            // увеличили размер
            size++;

            // было добавление
            return true;
        }

        // ничего не добавили
        return false;
    }

    @Override
    public boolean remove(Object o) {
        // считаем индекс
        int index = getItemIndex((E) o);

        if (items[index].remove(o)) {
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        if (c == null) {
            return false;
        }

        if (c.size() == 0) {
            return false;
        }

        boolean result = false;

        for (Object element : c) {
            if (add((E) element)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public void clear() {
        // стерли элементы в массиве
        for (ArrayList<E> item : items) {
            item.clear();
        }

        // обнулили длину
        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // ну и не надо ничего удалять тогда
        if (c == null) {
            return false;
        }

        // ну и не надо ничего удалять тогда
        if (c.size() == 0) {
            return false;
        }

        boolean result = false;

        size = 0;
        // идем по спискам и сносим элементы, что не входят в коллекцию
        for (ArrayList<E> list : items) {
            if (list.retainAll(c)) {
                result = true;
                size = size + list.size();
            }

            // нам надо пересчитать длину после операции
            size = size + list.size();
        }

        return result;
    }

    @Override
    public boolean removeAll(Collection c) {
        // пустоту и удалять не надо
        if (c == null) {
            return false;
        }

        if (c.size() == 0) {
            return false;
        }

        boolean result = false;

        for (Object element : c) {
            if (remove(element)) {
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean containsAll(Collection c) {
        // если нам дали null
        if (c == null) {
            return false;
        }

        // если дали пустую коллекцию
        if (c.size() == 0) {
            return true;
        }

        // идем по коллекции и смотри есть ли в нашем списке такой объект
        for (Object object : c) {
            if (!contains(object)) {
                // если объекта не нашлось
                return false;
            }
        }

        // все объекты были найдены в списке
        return true;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // если наш массив больше чем тот что нам дали
        if (size > a.length) {
            T[] array = (T[]) new Object[size];
            int i = 0;

            for (E element : this) {
                array[i] = (T) element;
            }

            return array;
        }

        T[] array = (T[]) this.toArray();
        // копируем наш список в переданный массив
        System.arraycopy(array, 0, a, 0, size);

        // Если массив имеет больше элементов, чем список,
        // элемент в массиве, следующий сразу за концом списка, устанавливается в значение null
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[").append(System.lineSeparator());

        if (items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                stringBuilder.append(' ').append(items[i]).append(System.lineSeparator());
            }
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    // внутренний класс итератора
    private class HashTableIterator implements Iterator<E> {
        private int currentIndex = 0;

        private Iterator<E> itemsIterator = items[currentIndex].iterator();

        public boolean hasNext() {
            while (currentIndex < items.length - 1) {
                if (itemsIterator.hasNext()) {
                    return true;
                }

                currentIndex++;
                itemsIterator = items[currentIndex].iterator();
            }
            return itemsIterator.hasNext();
        }

        // переход на следующий элемент
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец");
            }

            currentIndex++;

            return itemsIterator.next();
        }
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (E element : this) {
            hash = prime * hash + element.hashCode();
        }

        return hash;
    }

    public boolean equals(Object object) {
        // этот же объект
        if (object == this) {
            return true;
        }

        // пустота или передали другой класс
        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        HashTable<E> hashTableObject = (HashTable<E>) object;

        // размеры не совпадают
        if (hashTableObject.size != size) {
            return false;
        }

        // если получили несовпадение, точно не равны
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(hashTableObject.items[i], items[i])) {
                return false;
            }
        }

        return true;
    }
}