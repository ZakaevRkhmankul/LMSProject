package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Instructor {
    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @ManyToMany(mappedBy = "instructors")
    private List<Course> courses=new ArrayList<>();



}
