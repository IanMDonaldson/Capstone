package Data;

import java.util.List;

public interface EnrollmentDao {
   public boolean addEnrollment(Enrollment enrollment);
    public boolean updateEnrollment(Enrollment enrollment);
    public List<Enrollment> GetAllEnrollment();
}
