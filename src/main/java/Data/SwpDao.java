package Data;

import java.util.List;

public interface SwpDao {
    List<StudentOutcome> getStudentOutcomes(int swpID, int courseID, int termID);
    boolean assocStudentOutcomes(List<StudentOutcome> studentOutcomes, int swpID);
}
