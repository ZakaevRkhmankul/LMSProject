package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Student;
import peaksoft.repositories.StudentRepo;
import peaksoft.service.StudentService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    @Override
    public List<Student> getAllStudents() {
        return studentRepo.getAllStudents();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.getStudentById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentRepo.saveStudent(student);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        studentRepo.updateStudent(id, student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepo.deleteStudent(id);
    }
}
