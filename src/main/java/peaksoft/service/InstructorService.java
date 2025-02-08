package peaksoft.service;

import peaksoft.entities.Instructor;
import peaksoft.entities.Student;

import java.util.List;

public interface InstructorService {
    Instructor getById(Long id);
    List<Instructor> getAll();
    void save(Instructor instructor,Long courseId);
    void update(Long id, Instructor instructor);
    void delete(Long id);
    void assignInstructorToCourse(Long courseId, Long instructorId);
    List<Instructor> instructorsByCourseId(Long courseId);
}
