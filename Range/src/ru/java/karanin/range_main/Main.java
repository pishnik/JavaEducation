package ru.java.karanin.range_main;

import ru.java.karanin.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Чтение значений с консоли
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало интервала: ");
        double rangeBegin = scanner.nextDouble();

        System.out.print("Введите конец интервала: ");
        double rangeEnd = scanner.nextDouble();

        // создаем диапазон
        Range range = new Range(rangeBegin, rangeEnd);
        range.print();

        System.out.printf("Длинна интервала: %.2f%n", range.getLength());

        System.out.print("Введите число для проверки: ");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число входит в интервал!");
        } else {
            System.out.println("Число не входит в интервал!");
        }

        System.out.print("Давайте сдвинем начало интервала на: ");
        range.setFrom(range.getFrom() + scanner.nextDouble());

        System.out.print("Давайте сдвинем конец интервала на: ");
        range.setTo(range.getTo() + scanner.nextDouble());

        range.print();
        System.out.printf("Новая длинна интервала: %.2f%n", range.getLength());

        System.out.print("Введите начало еще одного интервала: ");
        double anotherRangeBegin = scanner.nextDouble();

        System.out.print("Введите конец еще одного интервала: ");
        double anotherRangeEnd = scanner.nextDouble();

        Range anotherRange = new Range(anotherRangeBegin, anotherRangeEnd);

        Range intersection = range.getIntersection(anotherRange);
        if (intersection != null) {
            System.out.printf("Пересечение интервалов: [%.2f, %.2f]%n", intersection.getFrom(), intersection.getTo());
        } else {
            System.out.println("Пересечения интервалов нет!");
        }

        Range[] rangeUnion = range.join(anotherRange);
        System.out.println("Объединение интервалов:");
        for (Range value : rangeUnion) {
            value.print();
        }

        Range[] rangeDifference = range.getDifference(anotherRange);
        if (rangeDifference == null) {
            System.out.println("Второй интервал полностью перекрывает первый!");
        } else {
            System.out.println("Разница интервалов:");
            for (Range value : rangeDifference) {
                value.print();
            }
        }
    }
}
