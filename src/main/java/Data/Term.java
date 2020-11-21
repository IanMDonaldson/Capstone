package Data;

import java.util.LinkedList;
import java.util.List;

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
    private List<Course> courseList;

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
