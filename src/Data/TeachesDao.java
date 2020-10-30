package Data;

import java.util.List;

public interface TeachesDao {
    public boolean AddTeaches(Teaches Teaches);
    public boolean UpdateTeaches(Teaches Teaches);
    public void DeleteTeaches(Teaches Teaches);
    public List<term> GetAllTerms();
}
