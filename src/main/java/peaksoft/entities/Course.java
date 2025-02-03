package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Course {
    @Id
    @GeneratedValue(generator = "course_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    private String title;
    private LocalDate dateOfStart;
    private String description;
    private String imageUrl;
    @ManyToMany
    private List<Instructor>instructors;
    @ManyToMany
    private List<Student>students;
    @OneToMany(mappedBy = "course",cascade = CascadeType.PERSIST)
    private List<Lesson>lessons;
}