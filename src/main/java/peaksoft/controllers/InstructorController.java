package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;



@Controller
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController{
    private final InstructorService instructorService;
    private final CourseService courseService;
    @GetMapping("/instructorToCourse/{courseId}")
    public String getInstructorByCourse(@PathVariable("courseId") Long courseId, Model model){
       model.addAttribute(instructorService.getInstructorsByCourse(courseId));
        return "/course/course";
    }
    @GetMapping("/create")
    public String createInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "/instructor/createInstructor";
    }
    @PostMapping("/save")
    public String saveInstructor( @ModelAttribute Instructor instructor) {
        instructorService.save(instructor);
        return "redirect:/instructors";
    }
    @GetMapping("/delete/{instructorId}")
    public String deleteInstructor(@PathVariable("instructorId") Long id) {
        instructorService.delete(id);
        return "redirect:/instructors";
    }
    @GetMapping("/edit/{id}")
    public String editInstructor(Model model, @PathVariable("id") Long id) {
        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor", instructor);
        return "instructor/editInstructor";
    }
    @PostMapping("/update/{id}")
    public String updateInstructor(@ModelAttribute Instructor instructor,@PathVariable("id") Long id) {
        instructorService.update(id, instructor);
        return "redirect:/instructors";
    }


}
