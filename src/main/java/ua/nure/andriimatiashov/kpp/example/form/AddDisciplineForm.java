package ua.nure.andriimatiashov.kpp.example.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AddDisciplineForm {

    private long id;
    @NotBlank
    private String name;
    @Min(1)
    @Max(200)
    //from 1-200 hours
    private int hourPerSemester;

    public AddDisciplineForm() {
    }

    public AddDisciplineForm(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHourPerSemester() {
        return hourPerSemester;
    }

    public void setHourPerSemester(int hourPerSemester) {
        this.hourPerSemester = hourPerSemester;
    }

    @Override
    public String toString() {
        return "AddDisciplineForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hourPerSemester=" + hourPerSemester +
                '}';
    }
}
