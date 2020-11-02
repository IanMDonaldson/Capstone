package Data;

import java.util.List;

public class works_on {
    private List<works_on> worksOnlist;
    private int worksOnid;

    public List<works_on> getWorksOnlist() {
        return worksOnlist;
    }

    public void setWorksOnlist(List<works_on> worksOnlist) {
        this.worksOnlist = worksOnlist;
    }

    public int getWorksOnid() {
        return worksOnid;
    }

    public void setWorksOnid(int worksOnid) {
        this.worksOnid = worksOnid;
    }

    public int getFkWorksonStudent() {
        return fkWorksonStudent;
    }

    public void setFkWorksonStudent(int fkWorksonStudent) {
        this.fkWorksonStudent = fkWorksonStudent;
    }

    public int getFkWorksonSwp() {
        return fkWorksonSwp;
    }

    public void setFkWorksonSwp(int fkWorksonSwp) {
        this.fkWorksonSwp = fkWorksonSwp;
    }

    public int getFkWorksonTerm() {
        return fkWorksonTerm;
    }

    public void setFkWorksonTerm(int fkWorksonTerm) {
        this.fkWorksonTerm = fkWorksonTerm;
    }

    private int fkWorksonStudent;
    private int fkWorksonSwp;
    private int fkWorksonTerm;
}
