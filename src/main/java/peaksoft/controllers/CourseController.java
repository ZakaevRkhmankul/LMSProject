package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.service.CourseService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        return "course";
    }
    @GetMapping("/delete/{courseId}")
    public String delete(@PathVariable("courseId") Long id) {
        courseService.delete(id);
        return "redirect:/courses";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("course", new Course());
        return "createCourse";
    }
    @PostMapping
    public String create(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getById(id);
        model.addAttribute("course", course);
        return "editCourse";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute Course course, @PathVariable("id") Long id) {
        courseService.update(id, course);
        return "redirect:/courses";
    }
}
