package peaksoft.controllers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Lesson;
import peaksoft.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;
    @GetMapping
    public String getAllLessons(Model model) {
        model.addAttribute(lessonService.getdAll());
        return "/lesson/lesson";
    }

//    @GetMapping("/{id}")
//    public String getCoursesLessons(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("lessons",lessonService.getAllLessonByCourseId());
//        return "lesson/lesson";
//    }
    @GetMapping("/create")
    public String createLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lesson/createLesson";
    }
    @PostMapping
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson) {
        lessonService.save(lesson);
        return "redirect:/lessons";
    }
    @GetMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable("lessonId") Long id) {
        lessonService.delete(id);
        return "redirect:/lessons";
    }
    @GetMapping("/edit/{id}")
    public String editLesson(Model model,@PathVariable("id") Long id) {
        Lesson lesson = lessonService.getById(id);
        model.addAttribute("lesson", lesson);
        return "lesson/editLesson";
    }
    @PostMapping("/update/{id}")
    public String updateLesson(@ModelAttribute("lesson") Lesson lesson, @PathVariable("id") Long id) {
        lessonService.update(id,lesson);
        return "redirect:/lessons";
    }
}
