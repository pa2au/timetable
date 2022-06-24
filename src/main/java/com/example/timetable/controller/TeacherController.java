package com.example.timetable.controller;

import com.example.timetable.service.ExerciseTimeService;
import com.example.timetable.service.GroupService;
import com.example.timetable.service.WeekDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final WeekDayService weekDayService;
    private final ExerciseTimeService exerciseTimeService;
    private final GroupService groupService;

    @GetMapping("/lessons")
    public String getLessons() {
        return "lessons";
    }

    @GetMapping("/lesson/create")
    public String createLesson(Model model) {
        model.addAttribute("weekDays", weekDayService.findWeekDayAll());
        model.addAttribute("exercisesTime", exerciseTimeService.findExerciseTimeAll());
        model.addAttribute("groups", groupService.findAllGroups());
        return "createLesson";
    }
}
