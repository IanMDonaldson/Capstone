package Data;

public class StudentWorkProduct {
    private double swp_grade;
    private String swp_name;
    private int swp_id;
    private int fk_swp_student;
    private int fk_swp_teaches;

    public void setSwp_grade(double swp_grade) {
        this.swp_grade = swp_grade;
    }

    public double getSwp_grade() {
        return swp_grade;
    }

    public void setSwp_id(int swp_id) {
        this.swp_id = swp_id;
    }

    public int getSwp_id() {
        return swp_id;
    }

    public void setSwp_name(String swp_name) {
        this.swp_name = swp_name;
    }

    public String getSwp_name() {
        return swp_name;
    }

    public void setFk_swp_student(int fk_swp_student) {
        this.fk_swp_student = fk_swp_student;
    }

    public int getFk_swp_student() {
        return fk_swp_student;
    }

    public void setFk_swp_teaches(int fk_swp_teaches) {
        this.fk_swp_teaches = fk_swp_teaches;
    }

    public int getFk_swp_teaches() {
        return fk_swp_teaches;
    }
}
