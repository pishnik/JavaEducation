package ru.java.karanin.main;

import ru.java.karanin.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();

        personList.add(new Person("Кирилл", 16));
        personList.add(new Person("Максим", 39));
        personList.add(new Person("Иван", 29));
        personList.add(new Person("Сергей", 12));
        personList.add(new Person("Сергей", 42));
        personList.add(new Person("Денис", 15));
        personList.add(new Person("Денис", 25));
        personList.add(new Person("Александр", 19));
        personList.add(new Person("Андрей", 25));
        personList.add(new Person("Никита", 17));
        personList.add(new Person("Игнат", 13));
        personList.add(new Person("Роман", 22));
        personList.add(new Person("Павел", 30));
        System.out.println();

        System.out.println("Исходный список людей:");
        personList.forEach(person -> System.out.printf("%s: %s%n", person.getFirstName(), person.getAge()));
        System.out.println();

        System.out.println("Уникальные имена людей:");
        List<String> uniqueNames = personList.stream().map(Person::getFirstName).distinct().toList();
        uniqueNames.forEach(person -> System.out.println(person + " "));
        System.out.println();

        System.out.println("Строка с уникальными именами людей:");
        String uniqueNamesString = uniqueNames.stream().collect(Collectors.joining(", ", "Имена: ", ""));
        System.out.println(uniqueNamesString);
        System.out.println();

        System.out.println("Список несовершеннолетних:");
        List<Person> underageList = personList.stream().filter(person -> person.getAge() < 18).toList();
        underageList.forEach(person -> System.out.println(person.getFirstName()));
        System.out.println();

        OptionalDouble averageAge = underageList.stream().mapToInt(Person::getAge).average();
        averageAge.ifPresent(value -> System.out.printf("Средний возраст несовершеннолетних: %.2f%n", value));
        System.out.println();

        System.out.println("Список уникальных имен и средний возраст:");
        Map<String, Double> namesAgeList = personList.stream().collect(Collectors.groupingBy(Person::getFirstName, Collectors.averagingInt(Person::getAge)));
        namesAgeList.forEach((key, value) -> System.out.println(key + " = " + value));
        System.out.println();

        System.out.println("Молодежь:");
        personList.stream().filter(person -> person.getAge() >= 20 && person.getAge() <= 45).sorted(Comparator.comparingInt(Person::getAge).reversed()).forEach(person -> System.out.println(person.getFirstName()));
        System.out.println();

        // бесконечные потоки
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число элементов для вывода: ");
        int numbersCount = scanner.nextInt();

        System.out.println("Квадратные корни чисел:");
        Stream.iterate(0, x -> x + 1).map(Math::sqrt).limit(numbersCount).forEach(p -> System.out.printf("%.2f%n", p));
        System.out.println();

        System.out.printf("Первые %d чисел Фибоначчи:%n", numbersCount);
        Stream.iterate(new int[]{1, 0, 1}, numbers -> new int[]{numbers[0], numbers[2], numbers[1] + numbers[2]}).limit(numbersCount).forEach(numbers -> System.out.print(numbers[1] + " "));
    }
}