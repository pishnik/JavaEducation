package ru.java.karanin.person;

public class Person {
    // имя
    private final String firstName;
    // возраст
    private final int age;

    public Person(String fistName, int age) {
        this.firstName = fistName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
