package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.entities.Student;
import peaksoft.repositories.CourseRepo;
import peaksoft.repositories.InstructorRepo;
import peaksoft.service.InstructorService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepo instructorRepo;
    private final CourseRepo courseRepo;
    @Override
    public Instructor getById(Long id) {
        return instructorRepo.getById(id);
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepo.getAll();
    }
    @Override
    public void save(Instructor instructor) {
        instructorRepo.save(instructor);
    }

    @Override
    public void update(Long id, Instructor instructor) {
        instructorRepo.update(id, instructor);
    }

    @Override
    public void delete(Long id) {
        instructorRepo.delete(id);
    }

    @Override
    public List<Instructor> getInstructorsByCourse(Long courseId) {
        return instructorRepo.getInstructorsByCourse(courseId);
    }
}
