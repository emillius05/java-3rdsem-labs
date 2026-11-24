package org.example.student.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentParser {
    private static final Logger logger = LogManager.getLogger(StudentParser.class);
    private static final String DELIMITER = ";";

    public List<Student> parseStudents(List<String> lines) {
        logger.info("Начало парсинга студентов");
        List<Student> students = new ArrayList<>();
        if (lines == null) {
            logger.warn("Передан null вместо списка строк");
            return students;
        }
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            if (parts.length < 3) {
                logger.warn("Пропуск некорректной строки: {}", line);
                continue;
            }
            try {
                String name = parts[0].trim();
                int age = Integer.parseInt(parts[1].trim());
                double grade = Double.parseDouble(parts[2].trim().replace(',', '.'));
                Student student = new Student(name, age, grade);
                students.add(student);
                logger.debug("Добавлен студент: {}", student);
            } catch (Exception e) {
                logger.warn("Ошибка парсинга строки [{}]: {}", line, e.getMessage());
            }
        }
        logger.info("Парсинг завершен. Корректных записей: {}", students.size());
        return students;
    }
}
