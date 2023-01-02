package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTeacherDAO implements TeacherIDAO{
    private String url;
    private String user;
    private String password;

    private final String SQL_GET_ALL_TEACHERS = "SELECT * FROM teacher";
    private final String SQL_GET_TEACHER_BY_ID = "SELECT * FROM teacher WHERE id = ?";
    private final String SQL_GET_TEACHER_BY_DISCIPLINE_ID = "SELECT * FROM teacher WHERE discipline_id = ?";
    private final String SQL_GET_TEACHER_BY_SURNAME = "SELECT * FROM teacher WHERE surname = ?";
    private final String SQL_ADD_TEACHER = "INSERT INTO teacher (surname, name, father_name, phone_number, email, discipline_id) VALUES (?,?,?,?,?,?)";
    private final String SQL_UPDATE_TEACHER = "UPDATE teacher SET surname = ?, name = ?, father_name = ?, phone_number = ?, email = ?, discipline_id = ? WHERE id = ?";
    private final String SQL_DELETE_TEACHER_BY_ID = "DELETE FROM teacher WHERE id = ?";

    public MySQLTeacherDAO(DAOConfig config) {
        url = config.getUrl();
        user = config.getUser();
        password = config.getPassword();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_TEACHERS);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(set.getLong("id"));
                teacher.setSurname(set.getString("surname"));
                teacher.setName(set.getString("name"));
                teacher.setFatherName(set.getString("father_name"));
                teacher.setEmail(set.getString("email"));
                teacher.setPhone(set.getString("phone_number"));
                teacher.setDisciplineId(set.getLong("discipline_id"));
                teachers.add(teacher);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public List<Teacher> getTeachersBySurname(String surname) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_TEACHER_BY_SURNAME);

            statement.setString(1, surname);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(set.getLong("id"));
                teacher.setSurname(set.getString("surname"));
                teacher.setName(set.getString("name"));
                teacher.setFatherName(set.getString("father_name"));
                teacher.setPhone(set.getString("phone_number"));
                teacher.setEmail(set.getString("email"));
                teacher.setDisciplineId(set.getLong("discipline_id"));
                teachers.add(teacher);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Teacher getTeacher(long id) {
        Teacher teacher = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_TEACHER_BY_ID);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                teacher = new Teacher();
                teacher.setId(set.getLong("id"));
                teacher.setSurname(set.getString("surname"));
                teacher.setName(set.getString("name"));
                teacher.setFatherName(set.getString("father_name"));
                teacher.setPhone(set.getString("phone_number"));
                teacher.setEmail(set.getString("email"));
                teacher.setDisciplineId(set.getLong("discipline_id"));
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        PreparedStatement statement = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            if (teacher == null) {
                throw new IllegalArgumentException("Object to save is null.");
            }

            if (teacher.getId() == 0) {
                statement = connection.prepareStatement(SQL_ADD_TEACHER);
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_TEACHER);
                statement.setLong(7, teacher.getId());
            }

            statement.setString(1, teacher.getSurname());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getFatherName());
            statement.setString(4, teacher.getPhone());
            statement.setString(5, teacher.getEmail());
            statement.setLong(6, teacher.getDisciplineId());
            statement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(long id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_TEACHER_BY_ID);
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
    public List<Teacher> getTeachersByDisciplineId(long id) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            PreparedStatement statement = connection.prepareStatement(SQL_GET_TEACHER_BY_DISCIPLINE_ID);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(set.getLong("id"));
                teacher.setSurname(set.getString("surname"));
                teacher.setName(set.getString("name"));
                teacher.setFatherName(set.getString("father_name"));
                teacher.setPhone(set.getString("phone_number"));
                teacher.setEmail(set.getString("email"));
                teacher.setDisciplineId(set.getLong("discipline_id"));
                teachers.add(teacher);
            }

            connection.commit();
            connection.setAutoCommit(true);

            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }
}
