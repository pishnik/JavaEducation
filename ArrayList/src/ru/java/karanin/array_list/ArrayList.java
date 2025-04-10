package ru.java.karanin.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    // массив элементов
    private E[] items;
    // размер массива
    private int size;
    // количество изменений
    private int changesCount;

    // пустой массив с определенной вместимостью
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format("Вместимость списка не может быть меньше 0, передано значение %d", capacity));
        }

        // создали массив объектов с размером-вместимостью
        items = (E[]) new Object[capacity];
    }

    // создаем пустой массив размерностью 256 элементов
    public ArrayList() {
        this(25);
    }

    // увеличение вместимости если требуется
    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            // вдвое увеличиваем вместимость
            int newCapacity = Math.max(capacity, items.length * 2);
            // копируем элементы в список с новой вместимостью
            items = Arrays.copyOf(items, newCapacity);
        }
    }

    // усечение вместимости
    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public int size() {
        // возвращаем размер списка
        return size;
    }

    @Override
    public boolean isEmpty() {
        // если размер 0
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        // саму items отдать не можем, а то набедокурят, отдадим копию
        return Arrays.copyOf(items, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // если наш массив больше чем тот что нам дали
        if (size > a.length) {
            // отдадим новый
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        // копируем наш список в переданный массив
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        // Если массив имеет больше элементов, чем список,
        // элемент в массиве, следующий сразу за концом списка, устанавливается в значение null
        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        // если нужно увеличим вместимость
        ensureCapacity(size + 1);
        // элемент присвоили в конец
        items[size] = e;
        // размер увеличили
        size++;
        // увеличили количество изменений
        changesCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int itemIndex = indexOf(o);

        if (itemIndex == -1) {
            return false;
        }

        remove(itemIndex);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        // тут можно вставить в конец списка (index = size)
        checkIndexRange(index, size);

        if (c.isEmpty()) {
            return false;
        }

        // коллекция должна полностью влезть в список
        ensureCapacity(size + c.size());
        // смещаем элементы с индекса правее
        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        // технически могли бы через копирование массива
        // закинуть часть до индекса, переданный массив, закинуть часть после индекса
        // идем по элементам
        for (E object : c) {
            // накидываем элементы по очереди и двигаем индекс дальше
            items[i] = object;
            i++;
        }

        // размер увеличили на количество элементов коллекции
        size += c.size();
        changesCount++;

        // ну поменяли же
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        if (c.isEmpty()) {
            return false;
        }

        boolean isChanged = false;

        //  идем по элементам
        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Передана null коллекция");
        }

        boolean isChanged = false;

        //  идем по элементам
        for (int i = size - 1; i >= 0; i--) {
            // если элемент не входит в коллекцию
            if (!c.contains(items[i])) {
                // удалили
                remove(i);

                // было хотя бы одно удаление - список поменялся
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        // стираем элементы массива
        Arrays.fill(items, 0, size, null);
        // обнуляем размер
        size = 0;
        // увеличили количество изменений
        changesCount++;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index, size - 1);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndexRange(index, size - 1);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        // тут можно вставить в конец списка (index = size)
        checkIndexRange(index, size);

        // добавление влияет на вместимость, надо проверить
        ensureCapacity(size + 1);
        // смещаем элементы копированием вправо
        System.arraycopy(items, index, items, index + 1, size - index);
        // присваиваем элемент
        items[index] = item;
        // увеличиваем размер
        size++;
        // увеличили количество изменений
        changesCount++;
    }

    @Override
    public E remove(int index) {
        // проверка индекса
        checkIndexRange(index, size - 1);

        // запомнили элемент
        E oldItem = items[index];
        // сдвигаем элементы влево, копирование элементов
        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;

        // уменьшили размер
        size--;
        changesCount++;

        return oldItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Метод не реализован");
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (int i = 0; i < size; i++) {
            hash = prime * hash + ((items[i] != null) ? items[i].hashCode() : 0);
        }

        return hash;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        ArrayList<E> arrayList = (ArrayList<E>) object;

        // размеры должно быть равны
        if (arrayList.size != size) {
            return false;
        }

        // если хотя бы один не совпадет, вернем false
        for (int i = 0; i < size; i++) {
            // в коллекциях могут быть null
            // сравним через static
            if (!Objects.equals(items[i], arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.append(items[size - 1]).append('}');

        return stringBuilder.toString();
    }

    // для проверки индекса в методах
    private static void checkIndexRange(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Индекс может быть в диапазоне [0, %d], передано значение %d", maxIndex, index));
        }
    }

    // внутренний класс итератора
    private class ArrayListIterator implements Iterator<E> {
        // текущий элемент пустой
        private int currentIndex = -1;
        // запомнили количество изменений
        private final int initialChangesCount = changesCount;

        // есть ли следующий элемент
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        // переход на следующий элемент
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец списка");
            }

            if (changesCount != initialChangesCount) {
                throw new ConcurrentModificationException("Во время выполнения итератора список изменился");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }
}
