package ru.java.karanin.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] lists;
    // количество элементов
    private int size;
    // количество изменений
    private int changesCount;

    @SuppressWarnings("unchecked")
    public HashTable(int tableCapacity) {
        if (tableCapacity <= 0) {
            throw new IllegalArgumentException(String.format("Размер хеш-таблицы должен быть больше 0, передано значение %d", tableCapacity));
        }

        lists = new ArrayList[tableCapacity];
    }

    public HashTable() {
        this(10);
    }

    private int getItemIndex(Object o) {
        // null значения будут лежать в 0 элементе массива
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public int size() {
        // количество элементов
        return size;
    }

    @Override
    public boolean isEmpty() {
        // если количество элементов равно 0
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = getItemIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        // массив объектов, размер = size таблицы
        Object[] array = new Object[size];

        // индекс
        int i = 0;

        // бегаем по массиву списков и набираем элементы в массив объектов
        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E item : list) {
                    array[i] = item;
                    i++;
                }
            }
        }

        return array;
    }

    @Override
    public boolean add(E o) {
        // считаем индекс
        int index = getItemIndex(o);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        // добавили по индексу элемент
        lists[index].add(o);
        // увеличили размер
        size++;
        changesCount++;

        // было добавление, таблица изменилась
        return true;
    }

    @Override
    public boolean remove(Object o) {
        // считаем индекс
        int index = getItemIndex(o);

        if (lists[index] == null) {
            return false;
        }

        if (lists[index].remove(o)) {
            size--;
            changesCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        if (c.isEmpty()) {
            return false;
        }

        for (E element : c) {
            add(element);
        }

        return true;
    }

    @Override
    public void clear() {
        // если размер 0, то нечего очищать
        if (size == 0) {
            return;
        }

        // стираем элементы в массиве
        for (ArrayList<E> list : lists) {
            // списка может не существовать
            if (list != null) {
                list.clear();
            }
        }

        // обнулили длину
        size = 0;
        changesCount++;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        // мы оставляем только то что внутри коллекции, а там нет ничего
        // сносим все содержимое
        if (c.isEmpty()) {
            if (size == 0) {
                return false;
            }

            clear();
            return true;
        }

        boolean isChanged = false;

        size = 0;

        // идем по спискам и сносим элементы, что не входят в коллекцию
        for (ArrayList<E> list : lists) {
            if (list != null) {
                if (list.retainAll(c)) {
                    changesCount++;
                    isChanged = true;
                }

                // нам надо пересчитать длину после операции
                size += list.size();
            }
        }

        return isChanged;
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean removeAll(Collection c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        if (c.isEmpty()) {
            return false;
        }

        // пока ничего не поменяли
        boolean isChanged = false;

        size = 0;

        // идем по спискам
        for (ArrayList<E> list : lists) {
            // если список есть
            if (list != null) {
                // из списка сносим все что передали
                if (list.removeAll(c)) {
                    // изменения были
                    changesCount++;
                    isChanged = true;
                }

                // нам надо пересчитать длину после операции
                size += list.size();
            }
        }

        return isChanged;
    }

    @Override
    public boolean containsAll(Collection c) {
        // если нам дали null
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
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
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public <T> T[] toArray(T[] a) {
        // если наш массив больше чем тот что нам дали
        if (size > a.length) {
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        // копируем наш список в переданный массив
        System.arraycopy(toArray(), 0, a, 0, size);

        // если массив имеет больше элементов, чем список,
        // элемент в массиве, следующий сразу за концом списка, устанавливается в значение null
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[").append(System.lineSeparator());

        for (ArrayList<E> list : lists) {
            stringBuilder.append(' ').append(list).append(System.lineSeparator());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    // внутренний класс итератора
    private class HashTableIterator implements Iterator<E> {
        // индекс элемента
        private int iterationIndex = -1;
        // индекс списка
        private int listIndex;
        // индекс элемента в списке
        private int itemIndex;

        private final int initialChangesCount = changesCount;

        public boolean hasNext() {
            return iterationIndex + 1 < size;
        }

        // переход на следующий элемент
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец таблицы");
            }

            if (changesCount != initialChangesCount) {
                throw new ConcurrentModificationException("Во время выполнения итератора таблица изменилась");
            }

            ++iterationIndex;
            ++itemIndex;
            E nextItem = null;

            while (listIndex < lists.length) {
                if (lists[listIndex] != null) {
                    if (itemIndex < lists[listIndex].size()) {
                        nextItem = lists[listIndex].get(itemIndex);
                        break;
                    }

                    itemIndex = 0;
                }

                ++listIndex;
            }

            return nextItem;
        }
    }
}