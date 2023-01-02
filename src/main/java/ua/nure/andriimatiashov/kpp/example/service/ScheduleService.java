package ua.nure.andriimatiashov.kpp.example.service;

import ua.nure.andriimatiashov.kpp.example.dao.DisciplineIDAO;
import ua.nure.andriimatiashov.kpp.example.dao.ScheduleIDAO;
import ua.nure.andriimatiashov.kpp.example.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleIDAO scheduleIDAO;
    @Autowired
    private DisciplineIDAO disciplineIDAO;

    public void saveSchedule(Schedule schedule) {
        scheduleIDAO.saveSchedule(schedule);
    }

    public Schedule getSchedule(long id) {
        Schedule schedule = scheduleIDAO.getSchedule(id);
        if(schedule!=null){
            schedule.setDisciplineName(disciplineIDAO.getDiscipline(schedule.getDisciplineId()).getName());
        }
        return schedule;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleIDAO.getAllSchedules();
        schedules.forEach(schedule -> schedule.setDisciplineName(
                disciplineIDAO.getDiscipline(schedule.getDisciplineId())
                        .getName()));
        return schedules;
    }

    public List<Schedule> getSchedulesByDisciplineId(long id){
        List<Schedule> schedules = scheduleIDAO.getSchedulesByDisciplineId(id);
        schedules.forEach(schedule -> schedule.setDisciplineName(
                disciplineIDAO.getDiscipline(schedule.getDisciplineId())
                        .getName()));
        return schedules;
    }

    public void deleteSchedule(long id) {
        scheduleIDAO.deleteSchedule(id);
    }
}
