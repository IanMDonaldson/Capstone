package Data;

import java.util.List;

public interface CourseDao {
    public boolean addCourse(Course course);
    public boolean updateCourse(Course course);
    public List<Course> getAllCourses();
    public void associateStudents(List<Student> students, Term term);
    public void associateInstructor(Instructor instructor, Term term);
}
