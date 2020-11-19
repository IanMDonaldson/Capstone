package Data;

import java.util.List;

public interface SWPDao {
    public boolean addSWP(StudentWorkProduct swp);
    public boolean updateSWP(StudentWorkProduct swp);
    public List<StudentWorkProduct> getAllSWP();
    public List<StudentWorkProduct> getAllSWPforCourse();
}
