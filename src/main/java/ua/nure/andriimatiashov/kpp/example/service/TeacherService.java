package ua.nure.andriimatiashov.kpp.example.service;

import ua.nure.andriimatiashov.kpp.example.dao.DisciplineIDAO;
import ua.nure.andriimatiashov.kpp.example.dao.TeacherIDAO;
import ua.nure.andriimatiashov.kpp.example.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherIDAO teacherIDAO;
    @Autowired
    private DisciplineIDAO disciplineIDAO;

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = teacherIDAO.getAllTeachers();
        teachers.forEach(teacher ->
                teacher.setDisciplineName(disciplineIDAO.getDiscipline(
                                teacher.getDisciplineId())
                        .getName())
        );
        return teachers;
    }

    public List<Teacher> getTeachersBySurname(String surname){
        List<Teacher> teachers = teacherIDAO.getTeachersBySurname(surname);
        teachers.forEach(teacher -> teacher.setDisciplineName(disciplineIDAO.getDiscipline(teacher.getDisciplineId()).getName()));
        return teachers;
    }

    public void saveTeacher(Teacher teacher) {
        teacherIDAO.saveTeacher(teacher);
    }

    public Teacher getTeacher(long id) {
        Teacher teacher = teacherIDAO.getTeacher(id);
        if (teacher != null) {
            teacher.setDisciplineName(disciplineIDAO.getDiscipline(teacher.getDisciplineId()).getName());
        }
        return teacher;
    }

    public void deleteTeacher(long id) {
        teacherIDAO.deleteTeacher(id);
    }

    public List<Teacher> getTeachersByDisciplineId(long id) {
        List<Teacher> teachers = teacherIDAO.getTeachersByDisciplineId(id);
        teachers.forEach(teacher ->
                teacher.setDisciplineName(disciplineIDAO.getDiscipline(
                                teacher.getDisciplineId())
                        .getName())
        );
        return teachers;
    }
}
