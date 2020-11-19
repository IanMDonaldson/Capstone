package Data;

import java.util.List;

public class SODaoImpl implements StudentOutcomeDao {
    @Override
    public boolean addSO(StudentOutcome so) {
        return false;
    }

    @Override
    public boolean updateSO(StudentOutcome so) {
        return false;
    }

    @Override
    public List<StudentOutcome> getAllSO() {
        return null;
    }

    @Override
    public List<StudentOutcome> getAllSOforCourse(Course course) {
        return null;
    }

    @Override
    public StudentOutcome getSOforSWP(StudentWorkProduct swp) {
        return null;
    }
}
