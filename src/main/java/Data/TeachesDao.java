package Data;

import java.util.List;

public interface TeachesDao {
    public boolean addTeaches(Teaches Teaches);
    public boolean updateTeaches(Teaches Teaches);
    public List<term> getAllterms();
}
