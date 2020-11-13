package Data;

public class Course {


    private int courseId;
    private String courseTitle;
    private String departmentId;
    private String fkCourseinstructor;

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getFk_course_instructor() {
        return fk_course_instructor;
    }

    public void setFk_course_instructor(String fk_course_instructor) {
        this.fk_course_instructor = fk_course_instructor;
    }

    public void setFkCourseinstructor(String fkCourseinstructor) {
        this.fkCourseinstructor = fkCourseinstructor;
    }
}
