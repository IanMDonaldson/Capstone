package Data;

import java.util.List;

public interface InstructorDao {
    public boolean instructorExists(Instructor instructor);
    List<Course> getCoursesTaught(String uname, int termID);

}
