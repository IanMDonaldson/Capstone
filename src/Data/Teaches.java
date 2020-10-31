package Data;

import java.util.List;

public class Teaches {
    private int teaches_id;
    private int fk_teaches_course;
    private int fk_teaches_instructor;
    private int fk_teaches_term;

    public int getTeaches_id() {
        return teaches_id;
    }

    public void setTeaches_id(int teaches_id) {
        this.teaches_id = teaches_id;
    }

    public int getFk_teaches_course() {
        return fk_teaches_course;
    }

    public void setFk_teaches_course(int fk_teaches_course) {
        this.fk_teaches_course = fk_teaches_course;
    }

    public String getFk_teaches_instructor() {
        return fk_teaches_instructor;
    }

    public void setFk_teaches_instructor(int fk_teaches_instructor) {
        this.fk_teaches_instructor = fk_teaches_instructor;
    }

    public int getFk_teaches_term() {
        return fk_teaches_term;
    }

    public void setFk_teaches_term(int fk_teaches_term) {
        this.fk_teaches_term = fk_teaches_term;
    }

    public List<Teaches> getGetTeacheslist() {
        return getTeacheslist;
    }

    public void setGetTeacheslist(List<Teaches> getTeacheslist) {
        this.getTeacheslist = getTeacheslist;
    }

    private List<Teaches> getTeacheslist;


}
