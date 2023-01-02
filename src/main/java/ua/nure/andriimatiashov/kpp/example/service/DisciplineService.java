package ua.nure.andriimatiashov.kpp.example.service;

import ua.nure.andriimatiashov.kpp.example.dao.DisciplineIDAO;
import ua.nure.andriimatiashov.kpp.example.dao.ScheduleIDAO;
import ua.nure.andriimatiashov.kpp.example.dao.TeacherIDAO;
import ua.nure.andriimatiashov.kpp.example.entity.Discipline;
import ua.nure.andriimatiashov.kpp.example.entity.Schedule;
import ua.nure.andriimatiashov.kpp.example.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplineService {
    @Autowired
    private DisciplineIDAO disciplineIDAO;
    @Autowired
    private TeacherIDAO teacherIDAO;
    @Autowired
    private ScheduleIDAO scheduleIDAO;

    public List<Discipline> getAllDisciplines() {
        return disciplineIDAO.getAllDisciplines();
    }

    public Discipline getDiscipline(long id) {
        return disciplineIDAO.getDiscipline(id);
    }

    public void saveDiscipline(Discipline discipline) {
        disciplineIDAO.saveDiscipline(discipline);
    }

    public void deleteDiscipline(long id) {
        List<Teacher> teachers = teacherIDAO.getTeachersByDisciplineId(id);
        List<Schedule> schedules = scheduleIDAO.getSchedulesByDisciplineId(id);
        List<Long> ids = new ArrayList<>();
        teachers.forEach(teacher -> ids.add(teacher.getId()));
        ids.forEach(i -> teacherIDAO.deleteTeacher(i));
        ids.clear();
        schedules.forEach(schedule -> ids.add(schedule.getId()));
        ids.forEach(i -> scheduleIDAO.deleteSchedule(i));
        disciplineIDAO.deleteDiscipline(id);
    }
}
