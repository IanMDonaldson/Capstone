package Data;

import java.util.List;

public interface StudentOutcomeDao {
    public boolean addSO(StudentOutcome so);
    public boolean updateSO(StudentOutcome so);
    public List<StudentOutcome> getAllSO();
    public List<StudentOutcome> getAllSOforCourse(Course course);
    public StudentOutcome getSOforSWP(StudentWorkProduct swp);
}
