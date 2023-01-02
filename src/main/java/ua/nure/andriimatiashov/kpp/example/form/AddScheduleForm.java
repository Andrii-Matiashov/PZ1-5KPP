package ua.nure.andriimatiashov.kpp.example.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public class AddScheduleForm {
    private long id;
    private String startTime;
    private String endTime;
    @NotBlank
    private String cabinetNumber;
    @Min(1)
    private long disciplineId;
    private String disciplineName;

    public AddScheduleForm() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(String cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(long disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    @Override
    public String toString() {
        return "AddScheduleForm{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", cabinetNumber='" + cabinetNumber + '\'' +
                ", disciplineId=" + disciplineId +
                ", disciplineName='" + disciplineName + '\'' +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime.replace('T',' ')+ ":00";
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime.replace('T',' ') + ":00";
    }
}
