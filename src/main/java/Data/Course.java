package Data;

public class Course {
    private int courseID;
    private int courseNumber;
    private String department;
    private String courseTitle;

    public Course() {}

    public Course(int courseID, int courseNumber, String department, String courseTitle) {
        this.courseID = courseID;
        this.courseNumber = courseNumber;
        this.department = department;
        this.courseTitle = courseTitle;
    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}


