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
        System.out.printf("Интервал: [%.2f; %.2f]%n", from, to);
    }

    public double getLength() {
        return to - from;
    }

    private static boolean checkIntersection(Range range1, Range range2) {
        if (range2.from > range1.to || range2.to < range1.from) {
            return false;
        }
        if (Math.max(range1.from, range2.from) == Math.min(range1.to, range2.to)) {
            return false;
        }

        return true;
    }

    public Range getIntersection(Range range) {
        if (range.from > to || range.to < from) {
            return null;
        } else {
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }
    }

    public Range[] join(Range range) {
        if (range.from > to || range.to < from) {
            return new Range[]{this, range};
        } else {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        }
    }

    // делаем статичным и доступным извне, для того что бы можно было проверить любые диапазоны
    public static void checkRange(double from, double to) {
        if (from > to) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Начало (%.2f) должно быть меньше или равно концу (%.2f).", from, to));
        }
    }
}
