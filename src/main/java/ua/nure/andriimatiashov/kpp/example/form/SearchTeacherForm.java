package ua.nure.andriimatiashov.kpp.example.form;

import jakarta.validation.constraints.NotBlank;

public class SearchTeacherForm {
    @NotBlank
    private String name;

    public SearchTeacherForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
