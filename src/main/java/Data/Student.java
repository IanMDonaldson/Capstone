package Data;

import java.util.List;

public class Student {
    private int student_id;
    private List<Student> studentList;
    private String student_fname;
    private String Student_lname;
    private List<StudentWorkProduct> swpList;

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getStudent_fname() {
        return student_fname;
    }

    public void setStudent_fname(String student_fname) {
        this.student_fname = student_fname;
    }

    public String getStudent_lname() {
        return Student_lname;
    }

    public void setStudent_lname(String student_lname) {
        Student_lname = student_lname;
    }
}
