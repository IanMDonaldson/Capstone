package Data;

import Data.term;

import java.util.List;

public interface termDao {
    public boolean addTerm(term term);
    public boolean updateTerm(term term);
    public List<String> getAllterms();
}
