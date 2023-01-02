package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Teacher;

import java.util.List;

public interface TeacherIDAO {
    List<Teacher> getAllTeachers();
    List<Teacher> getTeachersBySurname(String surname);
    Teacher getTeacher(long id);
    void saveTeacher(Teacher teacher);
    void deleteTeacher(long id);
    List<Teacher> getTeachersByDisciplineId(long id);
}
