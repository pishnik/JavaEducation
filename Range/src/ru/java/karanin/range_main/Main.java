package ru.java.karanin.range_main;

import ru.java.karanin.range.Range;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Чтение значений с консоли
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите начало диапазона: ");
        double rangeBegin = scanner.nextDouble();

        System.out.print("Введите конец диапазона: ");
        double rangeEnd = scanner.nextDouble();

        // создаем диапазон
        Range range = new Range(rangeBegin, rangeEnd);
        range.print();
        System.out.printf("Длинна диапазона: %.2f%n", range.getLength());

        System.out.print("Введите число для проверки: ");
        double number = scanner.nextDouble();

        if (range.isInside(number)) {
            System.out.println("Число входит в диапазон!");
        } else {
            System.out.println("Число не входит в диапазон!");
        }

        System.out.print("Давайте сдвинем начало диапазона на: ");
        range.setFrom(range.getFrom() + scanner.nextDouble());

        System.out.print("Давайте сдвинем конец диапазона на: ");
        range.setTo(range.getTo() + scanner.nextDouble());

        range.print();
        System.out.printf("Новая длинна диапазона: %.2f%n", range.getLength());
    }
}
