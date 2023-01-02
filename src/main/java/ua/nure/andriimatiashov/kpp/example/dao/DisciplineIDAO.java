package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Discipline;

import java.util.List;

public interface DisciplineIDAO {
    List<Discipline> getAllDisciplines();
    void saveDiscipline(Discipline discipline);
    Discipline getDiscipline(long id);
    void deleteDiscipline(long id);
}
