package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Student;
import peaksoft.service.StudentService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{courseId}")
    public String getAlStudents(@PathVariable("courseId")Long courseId, Model model) {
        model.addAttribute("students",studentService.)
        return "student";
    }
    @GetMapping("/create")
    public String createStudent(Model model) {
        model.addAttribute("student", new Student());
        return "createStudent";
    }
    @PostMapping()
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
    @GetMapping("/edit/{id}")
    public String editStudent(Model model,@PathVariable("id") Long id) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }
    @PostMapping("/update/{id}")
    public String updateStudent(@ModelAttribute("student") Student student,@PathVariable("id") Long id) {
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }
}
