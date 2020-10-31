package Data;

public class enrollment {
    private int enrollment_id;
    private int fk_enrollment_course;

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public int getFk_enrollment_course() {
        return fk_enrollment_course;
    }

    public void setFk_enrollment_course(int fk_enrollment_course) {
        this.fk_enrollment_course = fk_enrollment_course;
    }

    public int getFk_enrollment_student() {
        return fk_enrollment_student;
    }

    public void setFk_enrollment_student(int fk_enrollment_student) {
        this.fk_enrollment_student = fk_enrollment_student;
    }

    public int getFk_enrollment_term() {
        return fk_enrollment_term;
    }

    public void setFk_enrollment_term(int fk_enrollment_term) {
        this.fk_enrollment_term = fk_enrollment_term;
    }

    private int fk_enrollment_student;
    private int fk_enrollment_term;
}

