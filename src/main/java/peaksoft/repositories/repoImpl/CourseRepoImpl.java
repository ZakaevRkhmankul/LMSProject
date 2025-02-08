package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.repositories.CourseRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {
    private final EntityManager entityManager;

    @Override
    public List<Course> getAll() {
        try {
            entityManager.getTransaction().begin();
            List<Course> courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
            entityManager.getTransaction().commit();
            return courses;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Course getById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            entityManager.getTransaction().commit();
            return course;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Course course) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
            System.out.println("Course with id " + course.getId() + "saved successfully");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void update(Long id, Course newCourse) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            course.setTitle(newCourse.getTitle());
            course.setDateOfStart(newCourse.getDateOfStart());
            course.setDescription(newCourse.getDescription());
            course.setImageUrl(newCourse.getImageUrl());
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            System.out.println("Course with id " + id + " updated successfully");
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, id);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

}
