package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.repositories.InstructorRepo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstructorRepoImpl implements InstructorRepo {
    private final EntityManager entityManager;

    @Override
    public Instructor getById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, id);
            entityManager.getTransaction().commit();
            return instructor;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Instructor> getAll() {
        try {
            entityManager.getTransaction().begin();
            List<Instructor> instructors = entityManager.createQuery("select i from Instructor i").getResultList();
            entityManager.getTransaction().commit();
            return instructors;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Instructor instructor) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Instructor newInstructor) {
        try {
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, id);
            instructor.setFirstName(newInstructor.getFirstName());
            instructor.setLastName(newInstructor.getLastName());
            instructor.setEmail(newInstructor.getEmail());
            instructor.setPhoneNumber(newInstructor.getPhoneNumber());
            entityManager.merge(newInstructor);
            entityManager.getTransaction().commit();

            System.out.println("Instructor with id " + id + " updated successfully");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, id);
            entityManager.remove(instructor);
            entityManager.getTransaction().commit();
            System.out.println("Instructor with id " + id + " deleted successfully");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Instructor>getInstructorsByCourse(Long courseId) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            course.getInstructors().add(new Instructor());
            entityManager.getTransaction().commit();
            return course.getInstructors();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
