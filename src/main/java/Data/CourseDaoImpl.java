package Data;

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
            ps.setString(2,course.getDepartment());
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
            ps.setString(3,course.getDepartment());
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
    /* todo: maybe change the parameters of the function based on what information is pulled from the page
    *       when adding a set of students and their student work products*/
    @Override
    public boolean assocSWPs2Course(List<StudentWorkProduct> swpList, int courseID, int termID, String instructorUname) {
        SwpDaoImpl swpDaoImpl = new SwpDaoImpl();
        PreparedStatement ps = null;
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            for (StudentWorkProduct swp: swpList) {
                ps = conn.prepareStatement("insert into " +
                        "student_work_product(swp_title, swp_grade, fk_swp_course,fk_swp_term,fk_swp_student,fk_swp_instructor) " +
                        "VALUES(?,?,?,?,?,?);");
                ps.setString(1, swp.getName());
                ps.setFloat(2, swp.getGrade());
                ps.setInt(3, courseID);
                ps.setInt(4, termID);
                ps.setInt(5, swp.getStudentID());
                ps.setString(6, instructorUname);
                //opens another connection...may cause double null
                swpDaoImpl.assocStudentOutcomes(swp.getSoList(), swp.getSwpID());
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
            ps.setString(1, instructor.getUsername());
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
    public Instructor getInstructor4Course(int courseID, int termID) {
        Instructor instructor = new Instructor();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select user_uname, user_fname, user_email, user_type from user " +
                    "join teaches t on user.user_uname = t.fk_teaches_instructor " +
                    "where fk_teaches_course = ? AND fk_teaches_term = ?;");
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                instructor.setEmail(rs.getString("user_email"));
                instructor.setUsername(rs.getString("user_uname"));
                instructor.setFirstName(rs.getString("user_fname"));
                instructor.setLastName(rs.getString("user_lname"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return instructor;
    }

    @Override
    public List<Student> getStudents4Course(int courseID, int termID) {
        List<Student> studentList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {PreparedStatement ps = conn.prepareStatement("select * from student " +
                "join enrollment e on student.student_id = e.fk_enrollment_student " +
                "where e.fk_enrollment_course = ? AND e.fk_enrollment_term=?;");
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentFname(rs.getString("student_fname"));
                student.setStudentLname(rs.getString("student_lname"));
                studentList.add(student);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public List<StudentWorkProduct> getSWPs4Course(int courseID, int termID) {
        List<StudentWorkProduct> swpList = new LinkedList<>();
        SwpDaoImpl swpDaoImpl = new SwpDaoImpl();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student_work_product " +
                    "where fk_swp_course = ? AND fk_swp_term = ?;");
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentWorkProduct swp = new StudentWorkProduct();
                swp.setGrade(rs.getFloat("swp_grade"));
                swp.setName(rs.getString("swp_title"));
                swp.setSwpID(rs.getInt("swp_id"));
                swp.setStudentID(rs.getInt("fk_swp_student"));
                swp.setInstructorID(rs.getString("fk_swp_instructor"));
                swp.setSoList(null);
                swpList.add(swp);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return swpList;
    }

    @Override
    public List<StudentOutcome> getSOs4Course(int courseID, int termID) {
        return null;
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
        List<StudentWorkProduct> swps = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();

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
