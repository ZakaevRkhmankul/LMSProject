package peaksoft.controllers;

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

    @GetMapping("/{courseId}")
    public String getAllLessons(@PathVariable("courseId") Long courseId,Model model) {
        model.addAttribute("lessons",lessonService.getAllLessonByCourseId(courseId));
        return "/lesson";
    }
    @GetMapping("/create/{courseId}")
    public String createLesson(Model model, @PathVariable("courseId") Long courseId) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courseId", courseId);
        return "createLesson";
    }
    @PostMapping("/save/{courseId}")
    public String saveLesson(@ModelAttribute("lesson") Lesson lesson, @PathVariable("courseId") Long courseId) {
        lessonService.save(lesson, courseId);
        return "redirect:/lessons/"+courseId;
    }
    @GetMapping("/delete/{courseId}/{lessonId}")
    public String deleteLesson(@PathVariable("courseId") Long courseId ,@PathVariable("lessonId") Long instructorId) {
        lessonService.delete(instructorId);
        return "redirect:/lessons/"+courseId;
    }
    @GetMapping("/edit/{courseId}/{lessonId}")
    public String editLesson(@PathVariable("courseId")Long courseId,@PathVariable("lessonId") Long id,Model model) {
        Lesson lesson = lessonService.getById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", courseId);
        return "editLesson";
    }
    @PostMapping("/update/{courseId}/{lessonId}")
    public String updateLesson(@ModelAttribute Lesson lesson,@PathVariable("courseId")Long courseId ,@PathVariable("lessonId") Long id) {
        lessonService.update(id,lesson);
        return "redirect:/lessons/"+courseId;
    }
}
