package Data;

public class Teaches {
    private int teachesId;
    private int fkteachescourse;
    private int fkTeachesinstructor;
    private int fkTeachesterm;

    public int getTeachesId() {
        return teachesId;
    }

    public void setTeachesId(int teachesId) {
        this.teachesId = teachesId;
    }

    public int getFkteachescourse() {
        return fkteachescourse;
    }

    public void setFkteachescourse(int fkteachescourse) {
        this.fkteachescourse = fkteachescourse;
    }

    public String getFkTeachesinstructor() {
        return fkTeachesinstructor;
    }

    public void setFkTeachesinstructor(int fkTeachesinstructor) {
        this.fkTeachesinstructor = fkTeachesinstructor;
    }

    public int getFkTeachesterm() {
        return fkTeachesterm;
    }

    public void setFkTeachesterm(int fkTeachesterm) {
        this.fkTeachesterm = fkTeachesterm;
    }
}
