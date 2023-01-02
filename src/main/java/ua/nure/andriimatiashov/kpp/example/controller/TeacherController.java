package ua.nure.andriimatiashov.kpp.example.controller;

import jakarta.validation.Valid;
import ua.nure.andriimatiashov.kpp.example.entity.Teacher;
import ua.nure.andriimatiashov.kpp.example.form.AddTeacherForm;
import ua.nure.andriimatiashov.kpp.example.form.SearchTeacherForm;
import ua.nure.andriimatiashov.kpp.example.service.DisciplineService;
import ua.nure.andriimatiashov.kpp.example.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DisciplineService disciplineService;

    @GetMapping()
    public String showAllTeachers(Model model){

        model.addAttribute("teachers",teacherService.getAllTeachers());
        return "teachers";
    }

    @GetMapping("/new")
    public String newTeacher(Model model){
        model.addAttribute("form",new AddTeacherForm());
        model.addAttribute("listOfDisciplines",disciplineService.getAllDisciplines());
        return "addTeacher";
    }

    @PostMapping()
    public String createTeacher(@Valid @ModelAttribute("form") AddTeacherForm form, BindingResult result){
        if(result.hasErrors()){
            return "addTeacher";
        }
        Teacher teacher = new Teacher();
        teacher.setId(form.getId());
        teacher.setName(form.getName());
        teacher.setSurname(form.getSurname());
        teacher.setFatherName(form.getFatherName());
        teacher.setEmail(form.getEmail());
        teacher.setPhone(form.getPhone());
        teacher.setDisciplineId(form.getDisciplineId());
        teacherService.saveTeacher(teacher);
        return "redirect:/teacher";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(@PathVariable("id")long id,Model model){
        AddTeacherForm form = new AddTeacherForm();
        Teacher teacher = teacherService.getTeacher(id);
        if(teacher==null){
            return "redirect:/teacher";
        }
        form.setId(teacher.getId());
        form.setName(teacher.getName());
        form.setSurname(teacher.getSurname());
        form.setEmail(teacher.getEmail());
        form.setPhone(teacher.getPhone());
        form.setFatherName(teacher.getFatherName());
        form.setDisciplineId(teacher.getDisciplineId());
        model.addAttribute("form",form);
        model.addAttribute("listOfDisciplines",disciplineService.getAllDisciplines());
        return "addTeacher";
    }

    @GetMapping("/{id}")
    public String showTeacher(@PathVariable("id") long id, Model model){
        Teacher teacher = teacherService.getTeacher(id);
        if(teacher==null){
            return "redirect:/teacher";
        }
        model.addAttribute("teacher",teacher);

        return "showTeacher";
    }

    @PostMapping("/{id}")
    public String deleteTeacher(@PathVariable("id") long id){
        teacherService.deleteTeacher(id);
        return "redirect:/teacher";
    }

    @GetMapping("/search")
    public String findTeachersByName(Model model){
        model.addAttribute("form",new SearchTeacherForm());
        return "searchTeacherPage";
    }

    @GetMapping("/result")
    public String showFoundedTeachers(@Valid @ModelAttribute("form") SearchTeacherForm form,BindingResult result,Model model){
        if(result.hasErrors()){
            return "searchTeacherPage";
        }
        model.addAttribute("teachers",teacherService.getTeachersBySurname(form.getName()));
        return "teachers";
    }

}
