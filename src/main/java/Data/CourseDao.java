package Data;

import Data.Course;

import java.util.List;

public interface CourseDao {
    public boolean addCourse(Course course);
    public boolean updateCourse(Course course);
    public List<Course> getAllCourses();
}
