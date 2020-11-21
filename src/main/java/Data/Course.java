package Data;

import java.util.List;

public class Course {


    private int courseId;
    private String courseTitle;
    private String departmentId;



    private int courseNumber;
    private String fkCourseinstructor;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getFkCourseinstructor() {
        return fkCourseinstructor;
    }

    public void setFkCourseinstructor(String fkCourseinstructor) {
        this.fkCourseinstructor = fkCourseinstructor;
    }
    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
}
