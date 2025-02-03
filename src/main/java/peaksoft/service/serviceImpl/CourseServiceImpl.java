package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.repositories.CourseRepo;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    @Override
    public List<Course> getAll() {
        return courseRepo.getAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepo.getById(id);
    }

    @Override
    public void save(Course course) {
        courseRepo.save(course);
    }

    @Override
    public void update(Long id, Course course) {
        courseRepo.update(id, course);
    }

    @Override
    public void delete(Long id) {
        courseRepo.delete(id);
    }
}
