package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionScheduleDAO implements ScheduleIDAO{


    private static int scheduleIncrement = 1;
    private static List<Schedule> schedules = new ArrayList<>();
    static {

        schedules.add(new Schedule(scheduleIncrement++,"2022-12-23 17:00:00","2022-12-23 18:30:00","113-A",1));
        schedules.add(new Schedule(scheduleIncrement++,"2022-12-22 14:00:00","2022-12-22 18:30:00","112-I",1));
        schedules.add(new Schedule(scheduleIncrement++,"2022-12-24 18:00:00","2022-12-24 21:00:00","121-B",2));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return schedules;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        if(schedule.getId()!=0){
            Schedule update = schedules.stream().filter(s -> s.getId()==schedule.getId()).findAny().orElse(null);
            if(update==null){
                return;
            }
            update.setStartTime(schedule.getStartTime());
            update.setEndTime(schedule.getEndTime());
            update.setCabinetNumber(schedule.getCabinetNumber());
            update.setDisciplineId(schedule.getDisciplineId());
        }
        else{
            schedule.setId(scheduleIncrement++);
            schedules.add(schedule);
        }
    }

    @Override
    public void deleteSchedule(long id) {
        schedules.removeIf(schedule -> schedule.getId()==id);
    }

    @Override
    public Schedule getSchedule(long id) {
        return schedules.stream().filter(schedule -> schedule.getId()==id).findAny().orElse(null);
    }

    @Override
    public List<Schedule> getSchedulesByDisciplineId(long id) {
        return schedules.stream().filter(schedule -> schedule.getDisciplineId()==id).collect(Collectors.toList());
    }
}
