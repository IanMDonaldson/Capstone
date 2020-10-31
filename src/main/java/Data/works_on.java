package Data;

import java.util.List;

public class works_on {
    private List<works_on> works_onList;
    private int works_on_id;
    private int fk_works_on_student;
    private int fk_works_on_swp;
    private int fk_works_on_term;

    public List<works_on> getWorks_onList() {
        return works_onList;
    }

    public void setWorks_onList(List<works_on> works_onList) {
        this.works_onList = works_onList;
    }

    public int getWorks_on_id() {
        return works_on_id;
    }

    public void setWorks_on_id(int works_on_id) {
        this.works_on_id = works_on_id;
    }

    public int getFk_works_on_student() {
        return fk_works_on_student;
    }

    public void setFk_works_on_student(int fk_works_on_student) {
        this.fk_works_on_student = fk_works_on_student;
    }

    public int getFk_works_on_swp() {
        return fk_works_on_swp;
    }

    public void setFk_works_on_swp(int fk_works_on_swp) {
        this.fk_works_on_swp = fk_works_on_swp;
    }

    public int getFk_works_on_term() {
        return fk_works_on_term;
    }

    public void setFk_works_on_term(int fk_works_on_term) {
        this.fk_works_on_term = fk_works_on_term;
    }
}
