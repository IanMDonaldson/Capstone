package Data;

import java.util.List;

public interface TermDao {
    public boolean addTerm(Term term);
    public boolean updateTerm(Term term);
    public List<Term> getAllterms();
}
