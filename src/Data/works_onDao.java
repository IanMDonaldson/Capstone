package Data;

import java.util.List;

public interface works_onDao {
    public boolean addWorkson(works_on works);
    public boolean updateWorkson(works_on works);
    public List<works_on> getAllworks();
}
