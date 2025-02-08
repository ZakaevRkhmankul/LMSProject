package peaksoft.service;

import peaksoft.entities.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> getdAll();
    Lesson getById(Long id);
    Lesson save(Lesson lesson,Long courseId);
    void delete(Long id);
    void update(Long id,Lesson lesson);

    List<Lesson> getAllLessonByCourseId(Long courseId);
}
