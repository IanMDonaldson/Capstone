package Data;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean insertCourse(Course course) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO course(course_title, department_id, course_number) VALUES(?,?,?);");
            ps.setString(1,course.getCourseTitle());
            ps.setString(2,course.getDepartmentId());
            ps.setInt(3,course.getCourseNumber());
            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0)
            {
                return false;
            }
            else
            {
                ps.close();
                conn.close();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update course set course_title=?,course_number=?,department_id=?");
            ps.setString(1,course.getCourseTitle());
            ps.setInt(2,course.getCourseNumber());
            ps.setString(3,course.getDepartmentId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                return false;
            } else {
                ps.close();
                conn.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



    @Override
    public List<Course> getAllCourses() {
        return null;
    }
    /* Inserts into the ENROLLMENT TABLE a list of students to a course
    *  checks to see if student is associated with a course already by searching
    *  database for a row matching all of STUDENTID, COURSEID, TERMID
    *                 :D       */
    @Override
    public boolean associateStudents(List<Student> students, int courseID, int termID) {
        PreparedStatement ps = null;
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            for (Student student : students) {
                ps = conn.prepareStatement("insert into " +
                        "enrollment(fk_enrollment_student, fk_enrollment_course, fk_enrollment_term) VALUES(?,?,?);");
                ps.setInt(1, student.getStudentId());
                ps.setInt(2, courseID);
                ps.setInt(3, termID);
                ps.addBatch();
            }
            assert ps != null;
            ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException throwables) {
            /* if execute batch throws an exception( means one of them failed to execute )
             *   we can rollback the changes*/
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    /*assumes that TermDaoImpl.assocCourse has already been called
    * - meaning that theres an entry inside of TEACHES table where instructor = null
    *   that's where we will insert the instructor*/
    public boolean associateInstructor(Instructor instructor, int termID, int courseID) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("update teaches set" +
                    " fk_teaches_instructor =? where fk_teaches_term=? AND fk_teaches_course=?;");
            ps.setString(1, instructor.getInstructorUname());
            ps.setInt(2, termID);
            ps.setInt(3, courseID);
            int rowchanged = ps.executeUpdate();
            if (rowchanged == 0) {
                return false;
            } else {
                ps.close();
                conn.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StudentWorkProduct> getCoursesMeanSWPOverTime(int courseID, int[] termIDRange) {
        /*- StudentOutcome class has private double performance;
            - create sum, counter, studentOutcome INT variable and mean DOUBLE variable
            - Instrcutor instructor = new instructor;
            - grab first row in ResultSet and set that studentOUtcome variable to the first Row's so_id
            - for each row in ResultSet
                - sum += resultset.getInt(grade)
                - counter++;
                - IF ResultSet.next(getInt(so_id) != studentOutcome
                    - mean = sum/counter;
                    - So.setPerformance(mean)
                    - mean,counter,sum=0;
                    - studentOutcome (local variable) = ResultSet.next(getInt(fulfills.fk_fulfills_so))
                    - StudentOUtcome so = new studentOutcome;
                    - so.setPerformance(mean);
                    - soList.add(so);*/

        return null;
    }

    /* each of the student Outcomes in the returned list holds its own performance variable
    *
    * - is a list of all student outcome performances for each student outcome*/
    @Override
    public List<StudentOutcome> getCourseSORaw(int courseID, int termID) {
        List<StudentOutcome> soList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select swp_grade, so.* from student_work_product " +
                    "join fulfills f on student_work_product.swp_id = f.fk_fulfills_swp " +
                    "join student_outcome so on fk_fulfills_so = so.student_outcome_id " +
                    "where fk_swp_course = ? AND fk_swp_term = ? " +
                    "order by f.fk_fulfills_so;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentOutcome so = new StudentOutcome(rs.getInt("student_outcome_id"),
                        rs.getFloat("swp_grade"), rs.getString("student_outcome_title"));
                soList.add(so);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return soList;
    }

    /* returns a list of studentOutcomes each having the mean performance for those student outcomes for a course
    *   */
    @Override
    public List<StudentOutcome> getCourseSOMean(int courseID, int termID) {
        List<StudentOutcome> soList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select swp_grade, so.* from student_work_product " +
                    "join fulfills f on student_work_product.swp_id = f.fk_fulfills_swp " +
                    "join student_outcome so on fk_fulfills_so = so.student_outcome_id " +
                    "where fk_swp_course = ? AND fk_swp_term = ? " +
                    "order by f.fk_fulfills_so;");
            ResultSet rs = ps.executeQuery();
            rs.next();

            float counter = 1;
            int currOutcomeID = rs.getInt("student_outcome_id");
            float sum = rs.getFloat("swp_grade");

            while (rs.next()) {
                if(rs.getInt("student_outcome_id") != currOutcomeID) {
                    StudentOutcome so = new StudentOutcome(currOutcomeID,
                            (sum/counter),
                            rs.getString("student_outcome_title"));
                    soList.add(so);
                    sum=rs.getFloat("swp_grade");
                    counter=1;
                    currOutcomeID=rs.getInt("student_outcome_id");
                } else {
                    counter++;
                    sum += rs.getFloat("swp_grade");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return soList;
    }

    @Override
    public List<StudentOutcome> getCourseSOMedian(int courseID, int termID) {
        List<StudentOutcome> soList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select swp_grade, so.* from student_work_product " +
                    "join fulfills f on student_work_product.swp_id = f.fk_fulfills_swp " +
                    "join student_outcome so on fk_fulfills_so = so.student_outcome_id " +
                    "where fk_swp_course = ? AND fk_swp_term = ? " +
                    "order by f.fk_fulfills_so;");
            ResultSet rs = ps.executeQuery();
            rs.next();

            float counter = 1;
            int currOutcomeID = rs.getInt("student_outcome_id");

            ArrayList<Float> grades = new ArrayList<Float>();
            grades.add(rs.getFloat("swp_grade"));
            while (rs.next()) {
                if(rs.getInt("student_outcome_id") != currOutcomeID) {
                    float median;
                    int size = grades.size();
                    if (grades.size() %2 == 0) {
                        median = ((grades.get(size-1)+(grades.get(size+1))/2));
                        StudentOutcome so = new StudentOutcome(currOutcomeID,
                                median,
                                rs.getString("student_outcome_title"));
                        soList.add(so);
                    } else {
                        median = grades.get(size/2);
                        StudentOutcome so = new StudentOutcome(currOutcomeID,
                                median,
                                rs.getString("student_outcome_title"));
                        soList.add(so);
                    }
                    counter=1;
                    currOutcomeID=rs.getInt("student_outcome_id");
                    grades.clear();
                    grades.add(rs.getFloat("swp_grade"));
                    counter=1;
                    currOutcomeID=rs.getInt("student_outcome_id");
                } else {
                    counter++;
                    grades.add(rs.getFloat("swp_grade"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return soList;
    }
}
