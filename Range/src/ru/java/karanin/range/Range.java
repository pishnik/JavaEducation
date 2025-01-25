package ru.java.karanin.range;

public class Range {

    private double from;
    private double to;

    public Range(double from, double to) {
        checkRange(from, to);

        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        checkRange(from, to);
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        checkRange(from, to);
        this.to = to;
    }

    public boolean isInside(double number) {
        return number >= from && to >= number;
    }

    public void print() {
        System.out.printf("Диапазон: [%.2f; %.2f]%n", from, to);
    }

    public double getLength() {
        return to - from;
    }

    // делаем статичным и доступным извне, для того что бы можно было проверить любые диапазоны
    public static void checkRange(double from, double to) {
        if (from > to) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Начало (%.2f) должно быть меньше или равно концу (%.2f).", from, to));
        }
    }
}
