package peaksoft.repositories.repoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Course;
import peaksoft.entities.Lesson;
import peaksoft.repositories.LessonRepo;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class LessonRepoImpl implements LessonRepo {
    private final EntityManager entityManager;
    @Override
    public List<Lesson> getdAll() {
        try {
            entityManager.getTransaction().begin();
            List<Lesson> lessons = entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();
            entityManager.getTransaction().commit();

            return lessons;
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson getById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            entityManager.getTransaction().commit();

            return lesson;
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Lesson save(Lesson lesson,Long courseId) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            entityManager.persist(lesson);
            course.getLessons().add(lesson);
            lesson.setCourse(course);
            entityManager.getTransaction().commit();

            return lesson;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try {
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            if (lesson != null) {
                Course course = lesson.getCourse();
                if (course != null) {
                    course.getLessons().remove(lesson);
                }lesson.setCourse(null);
                entityManager.remove(lesson);
            }else {
                System.out.println("Lesson not found");
            }
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Lesson neqLesson) {
        try {
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, id);
            lesson.setTitle(neqLesson.getTitle());
            lesson.setDescription(neqLesson.getDescription());
            lesson.setPublisherDate(neqLesson.getPublisherDate());
            entityManager.merge(neqLesson);
            entityManager.getTransaction().commit();

            System.out.println("Lesson " + id + " updated");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Lesson> getAllLessonByCourseId(Long courseId) {
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            List<Lesson> lessons = course.getLessons();
            entityManager.getTransaction().commit();
            return lessons;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
