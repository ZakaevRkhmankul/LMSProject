package peaksoft.repositories;

import org.springframework.stereotype.Repository;
import peaksoft.entities.Lesson;

import java.util.List;

public interface LessonRepo {
    List<Lesson> getdAll();
    Lesson getById(Long id);
    Lesson save(Lesson lesson);
    void delete(Long id);
    void update(Long id,Lesson lesson);
    List<Lesson> getAllLessonByCourseId();
}
