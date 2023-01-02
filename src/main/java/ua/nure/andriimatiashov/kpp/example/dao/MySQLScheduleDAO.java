package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLScheduleDAO implements ScheduleIDAO{

    private String url;
    private String user;
    private String password;


    private final String SQL_GET_ALL_SCHEDULES = "SELECT * FROM schedule";
    private final String SQL_GET_SCHEDULE_BY_ID= "SELECT * FROM schedule WHERE id = ?";
    private final String SQL_GET_SCHEDULES_BY_DISCIPLINE_ID= "SELECT * FROM schedule WHERE discipline_id = ?";
    private final String SQL_ADD_SCHEDULE= "INSERT INTO schedule (start_time,end_time,cabinet_number,discipline_id) VALUES(?,?,?,?)";
    private final String SQL_UPDATE_SCHEDULE= "UPDATE schedule SET start_time = ?, end_time = ?, cabinet_number = ?, discipline_id = ? WHERE id = ?";
    private final String SQL_DELETE_SCHEDULE_BY_ID= "DELETE FROM schedule WHERE id = ?";

    public MySQLScheduleDAO(DAOConfig config){
        url = config.getUrl();
        user = config.getUser();
        password = config.getPassword();
    }

    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_SCHEDULES);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(set.getLong("id"));
                schedule.setDisciplineId(set.getLong("discipline_id"));
                schedule.setEndTime(set.getString("end_time"));
                schedule.setStartTime(set.getString("start_time"));
                schedule.setCabinetNumber(set.getString("cabinet_number"));
                schedules.add(schedule);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        PreparedStatement statement = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (schedule == null) {
                throw new IllegalArgumentException("Object to save is null.");
            }

            if (schedule.getId() == 0) {
                statement = connection.prepareStatement(SQL_ADD_SCHEDULE);
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_SCHEDULE);
                statement.setLong(5, schedule.getId());
            }

            statement.setString(1, schedule.getStartTime());
            statement.setString(2, schedule.getEndTime());
            statement.setString(3, schedule.getCabinetNumber());
            statement.setLong(4, schedule.getDisciplineId());
            statement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSchedule(long id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SCHEDULE_BY_ID);
            statement.setLong(1,id);
            statement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Schedule getSchedule(long id) {
        Schedule schedule = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_SCHEDULE_BY_ID);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                schedule = new Schedule();
                schedule.setId(set.getLong("id"));
                schedule.setDisciplineId(set.getLong("discipline_id"));
                schedule.setEndTime(set.getString("end_time"));
                schedule.setStartTime(set.getString("start_time"));
                schedule.setCabinetNumber(set.getString("cabinet_number"));
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    @Override
    public List<Schedule> getSchedulesByDisciplineId(long id) {
        List<Schedule> schedules = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_SCHEDULES_BY_DISCIPLINE_ID);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(set.getLong("id"));
                schedule.setDisciplineId(set.getLong("discipline_id"));
                schedule.setEndTime(set.getString("end_time"));
                schedule.setStartTime(set.getString("start_time"));
                schedule.setCabinetNumber(set.getString("cabinet_number"));
                schedules.add(schedule);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}
