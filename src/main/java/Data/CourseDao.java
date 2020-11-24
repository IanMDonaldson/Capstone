package Data;

import java.util.List;

public interface CourseDao {
    Instructor getInstructor4Course(int courseID, int termID);

    List<Course> getAllCourses();

    List<Student> getStudents4Course(int courseID, int termID);

    List<StudentOutcome> getCourseSOMean(int courseID, int termID);
    List<StudentOutcome> getCourseSOMedian(int courseID, int termID);
    List<StudentOutcome> getCourseSORaw(int courseID, int termID);
    List<StudentOutcome> getSOs4Course(int courseID, int termID);

    List<StudentWorkProduct> getCoursesMeanSWPOverTime(int courseID, int[] termIDRange);
    List<StudentWorkProduct> getSWPs4Course(int courseID, int termID);

    boolean associateInstructor(Instructor instructor, int termID, int courseID);
    boolean associateStudents(List<Student> students, int termID, int courseID);
    boolean assocSWPs2Course(List<StudentWorkProduct> swpList, int courseID, int termID, String instructorUname);
    boolean insertCourse(Course course);
    boolean updateCourse(Course course);
}
