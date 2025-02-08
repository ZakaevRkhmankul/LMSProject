package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Lesson;
import peaksoft.repositories.LessonRepo;
import peaksoft.service.LessonService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepo;
    @Override
    public List<Lesson> getdAll() {
        return lessonRepo.getdAll();
    }

    @Override
    public Lesson getById(Long id) {
        return lessonRepo.getById(id);
    }

    @Override
    public Lesson save(Lesson lesson,Long courseId) {
        return lessonRepo.save(lesson,courseId);
    }

    @Override
    public void delete(Long id) {
        lessonRepo.delete(id);
    }

    @Override
    public void update(Long id, Lesson lesson) {
        lessonRepo.update(id, lesson);
    }

    @Override
    public List<Lesson> getAllLessonByCourseId(Long courseId) {
        return lessonRepo.getAllLessonByCourseId(courseId);
    }
}
