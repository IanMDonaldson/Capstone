package Data;

import java.util.List;

public interface termDao {
    public boolean Addterm(term term);
    public boolean Updateterm(term term);
    public boolean DeleteTerm(term term);
    public List<term> GetAllTerms();
}
