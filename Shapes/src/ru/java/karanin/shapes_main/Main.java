package ru.java.karanin.shapes_main;

import ru.java.karanin.shapes.*;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Начинаем представление!");
        // создаем массив фигур, для удобства чтения - каждая фигура на новой строке
        Shape[] shapes = new Shape[]{new Square(4)
                , new Square(7)
                , new Square(3)
                , new Rectangle(3, 4)
                , new Rectangle(4, 2)
                , new Circle(4.5)
                , new Circle(6)
                , new Triangle(-6, 1, 2, 4, 2, -2)
                , new Triangle(-3, -2, 0, -1, -2, 5)
                , new Triangle(4, 4, -6, -1, -2, -4)
        };

        // отсортировали по площади
        Arrays.sort(shapes, new SortByArea());
        // фигура с индексом 0 имеет самую большую площадь
        System.out.println();
        System.out.println("Фигура с самой большой площадью: " + shapes[0].toString());
        System.out.printf("Высота этой фигуры:  %.2f%n", shapes[1].getHeight());
        System.out.println("Сортировка по площади:");

        // распечатали для проверки
        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }

        // вторая фигура по размеру периметра
        // отсортировали по периметру
        Arrays.sort(shapes, new SortByPerimeter());
        // фигура с индексом 1 имеет второй по размеру периметр
        System.out.println();
        System.out.println("Вторая фигура по размеру периметра: " + shapes[1].toString());
        System.out.printf("Ширина этой фигуры:  %.2f%n", shapes[1].getWidth());
        System.out.println("Сортировка по периметру:");

        // распечатали для проверки
        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }

        System.out.println();
        System.out.println("Сравнение фигур:");

        Shape shape1 = new Square(5.5);
        System.out.println(shape1);
        Shape shape2 = new Square(4);
        System.out.println(shape2);

        if (shape1.equals(shape2)) {
            System.out.println("Фигуры равны!");
        } else {
            System.out.println("Фигуры не равны!");
        }

        Shape shape3 = new Square(5);
        System.out.println(shape3);
        Shape shape4 = new Square(5);
        System.out.println(shape4);

        if (shape3.equals(shape4)) {
            System.out.println("Фигуры равны!");
        } else {
            System.out.println("Фигуры не равны!");
        }
    }
}

class SortByArea implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        double shape1Area = shape1.getArea();
        double shape2Area = shape2.getArea();

        return (int) (shape2Area - shape1Area);
    }
}

class SortByPerimeter implements Comparator<Shape> {
    public int compare(Shape shape1, Shape shape2) {
        double shape1Perimeter = shape1.getPerimeter();
        double shape2Perimeter = shape2.getPerimeter();

        return Double.compare(shape2Perimeter, shape1Perimeter);
    }
}