package Data;

import java.util.List;

public class Student {
    private int studentId;
    private List<StudentWorkProduct> swpList;
    private String studentFname;
    private String StudentLname;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public List<StudentWorkProduct> getSwpList() {
        return swpList;
    }

    public void setSwpList(List<StudentWorkProduct> swpList) {
        this.swpList = swpList;
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

}
