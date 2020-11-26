package Data;

import java.util.List;

public interface StudentWorkProductDao {
    public boolean addSWP(StudentWorkProduct swp);
    public boolean updateSWP(StudentWorkProduct swp);
    public List<StudentWorkProduct> getAllswp();
}
