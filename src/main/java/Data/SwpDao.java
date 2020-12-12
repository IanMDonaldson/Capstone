package Data;

import java.sql.Connection;
import java.util.List;

public interface SwpDao {
    StudentWorkProduct getSwp(int id);
    List<StudentWorkProduct> getSwpsUnassoc2SO(int courseID, int termID);
    List<StudentOutcome> getStudentOutcomes(int swpID, int courseID, int termID);
    List<StudentOutcome> getStudentOutcomesForSwp(int swpID, int courseID, int termID);

    boolean assocStudentOutcomes(List<StudentOutcome> studentOutcomes, List<StudentWorkProduct> swpList);
    boolean createSwp(String swp, int courseID, int termID, String instructorUname);
    boolean updateSwpGrades(List<StudentWorkProduct> swpList);
}
