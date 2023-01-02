package ua.nure.andriimatiashov.kpp.example.controller;

import jakarta.validation.Valid;
import ua.nure.andriimatiashov.kpp.example.entity.Schedule;
import ua.nure.andriimatiashov.kpp.example.form.AddScheduleForm;
import ua.nure.andriimatiashov.kpp.example.service.DisciplineService;
import ua.nure.andriimatiashov.kpp.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping()
    public String showSchedules(Model model){
        model.addAttribute("allSchedules",scheduleService.getAllSchedules());
        return "schedules";
        // FIXME: 23.12.2022
    }

    @GetMapping("/new")
    public String newSchedule(Model model){
        model.addAttribute("form", new AddScheduleForm());
        model.addAttribute("listOfDisciplines", disciplineService.getAllDisciplines());
        return "addSchedule";
    }
    @PostMapping()
    public String createSchedule(@Valid @ModelAttribute("form") AddScheduleForm form, BindingResult result){
        if(result.hasErrors()){
            return "addSchedule";
        }
        System.out.println(form);
        Schedule schedule = new Schedule();
        schedule.setId(form.getId());
        schedule.setCabinetNumber(form.getCabinetNumber());
        schedule.setEndTime(form.getEndTime());
        schedule.setStartTime(form.getStartTime());
        schedule.setDisciplineId(form.getDisciplineId());
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedule";
    }
    @GetMapping("/{id}")
    public String showSchedule(@PathVariable("id")long id, Model model){
        Schedule schedule = scheduleService.getSchedule(id);
        if(schedule==null){
            return "redirect:/schedule";
        }
        model.addAttribute("schedule",schedule);
        return "showSchedule";
    }

    @GetMapping("/{id}/edit")
    public String editSchedule(@PathVariable("id")long id,Model model){
        AddScheduleForm form = new AddScheduleForm();
        Schedule schedule = scheduleService.getSchedule(id);
        if(schedule==null){
            return "redirect:/schedule";
        }
        form.setId(schedule.getId());
        form.setCabinetNumber(schedule.getCabinetNumber());
        form.setDisciplineId(schedule.getDisciplineId());
        form.setStartTime(schedule.getStartTime());
        form.setEndTime(schedule.getEndTime());
        model.addAttribute("form",form);
        model.addAttribute("listOfDisciplines",disciplineService.getAllDisciplines());
        return "addSchedule";
    }

    @PostMapping("/{id}")
    public String deleteSchedule(@PathVariable("id") long id){
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule";
    }
}
