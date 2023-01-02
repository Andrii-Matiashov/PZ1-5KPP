package ua.nure.andriimatiashov.kpp.example.controller;

import jakarta.validation.Valid;
import ua.nure.andriimatiashov.kpp.example.entity.Discipline;
import ua.nure.andriimatiashov.kpp.example.form.AddDisciplineForm;
import ua.nure.andriimatiashov.kpp.example.service.DisciplineService;
import ua.nure.andriimatiashov.kpp.example.service.ScheduleService;
import ua.nure.andriimatiashov.kpp.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/discipline")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping()
    public String showAllDisciplines(Model model){
        model.addAttribute("allDisciplines",disciplineService.getAllDisciplines());
        return "disciplines";
    }

    @GetMapping("/{id}")
    public String showDiscipline(@PathVariable("id") long id, Model model){
        Discipline discipline = disciplineService.getDiscipline(id);
        if(discipline==null){
            return "redirect:/discipline";
        }
        model.addAttribute("teachers", teacherService.getTeachersByDisciplineId(id));
        model.addAttribute("schedule",scheduleService.getSchedulesByDisciplineId(id));
        model.addAttribute("discipline",discipline);
        return "showDiscipline";
    }

    @GetMapping("/new")
    public String newDiscipline(Model model){
        model.addAttribute("form",new AddDisciplineForm());
        return "addDiscipline";
    }
    @PostMapping()
    public String createDiscipline(@Valid @ModelAttribute("form") AddDisciplineForm form, BindingResult result){
        if(result.hasErrors()){
            return "addDiscipline";
        }
        Discipline discipline = new Discipline();
        discipline.setId(form.getId());
        discipline.setName(form.getName());
        discipline.setHourPerSemester(form.getHourPerSemester());
        disciplineService.saveDiscipline(discipline);
        return "redirect:/discipline";
    }

    @GetMapping("/{id}/edit")
    public String editDiscipline(@PathVariable("id")long id,Model model){
        AddDisciplineForm form = new AddDisciplineForm();
        Discipline discipline = disciplineService.getDiscipline(id);
        if(discipline==null){
            return "redirect:/discipline";
        }
        form.setId(discipline.getId());
        form.setName(discipline.getName());
        form.setHourPerSemester(discipline.getHourPerSemester());
        model.addAttribute("form",form);
        return "addDiscipline";
    }
    @PostMapping("/{id}")
    public String deleteDiscipline(@PathVariable("id")long id){
        disciplineService.deleteDiscipline(id);
        return "redirect:/discipline";
    }
}
