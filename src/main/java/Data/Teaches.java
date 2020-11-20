package Data;

public class Teaches {
    public int getTeachesID() {
        return teachesID;
    }

    public void setTeachesID(int teachesID) {
        this.teachesID = teachesID;
    }

    public int getFkTeachescourse() {
        return fkTeachescourse;
    }

    public void setFkTeachescourse(int fkTeachescourse) {
        this.fkTeachescourse = fkTeachescourse;
    }

    public String getFkTeachesinstructor() {
        return fkTeachesinstructor;
    }

    public void setFkTeachesinstructor(String fkTeachesinstructor) {
        this.fkTeachesinstructor = fkTeachesinstructor;
    }

    public int getFkTeachesterm() {
        return fkTeachesterm;
    }

    public void setFkTeachesterm(int fkTeachesterm) {
        this.fkTeachesterm = fkTeachesterm;
    }

    private int teachesID;
    private int fkTeachescourse;
    private String fkTeachesinstructor;
    private int fkTeachesterm;
}
