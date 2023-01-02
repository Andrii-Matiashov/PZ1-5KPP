package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionTeacherDAO implements TeacherIDAO{

    private static int teacherIncrement = 1;
    private static List<Teacher> teachers = new ArrayList<>();
    static {

        teachers.add(new Teacher(teacherIncrement++,"Matiashov","Andrii","Vyacheslavovich","+3530872030505","example@demo.ua",1));
        teachers.add(new Teacher(teacherIncrement++,"Shevchenko","Alexandr","Aleksandrovich","+3530853539433","sanya@example.com",2));
        teachers.add(new Teacher(teacherIncrement++,"Kryglov","Oleg","Sergeevich","+380506774536","Oleg@example.ua",1));
    }
    @Override
    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    @Override
    public List<Teacher> getTeachersBySurname(String surname) {
        return teachers.stream().filter(teacher -> teacher.getSurname().equals(surname)).collect(Collectors.toList());
    }

    @Override
    public Teacher getTeacher(long id) {
        return teachers.stream().filter(teacher -> teacher.getId()==id).findAny().orElse(null);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        if(teacher.getId()!=0){
            Teacher update = teachers.stream().filter(t -> t.getId()==teacher.getId()).findAny().orElse(null);
            if(update==null){
                return;
            }
            update.setSurname(teacher.getSurname());
            update.setName(teacher.getName());
            update.setFatherName(teacher.getFatherName());
            update.setPhone(teacher.getPhone());
            update.setEmail(teacher.getEmail());
            update.setDisciplineId(teacher.getDisciplineId());
        }
        else{
            teacher.setId(teacherIncrement++);
            teachers.add(teacher);
        }
    }

    @Override
    public void deleteTeacher(long id) {
        teachers.removeIf(teacher -> teacher.getId()==id);
    }

    @Override
    public List<Teacher> getTeachersByDisciplineId(long id) {
        return teachers.stream().filter(teacher -> teacher.getDisciplineId()==id).collect(Collectors.toList());
    }
}
