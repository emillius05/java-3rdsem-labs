package org.example.student.functional;

import java.util.List;
import org.example.student.entity.Student;

@FunctionalInterface
public interface StudentListProcessor {
    List<Student> process(List<Student> students, StudentFilter filter, StudentSorter sorter);
}
