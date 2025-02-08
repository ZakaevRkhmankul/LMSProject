package peaksoft.repositories;

import peaksoft.entities.Course;
import peaksoft.entities.Instructor;

import java.util.List;
import java.util.Map;

public interface CourseRepo {
    List<Course> getAll();
    Course getById(Long id);
    void save(Course course);
    void update(Long id,Course course);
    void delete(Long id);
}
