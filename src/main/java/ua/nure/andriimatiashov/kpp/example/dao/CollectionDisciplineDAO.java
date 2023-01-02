package ua.nure.andriimatiashov.kpp.example.dao;

import ua.nure.andriimatiashov.kpp.example.entity.Discipline;

import java.util.ArrayList;
import java.util.List;

public class CollectionDisciplineDAO implements DisciplineIDAO {

    private static int disciplineIncrement = 1;
    private static List<Discipline> disciplines = new ArrayList<>();

    static {
        disciplines.add(new Discipline(disciplineIncrement++, "Math", 30));
        disciplines.add(new Discipline(disciplineIncrement++, "Programming C++", 40));
        disciplines.add(new Discipline(disciplineIncrement++, "Physics", 40));
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        return disciplines;
    }

    @Override
    public void saveDiscipline(Discipline discipline) {
        if (discipline.getId() != 0) {
            Discipline update = disciplines.stream().filter(d -> d.getId() == discipline.getId()).findAny().orElse(null);
            if (update == null) {
                return;
            }
            update.setName(discipline.getName());
            update.setHourPerSemester(discipline.getHourPerSemester());
        }
        else{
            discipline.setId(disciplineIncrement++);
            disciplines.add(discipline);
        }
    }

    @Override
    public Discipline getDiscipline(long id) {
        return disciplines.stream().filter(discipline -> discipline.getId() == id).findAny().orElse(null);
    }

    @Override
    public void deleteDiscipline(long id) {
        disciplines.removeIf(discipline -> discipline.getId() == id);
    }


}
