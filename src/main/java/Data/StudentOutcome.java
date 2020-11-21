package Data;

public class StudentOutcome {
    private int soID;
    private String title;
    private float performance;
    public StudentOutcome() {
        this.title=null;
        this.performance=-1;
        this.soID=-1;
    };
    public StudentOutcome(int id, float performance, String title) {
        this.soID = id;
        this.performance = performance;
        this.title = title;
    }
    public int getSoID() {
        return soID;
    }

    public void setSoID(int soID) {
        this.soID = soID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPerformance() {
        return performance;
    }

    public void setPerformance(float performance) {
        this.performance = performance;
    }
}
