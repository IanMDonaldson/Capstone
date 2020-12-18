package Data;

import java.util.List;

public class StudentWorkProduct {
    private float grade;
    private int courseID;
    private int studentID;
    private int swpID;
    private int termID;
    private List<StudentOutcome> soList;
    private String instructorUname;
    private String name;
    public StudentWorkProduct() {}

    public StudentWorkProduct(int swpID, String name, int courseID, int termID, String instructorUname, int studentID, float grade, List<StudentOutcome> soList) {
        this.swpID = swpID;
        this.name = name;
        this.courseID = courseID;
        this.termID = termID;
        this.instructorUname = instructorUname;
        this.grade = grade;
        this.soList = soList;
    }
    public StudentWorkProduct(int swpID, String name, int courseID, int termID, String instructorUname, int studentID, float grade) {
        this.swpID = swpID;
        this.name = name;
        this.courseID = courseID;
        this.termID = termID;
        this.instructorUname = instructorUname;
        this.studentID = studentID;
        this.grade = grade;
        this.soList = null;
    }

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

    public String getInstructorUname() {
        return instructorUname;
    }

    public void setInstructorUname(String instructorUname) {
        this.instructorUname = instructorUname;
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
