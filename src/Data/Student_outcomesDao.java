package Data;

import java.util.List;

public interface Student_outcomesDao {
    public boolean addStudent_outcomes(student_outcomes SO);
    public boolean updateStudent_outcomes(student_outcomes SO);
    public List<student_outcomes> GetSO();
}
