package Data;

public class Course {

    private int course_id;
    private String courseTitle;
    private String department_id;
    private String fk_course_instructor;
    private String course_num;
    //private String departmentId;
// private String fkCourseinstructor;




    public void setCourseId(int course_id) {
        this.course_id = course_id;
    }
    public int getCourseId() {
        return course_id;
    }

    public void setCourse_title(String course_title) {
        this.courseTitle = course_title;
    }

    public String getCourse_title() {
        return courseTitle;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_id() {
        return department_id;
    }


    public void setFk_course_instructor(String fk_course_instructor) {
        this.fk_course_instructor = fk_course_instructor;
    }

    public String getFk_course_instructor() {
        return fk_course_instructor;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getCourse_num() {
        return course_num;
    }
}


