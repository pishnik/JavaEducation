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

    @Override
    public String toString() {
        return String.format("[%.2f; %.2f]", from, to);
    }

    public double getLength() {
        return to - from;
    }

    // Получение пересечения
    public Range getIntersection(Range range) {
        // сравниваем границы
        if (range.from >= to || range.to <= from) {
            // не пересекаются (но границы ВКЛ)
            return null;
        }

        // отдали пересечение
        return new Range(Math.max(from, range.from), Math.min(to, range.to));
    }

    public Range[] getUnion(Range range) {
        // не пересекаются
        if (range.from > to || range.to < from) {
            // отдали 2 интервала
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        // отдали 1 расширенный интервал
        return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
    }

    public Range[] getDifference(Range range) {
        if (range.from >= to || range.to <= from) {
            // не пересекаются
            return new Range[]{new Range(from, to)};
        }

        if (range.from > from && range.to < to) {
            // второй внутри первого
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (range.to < to) { // избыточно && range.to > from
            // перекрытие слева
            return new Range[]{new Range(range.to, to)};
        }

        if (range.from > from) {// избыточно  && range.from < to
            // перекрытие справа
            return new Range[]{new Range(from, range.from)};
        }

        // прочее
        return new Range[]{};
    }

    // делаем статичным, для того что бы можно было проверить любые диапазоны
    private static void checkRange(double from, double to) {
        if (from > to) {
            throw new IllegalArgumentException(String.format("Ошибка ввода! Начало (%.2f) должно быть меньше или равно концу (%.2f).", from, to));
        }
    }
}
