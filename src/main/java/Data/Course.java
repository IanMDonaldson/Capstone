package Data;

public class Course {


    private int courseId;
    private String courseTitle;
    private String departmentId;
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
}
