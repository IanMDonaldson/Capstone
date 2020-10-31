package Data;

import java.util.List;

public interface CourseDao {
    public boolean AddCourse(Course course);
    public boolean UpdateCourse(Course course);
    public List<Course> GetAllCourses();
}
