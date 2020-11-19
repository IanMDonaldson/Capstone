package Data;

public interface TermDao {
    public boolean termExists(Term term);
    public boolean addTerm(Term term);
    public boolean updateTerm(Term term);
    public Term getTerm(int Id);
    public int getNewTermID();
    public boolean assocCourse(Term term,String newlyAssocCourseID);
}
