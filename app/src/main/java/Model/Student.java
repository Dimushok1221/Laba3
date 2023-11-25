package Model;

public class Student {

    private int id;
    private String name;
    private String addTime;

    public Student() {
    }

    public Student(int id, String name, String addTime) {
        this.id = id;
        this.name = name;
        this.addTime = addTime;
    }

    public Student(String name, String addTime) {
        this.name = name;
        this.addTime = addTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
