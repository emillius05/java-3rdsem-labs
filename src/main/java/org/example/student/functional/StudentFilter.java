package org.example.student.functional;

import org.example.student.entity.Student;

@FunctionalInterface
public interface StudentFilter {
    boolean test(Student student);
}
