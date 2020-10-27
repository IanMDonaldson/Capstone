package Data;

import java.util.List;

public interface works_onDao {
    public boolean AddWorks_on(works_on works);
    public boolean deleteWorks_on(works_on works);
    public boolean updateWorks_on(works_on works);
    public List<works_on> getAllWorks();
}
