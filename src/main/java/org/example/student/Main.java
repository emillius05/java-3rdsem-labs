package org.example.student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.student.entity.Student;
import org.example.student.parser.StudentParser;
import org.example.student.reader.StudentFileReader;
import org.example.student.service.StudentService;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final String DATA_PATH = "data/students.txt";

    public static void main(String[] args) {
        logger.info("=== Запуск лабораторной работы 7: Студент ===");
        StudentFileReader reader = new StudentFileReader();
        StudentParser parser = new StudentParser();
        StudentService service = new StudentService();

        try {
            List<String> lines = reader.readLines(DATA_PATH);
            List<Student> parsed = parser.parseStudents(lines);
            Student[] array = parsed.toArray(new Student[0]);

            List<Student> customResult = service.filterAndSortWithCustomInterface(array);
            List<Student> standardResult = service.filterAndSortWithStandardInterface(array);

            logger.info("Результат (свой интерфейс): {}", customResult);
            logger.info("Результат (стандартный интерфейс): {}", standardResult);
        } catch (IOException e) {
            logger.error("Ошибка чтения файла: {}", e.getMessage());
        }
        logger.info("=== Завершение работы ===");
    }
}
