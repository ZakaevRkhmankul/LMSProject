package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.repositories.StudentRepo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepoImpl implements StudentRepo {
    private final EntityManager entityManager;
    @Override
    public List<Student> getAllStudents() {
        try {
            entityManager.getTransaction().begin();
            List<Student> students = entityManager.createQuery(" select s from Student s", Student.class).getResultList();
            entityManager.getTransaction().commit();

            return students;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }return null;
    }

    @Override
    public Student getStudentById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.getTransaction().commit();

            return student;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void saveStudent(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            student.setFirstName(newStudent.getFirstName());
            student.setLastName(newStudent.getLastName());
            student.setEmail(newStudent.getEmail());
            student.setPhoneNumber(newStudent.getPhoneNumber());
            entityManager.merge(newStudent);
            entityManager.getTransaction().commit();

            System.out.println("Student with id " + id + " has been updated");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteStudent(Long id) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            entityManager.remove(student);
            entityManager.getTransaction().commit();

            System.out.println("Student with id " + id + " has been deleted");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, studentId);
            Course course = entityManager.find(Course.class, courseId);
            student.getCourses().add(course);
            course.getStudents().add(student);
            entityManager.merge(student);
            entityManager.merge(course);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
