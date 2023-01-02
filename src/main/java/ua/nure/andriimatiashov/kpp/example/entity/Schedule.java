package ua.nure.andriimatiashov.kpp.example.entity;

import jakarta.validation.constraints.NotBlank;

public class Schedule {
    private long id;
    private String startTime;
    private String endTime;
    @NotBlank
    private String cabinetNumber;
    private long disciplineId;
    private String disciplineName;

    public Schedule(long id, String startTime, String endTime, String cabinetNumber, long disciplineId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cabinetNumber = cabinetNumber;
        this.disciplineId = disciplineId;
    }

    public Schedule(){

    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(String cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
