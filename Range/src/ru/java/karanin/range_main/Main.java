package ru.java.karanin.range_main;

import ru.java.karanin.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Чтение значений с консоли
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало интервала: ");
        double range1From = scanner.nextDouble();

        System.out.print("Введите конец интервала: ");
        double range1To = scanner.nextDouble();

        // создаем диапазон
        Range range1 = new Range(range1From, range1To);
        // вывод с заголовком
        range1.print();

        System.out.printf("Длинна интервала: %.2f%n", range1.getLength());

        System.out.print("Введите число для проверки: ");
        double number = scanner.nextDouble();

        if (range1.isInside(number)) {
            System.out.println("Число входит в интервал!");
        } else {
            System.out.println("Число не входит в интервал!");
        }

        System.out.print("Давайте сдвинем начало интервала на: ");
        range1.setFrom(range1.getFrom() + scanner.nextDouble());

        System.out.print("Давайте сдвинем конец интервала на: ");
        range1.setTo(range1.getTo() + scanner.nextDouble());

        range1.print();
        System.out.printf("Новая длинна интервала: %.2f%n", range1.getLength());

        System.out.print("Введите начало еще одного интервала: ");
        double range2From = scanner.nextDouble();

        System.out.print("Введите конец еще одного интервала: ");
        double range2To = scanner.nextDouble();

        Range range2 = new Range(range2From, range2To);

        Range intersection = range1.getIntersection(range2);

        if (intersection != null) {
            System.out.println("Пересечение интервалов: " + intersection);
        } else {
            System.out.println("Пересечения интервалов нет!");
        }

        Range[] union = range1.getUnion(range2);
        System.out.println("Объединение интервалов:");

        for (Range range : union) {
            System.out.println(range);
        }

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Второй интервал полностью перекрывает первый!");
        } else {
            System.out.println("Разница интервалов:");

            for (Range range : difference) {
                System.out.println(range);
            }
        }
    }
}
