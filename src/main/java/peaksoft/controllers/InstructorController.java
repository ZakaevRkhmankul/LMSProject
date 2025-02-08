package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/instructors")
public class InstructorController{
    private final InstructorService instructorService;

    @GetMapping("/{courseId}")
    public String getAllInstructors(@PathVariable("courseId") Long courseId,Model model){
        model.addAttribute("instructors",instructorService.instructorsByCourseId(courseId));
        return "/instructors";
    }

    @GetMapping("/create/{courseId}")
    public String createInstructor(Model model,@PathVariable("courseId") Long courseId) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", courseId);
        return "/createInstructor";
    }
    @PostMapping("/save/{courseId}")
    public String saveInstructor(@ModelAttribute Instructor instructor, @PathVariable("courseId") Long courseId) {
        instructorService.save(instructor, courseId);
        return "redirect:/instructors/" + courseId;
    }
    @GetMapping("/delete/{courseId}/{instructorId}")
    public String deleteInstructor(@PathVariable("courseId")Long courseId,@PathVariable("instructorId") Long instructorId){
        instructorService.delete(instructorId);
        return "redirect:/instructors/" + courseId;
    }
    @GetMapping("/edit/{courseId}/{id}")
    public String editInstructor(Model model, @PathVariable("id") Long id,@PathVariable("courseId")Long courseId) {
        Instructor instructor = instructorService.getById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", courseId);
        return "/editInstructor";
    }
    @PostMapping("/update/{courseId}/{id}")
    public String updateInstructor(@ModelAttribute Instructor instructor,@PathVariable("id") Long id,@PathVariable("courseId")Long courseId) {
        instructorService.update(id, instructor);
        return "redirect:/instructors/" + courseId;
    }


}
