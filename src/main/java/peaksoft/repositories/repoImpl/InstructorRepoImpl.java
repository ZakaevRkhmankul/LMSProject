package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.repositories.InstructorRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstructorRepoImpl implements InstructorRepo {
    private final EntityManager entityManager;
@Transactional
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
            entityManager.getTransaction().begin();
            List<Instructor> instructors = entityManager.createQuery("select i from Instructor i",Instructor.class).getResultList();
            entityManager.getTransaction().commit();
            return instructors;
        }


    @Override
    public void save(Instructor instructor, Long courseId) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.persist(instructor);
            course.getInstructors().add(instructor);
            instructor.getCourses().add(course);
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
    entityManager.getTransaction().begin();

            Instructor instructor = entityManager.find(Instructor.class, id);

            if (instructor != null) {
                for (Course course : instructor.getCourses()) {
                    if (course != null) {
                        course.getInstructors().remove(instructor);
                    }
                }
                entityManager.remove(instructor);
            }
            entityManager.getTransaction().commit();
            System.out.println("Instructor with id " + id + " deleted successfully");

        }


    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            instructor.setCourses(List.of(course));
            course.setInstructors(List.of(instructor));
            entityManager.merge(course);
            entityManager.merge(instructor);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Instructor> getAllInstructorById(Long courseId) {
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        List<Instructor> instructors = course.getInstructors();
        entityManager.getTransaction().commit();
        return instructors;
    }

//    @Override
//    public List<Instructor> getAllInstructorById(Long courseId) {
//        entityManager.getTransaction().begin();
//        List<Instructor> instructors = new ArrayList<>();
//        Course course = entityManager.find(Course.class, courseId);
//        if (course.getInstructors() != null) {
//            instructors.addAll(course.getInstructors());
//        }
//        entityManager.getTransaction().commit();
//        return instructors;
//    }

}
