package org.example.student.functional;

import org.example.student.entity.Student;

@FunctionalInterface
public interface StudentSorter {
    int compare(Student first, Student second);
}
