package org.example.student;

import org.example.student.entity.Student;
import org.example.student.parser.StudentParser;
import org.example.student.reader.StudentFileReader;
import org.example.student.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private StudentService service;
    private Student[] students;

    @BeforeEach
    void setUp() {
        service = new StudentService();
        students = new Student[]{
                new Student("Виктор", 19, 9.2),
                new Student("Анна", 20, 8.5),
                new Student("Борис", 18, 7.9),
                new Student("Глеб", 21, 8.0),
                new Student("Денис", 22, 9.8)
        };
    }

    @Test
    void testCustomFunctionalInterfaceFilterAndSort() {
        List<Student> result = service.filterAndSortWithCustomInterface(students);
        assertEquals(3, result.size());
        assertEquals("Анна", result.get(0).getName());
        assertEquals("Виктор", result.get(1).getName());
        assertEquals("Денис", result.get(2).getName());
    }

    @Test
    void testStandardFunctionalInterfaceFilterAndSort() {
        List<Student> custom = service.filterAndSortWithCustomInterface(students);
        List<Student> standard = service.filterAndSortWithStandardInterface(students);
        assertEquals(custom, standard);
    }

    @Test
    void testInvalidStudentCreation() {
        assertThrows(IllegalArgumentException.class, () -> new Student("", 19, 9.0));
        assertThrows(IllegalArgumentException.class, () -> new Student("Иван", 12, 9.0));
        assertThrows(IllegalArgumentException.class, () -> new Student("Иван", 19, 11.5));
    }

    @Test
    void testParserAndFileReader() throws IOException {
        StudentFileReader reader = new StudentFileReader();
        StudentParser parser = new StudentParser();
        List<String> lines = reader.readLines("data/students.txt");
        List<Student> parsed = parser.parseStudents(lines);
        assertEquals(5, parsed.size());
    }
}
