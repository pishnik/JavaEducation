package ru.java.karanin.array_list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    // массив элементов
    private Object[] items;
    // размер массива
    private int size;

    // пустой массив с определенной вместимостью
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format("Вместимость списка должна быть больше 0, передано %d", capacity));
        }

        // создали массив объектов с размером-вместимостью
        items = new Object[capacity];
        // массив пустой там нет элементов, есть только места для них
        size = 0;
    }

    // создаем пустой массив размерностью 256 элементов
    public ArrayList() {
        this(256);
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
    public <T> T[] toArray(T[] a) {
        // если наш массив больше чем тот что нам дали
        if (size > a.length) {
            // отдадим новый
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        // копируем наш список в переданный массив
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
    public boolean addAll(Collection<? extends E> c) {
        // пустота
        if (c == null) {
            // список не изменился
            return false;
        }

        // без элементов
        if (c.size() == 0) {
            // список не изменился
            return false;
        }

        // накидываем элементы
        for (E object : c) {
            add(object);
        }

        // говорим что список изменился, а как он мог не поменяться...
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndexRange(index);

        if (c == null) {
            return false;
        }

        if (c.size() == 0) {
            return false;
        }

        int currentIndex = index;

        // технически могли бы через копирование массива
        // закинуть часть до индекса, переданный массив, закинуть часть после индекса
        // идем по элементам
        for (E object : c) {
            // накидываем элементы по очереди и двигаем индекс дальше
            add(currentIndex, object);
            currentIndex++;
        }

        // ну поменяли же
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        if (c.size() == 0) {
            return false;
        }

        boolean result = false;

        for (Object object : c) {
            // если удаление было
            if (remove(object)) {
                // список поменялся
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        if (c.size() == 0) {
            return false;
        }

        boolean result = false;
        //  идем по элементам
        for (int i = 0; i < size; i++) {
            // если элемент не входит в коллекцию
            if (!c.contains(items[i])) {
                // удалили
                remove(i);
                i--;

                // было хотя бы одно удаление - список поменялся
                if (!result) {
                    result = true;
                }
            }
        }

        return result;
    }

    @Override
    public void clear() {
        // стираем элементы массива
        Arrays.fill(items, 0, size, null);
        // обнуляем размер
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndexRange(index);

        return (E) items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndexRange(index);

        E oldItem = (E) items[index];
        items[index] = element;

        return oldItem;
    }

    @Override
    public void add(int index, E element) {
        checkIndexRange(index);

        // добавление влияет на вместимость, надо проверить
        ensureCapacity(size + 1);
        // смещаем элементы копированием вправо
        System.arraycopy(items, index, items, index + 1, size - index);
        // присваиваем элемент
        items[index] = element;
        // увеличиваем размер
        size++;
    }

    @Override
    public E remove(int index) {
        // проверка индекса
        checkIndexRange(index);

        // запомнили элемент
        E oldItem = (E) items[index];
        // сдвигаем элементы влево, копирование элементов
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        // нужно затереть последний элемент
        items[size] = null;
        // уменьшили размер
        size--;

        return oldItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
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
        // жульничаем - используем от массива
        return Arrays.hashCode(items);
    }

    @Override
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
            if (!items[i].equals(arrayList.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        if (size > 0) {
            for (int i = 0; i < size - 1; i++) {
                stringBuilder.append(items[i].toString()).append(", ");
            }

            stringBuilder.append(items[size - 1].toString());
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    // для проверки индекса в методах
    private void checkIndexRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Индекс может быть в диапазоне [0, %d], передано %d", size - 1, index));
        }
    }

    // внутренний класс итератора
    private class ArrayListIterator implements Iterator<E> {
        // текущий элемент пустой
        private int currentIndex = -1;

        // есть ли следующий элемент
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        // переход на следующий элемент
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Достигнут конец списка");
            }

            currentIndex++;

            return (E) items[currentIndex];
        }
    }
}
