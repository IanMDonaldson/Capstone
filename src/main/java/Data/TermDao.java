package Data;

public interface TermDao {
    public boolean termExists(Term term);
    public boolean addTerm(Term term);
    public boolean updateTerm(Term term);
    public boolean getTerm(Term term);
    public int getNewTermID();
}
