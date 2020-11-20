package Data;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    public boolean addCourse(Course course);
    public boolean updateCourse(Course course);
    public List<Course> getAllCourses();

    List<Course> getCoursesForTerm(Term term) throws SQLException;
}
