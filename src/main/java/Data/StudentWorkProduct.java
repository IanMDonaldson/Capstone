package Data;

import java.util.List;

public class StudentWorkProduct {
    private double grade;
    private String name;
    private int swpID;
    private List<StudentOutcome> so;



    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSwpID() {
        return swpID;
    }

    public void setSwpID(int swpID) {
        this.swpID = swpID;
    }


    public List<StudentOutcome> getSo() {
        return so;
    }

    public void setSo(List<StudentOutcome> so) {
        this.so = so;
    }
}
