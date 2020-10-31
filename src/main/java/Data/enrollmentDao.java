package Data;

import java.util.List;

public interface enrollmentDao {
   public boolean addEnrollment(enrollment enrollment);
    public boolean updateEnrollment(enrollment enrollment);
    public List<enrollment> GetAllEnrollment();
}
