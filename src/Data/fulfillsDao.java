package Data;

import java.util.List;

public interface fulfillsDao {
    public boolean addFulfills(fulfills fulfills);
    public boolean updateFulfills(fulfills fulfills);
    public List<fulfills> GetAllfulfills();
}
