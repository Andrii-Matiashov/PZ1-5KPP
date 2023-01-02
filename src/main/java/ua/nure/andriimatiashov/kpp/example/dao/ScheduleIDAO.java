package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Schedule;

import java.util.List;

public interface ScheduleIDAO {
    List<Schedule> getAllSchedules();
    void saveSchedule(Schedule schedule);
    void deleteSchedule(long id);
    Schedule getSchedule(long id);
    List<Schedule> getSchedulesByDisciplineId(long id);
}
