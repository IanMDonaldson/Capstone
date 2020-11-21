package Data;

import java.util.List;

public interface CourseDao {
    boolean insertCourse(Course course);
    boolean updateCourse(Course course);
    List<Course> getAllCourses();
    boolean associateStudents(List<Student> students, int termID, int courseID);
     /*assumes that TermDaoImpl.assocCourse has already been called
     * - meaning that theres an entry inside of TEACHES table where instructor = null
     *   that's where we will insert the instructor*/
    boolean associateInstructor(Instructor instructor, int termID, int courseID);
    List<StudentWorkProduct> getCoursesMeanSWPOverTime(int courseID, int[] termIDRange);
    List<StudentOutcome> getCourseSORaw(int courseID, int termID);
    List<StudentOutcome> getCourseSOMean(int courseID, int termID);
    List<StudentOutcome> getCourseSOMedian(int courseID, int termID);

}
