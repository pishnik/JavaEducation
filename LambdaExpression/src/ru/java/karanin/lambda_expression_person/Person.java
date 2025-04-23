package ru.java.karanin.lambda_expression_person;

public class Person {
    // имя
    private final String firstName;
    // возраст
    private final int age;

    public Person(String firstName, int age) {
        this.firstName = firstName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
