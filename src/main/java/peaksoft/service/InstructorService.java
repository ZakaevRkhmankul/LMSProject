package peaksoft.service;

import peaksoft.entities.Instructor;
import peaksoft.entities.Student;

import java.util.List;

public interface InstructorService {
    Instructor getById(Long id);
    List<Instructor> getAll();
    void save(Instructor instructor);
    void update(Long id, Instructor instructor);
    void delete(Long id);
    List<Instructor> getInstructorsByCourse(Long courseId);
}
