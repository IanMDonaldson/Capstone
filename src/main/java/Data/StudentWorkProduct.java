package Data;

import java.util.List;

public class StudentWorkProduct {
    private float grade;
    private String name;
    private int swpID;
    private List<StudentOutcome> soList;
    private int studentID;
    private String instructorID;
    private int termID;
    private int courseID;



    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
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


    public List<StudentOutcome> getSoList() {
        return soList;
    }

    public void setSoList(List<StudentOutcome> so) {
        this.soList = so;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
