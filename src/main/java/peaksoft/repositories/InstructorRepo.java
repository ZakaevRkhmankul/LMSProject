package peaksoft.repositories;

import peaksoft.entities.Instructor;

import java.util.List;

public interface InstructorRepo {
    Instructor getById(Long id);
    List<Instructor> getAll();
    void save(Instructor instructor, Long courseId);
    void update(Long id,Instructor instructor);
    void delete(Long id);
    void assignInstructorToCourse(Long courseId, Long instructorId);

    List<Instructor> getAllInstructorById(Long courseId);
}
