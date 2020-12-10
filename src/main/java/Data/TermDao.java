package Data;

import java.sql.SQLException;
import java.util.List;

public interface TermDao {
    public boolean termExists(Term term);
    public boolean addTerm(Term term);
    public boolean updateTerm(Term term);
    public Term getTerm(int Id);
    List<Term> getAllTerms();
    Term getLastTerm();
    /*inside 3.3.2 Setup - Department's Courses- Admin will enter the information
     *for the set of courses offered by the department
     * This can mean either, THIS FUNCTION - course is already created and we need to associate it to a term
     * OR CourseDaoImpl.addCourse(Course course) we need to create a new course
     * this inserts into teaches table leaving instructor NULL because the adminSetup associates an instructor to course later*/
    boolean assocCourse(int termID, int courseID);


}
