package Data;

import java.util.List;

public interface InstructorDao {
    public boolean addInstructor(Instructor instructor);
    public boolean updateInstructor(Instructor instructor);
    public List<Instructor> GetAllEnrollment();
}
