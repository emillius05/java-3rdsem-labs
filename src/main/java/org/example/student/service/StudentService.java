package org.example.student.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.student.entity.Student;
import org.example.student.functional.StudentFilter;
import org.example.student.functional.StudentListProcessor;
import org.example.student.functional.StudentSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class StudentService {
    private static final Logger logger = LogManager.getLogger(StudentService.class);
    public static final double MIN_GRADE_THRESHOLD = 8.0;

    public List<Student> filterAndSortWithCustomInterface(Student[] students) {
        logger.info("Обработка через собственный функциональный интерфейс");
        StudentFilter aboveEightFilter = s -> s != null && s.getGrade() > MIN_GRADE_THRESHOLD;
        StudentSorter byNameSorter = (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName());

        StudentListProcessor processor = (list, filter, sorter) -> {
            List<Student> result = new ArrayList<>();
            for (Student s : list) {
                if (filter.test(s)) {
                    result.add(s);
                }
            }
            result.sort(sorter::compare);
            return result;
        };

        List<Student> input = students == null ? List.of() : List.of(students);
        List<Student> output = processor.process(input, aboveEightFilter, byNameSorter);
        logger.debug("Результат (свой интерфейс): {}", output);
        return List.copyOf(output);
    }

    public List<Student> filterAndSortWithStandardInterface(Student[] students) {
        logger.info("Обработка через стандартные функциональные интерфейсы Java");
        Predicate<Student> gradeAboveEight = s -> s != null && s.getGrade() > MIN_GRADE_THRESHOLD;
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER);

        List<Student> input = students == null ? List.of() : List.of(students);
        List<Student> output = input.stream()
                .filter(gradeAboveEight)
                .sorted(nameComparator)
                .toList();
        logger.debug("Результат (стандартный интерфейс): {}", output);
        return output;
    }
}
