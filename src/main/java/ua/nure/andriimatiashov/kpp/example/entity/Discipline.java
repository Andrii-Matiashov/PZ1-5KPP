package ua.nure.andriimatiashov.kpp.example.entity;

public class Discipline {
    private long id;
    private String name;
    private int hourPerSemester;

    public Discipline() {
    }

    public Discipline(long id, String name, int hourPerSemester) {
        this.id = id;
        this.name = name;
        this.hourPerSemester = hourPerSemester;
    }

    public int getHourPerSemester() {
        return hourPerSemester;
    }

    public void setHourPerSemester(int hourPerSemester) {
        this.hourPerSemester = hourPerSemester;
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

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hourPerSemester=" + hourPerSemester +
                '}';
    }
}
