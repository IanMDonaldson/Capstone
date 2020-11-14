package Data;

public class Term {
    public int getTermId() {
        return TermId;
    }

    public void setTermId(int termId) {
        TermId = termId;
    }

    public int getTermYear() {
        return TermYear;
    }

    public void setTermYear(int termYear) {
        TermYear = termYear;
    }

    public String getTermName() {
        return TermName;
    }

    public void setTermName(String termName) {
        TermName = termName;
    }

    private  int TermId;
    private int TermYear;
    private String TermName;
}
