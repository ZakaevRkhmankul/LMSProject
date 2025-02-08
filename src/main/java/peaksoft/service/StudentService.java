package peaksoft.service;

import peaksoft.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void saveStudent(Student student);
    void updateStudent(Long id,Student student);
    void deleteStudent(Long id);
    void assignStudentToCourse(Long studentId, Long courseId);
}
