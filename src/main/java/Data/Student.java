package Data;

import java.util.List;

public class Student {
    private int studentId;
    private List<Student> studentList;
    private String studentFname;
    private String StudentLname;
    private int isGraded;
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getStudentFname() {
        return studentFname;
    }

    public void setStudentFname(String studentFname) {
        this.studentFname = studentFname;
    }

    public String getStudentLname() {
        return StudentLname;
    }

    public void setStudentLname(String studentLname) {
        StudentLname = studentLname;
    }

   public void setIsGraded(int grade)
   {
       this.isGraded=grade;
   }

    public int getIsGraded() {
        return isGraded;
    }
}
