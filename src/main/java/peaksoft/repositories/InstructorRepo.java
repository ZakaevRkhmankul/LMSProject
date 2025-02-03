package peaksoft.repositories;

import peaksoft.entities.Instructor;

import java.util.List;

public interface InstructorRepo {
    Instructor getById(Long id);
    List<Instructor> getAll();
    void save(Instructor instructor);
    void update(Long id,Instructor instructor);
    void delete(Long id);
    List<Instructor> getInstructorsByCourse(Long courseId);
}
