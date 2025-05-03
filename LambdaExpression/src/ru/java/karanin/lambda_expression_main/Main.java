package ru.java.karanin.lambda_expression_main;

import ru.java.karanin.lambda_expression_person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Person> personsList = List.of(
                new Person("Кирилл", 16),
                new Person("Максим", 39),
                new Person("Иван", 29),
                new Person("Сергей", 12),
                new Person("Сергей", 42),
                new Person("Денис", 15),
                new Person("Денис", 25),
                new Person("Александр", 19),
                new Person("Андрей", 25),
                new Person("Никита", 17),
                new Person("Игнат", 13),
                new Person("Роман", 22),
                new Person("Павел", 30));
        System.out.println();

        System.out.println("Исходный список людей:");
        personsList.forEach(person -> System.out.printf("%s: %s%n", person.getFirstName(), person.getAge()));
        System.out.println();

        System.out.println("Уникальные имена людей:");
        List<String> uniqueNames = personsList.stream()
                .map(Person::getFirstName)
                .distinct()
                .toList();
        uniqueNames.forEach(person -> System.out.println(person + " "));
        System.out.println();

        System.out.println("Строка с уникальными именами людей:");
        String uniqueNamesString = uniqueNames.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(uniqueNamesString);
        System.out.println();

        System.out.println("Список несовершеннолетних:");
        List<Person> underagePersonsList = personsList.stream()
                .filter(person -> person.getAge() < 18)
                .toList();
        underagePersonsList.forEach(person -> System.out.println(person.getFirstName()));
        System.out.println();

        underagePersonsList.stream()
                .mapToInt(Person::getAge)
                .average()
                .ifPresentOrElse(value -> System.out.printf("Средний возраст несовершеннолетних: %.2f%n", value), () -> System.out.println("В списке нет несовершеннолетних"));
        System.out.println();

        System.out.println("Список уникальных имен и средний возраст:");
        Map<String, Double> uniqueNamesAverageAges = personsList.stream()
                .collect(Collectors.groupingBy(Person::getFirstName, Collectors.averagingInt(Person::getAge)));
        uniqueNamesAverageAges.forEach((firstName, averageAge) -> System.out.println(firstName + " = " + averageAge));
        System.out.println();

        System.out.println("Молодежь:");
        personsList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .forEach(person -> System.out.println(person.getFirstName()));
        System.out.println();

        // бесконечные потоки
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число элементов для вывода: ");
        int numbersCount = scanner.nextInt();

        System.out.println("Квадратные корни чисел:");
        DoubleStream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(numbersCount)
                .forEach(sqrt -> System.out.printf("%.2f%n", sqrt));
        System.out.println();

        System.out.printf("Первые %d чисел Фибоначчи:%n", numbersCount);
        printFibonacciNumbers(numbersCount);
    }

    public static void printFibonacciNumbers(int numbersCount) {
        //Stream.iterate(new int[]{1, 0, 1}, numbers -> new int[]{numbers[0], numbers[2], numbers[1] + numbers[2]}).limit(numbersCount).forEach(numbers -> System.out.print(numbers[1] + " "));
        int[] pair = {0, 1};
        IntStream.iterate(0, number -> {
                    int prevNumber = pair[1];
                    int nextNumber = pair[0] + pair[1];
                    pair[0] = pair[1];
                    pair[1] = nextNumber;

                    return prevNumber;
                })
                .limit(numbersCount)
                .forEach(number -> System.out.print(number + " "));
    }
}