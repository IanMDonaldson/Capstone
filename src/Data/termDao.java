package Data;

import java.util.List;

public interface termDao {
    public boolean addTerm(term term);
    public boolean updateTerm(term term);
    public List<term> getAllterms();
}
