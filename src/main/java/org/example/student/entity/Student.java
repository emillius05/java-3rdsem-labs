package org.example.student.entity;

import java.util.Objects;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя студента не может быть пустым");
        }
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Некорректный возраст студента: " + age);
        }
        if (grade < 0.0 || grade > 10.0) {
            throw new IllegalArgumentException("Некорректная оценка студента: " + grade);
        }
        this.name = name.trim();
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя студента не может быть пустым");
        }
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("Некорректный возраст студента: " + age);
        }
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0.0 || grade > 10.0) {
            throw new IllegalArgumentException("Некорректная оценка студента: " + grade);
        }
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return age == student.age
                && Double.compare(student.grade, grade) == 0
                && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, grade);
    }

    @Override
    public String toString() {
        return String.format("Student{name='%s', age=%d, grade=%.2f}", name, age, grade);
    }
}
