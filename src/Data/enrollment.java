package Data;

public class enrollment {
    private int enrollmentId;



    private int fkEnrollmentcourse;

    public int getFkEnrollmentcourse() {
        return fkEnrollmentcourse;
    }

    public void setFkEnrollmentcourse(int fkEnrollmentcourse) {
        this.fkEnrollmentcourse = fkEnrollmentcourse;
    }

    private int fkEnrollmentstudent;
    private int fkEnrollmentterm;

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }



    public int getFkEnrollmentstudent() {
        return fkEnrollmentstudent;
    }

    public void setFkEnrollmentstudent(int fkEnrollmentstudent) {
        this.fkEnrollmentstudent = fkEnrollmentstudent;
    }

    public int getFkEnrollmentterm() {
        return fkEnrollmentterm;
    }

    public void setFkEnrollmentterm(int fkEnrollmentterm) {
        this.fkEnrollmentterm = fkEnrollmentterm;
    }
}

