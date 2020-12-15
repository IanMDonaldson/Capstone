package Data;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SwpDaoImpl implements SwpDao {
    @Override
    public List<StudentOutcome> getStudentOutcomesForSwp(int swpID, int courseID, int termID) {
        return null;
    }

    @Override
    public StudentWorkProduct getSwp(int id) {
        StudentWorkProduct swp = new StudentWorkProduct();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student_work_product " +
                    "where swp_id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            swp.setSwpID(rs.getInt("swp_id"));
            swp.setName(rs.getString("swp_title"));
            swp.setInstructorUname(rs.getString("fk_swp_instructor"));
            swp.setCourseID(rs.getInt("fk_swp_course"));
            swp.setTermID(rs.getInt("fk_swp_term"));
            swp.setStudentID(rs.getInt("fk_swp_student"));
            swp.setGrade(rs.getFloat("swp_grade"));
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return swp;
    }

    @Override
    public List<StudentWorkProduct> getSwpsUnassoc2SO(int courseID, int termID) {
        List<StudentWorkProduct> swpList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student_work_product " +
                    "where fk_swp_course = ? AND fk_swp_term=? AND swp_id not in " +
                    "(select fk_fulfills_swp from fulfills);" );
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentWorkProduct swp = new StudentWorkProduct(
                        rs.getInt("swp_id"),
                        rs.getString("swp_title"),
                        rs.getInt("fk_swp_course"),
                        rs.getInt("fk_swp_term"),
                        rs.getString("fk_swp_instructor"),
                        rs.getInt("fk_swp_student"),
                        rs.getFloat("swp_grade")
                );
                swpList.add(swp);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return swpList;
    }

    @Override
    public boolean updateSwpGrades(List<StudentWorkProduct> swpList) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("update student_work_product set " +
                    "swp_grade = ? where swp_id = ?;" );
            for (StudentWorkProduct swp : swpList) {
                ps.setFloat(1, swp.getGrade());
                ps.setInt(2, swp.getSwpID());
                ps.addBatch();
            }
            assert ps != null;
            int[] updated = ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            if (updated.length == swpList.size()) {
                return true;
            }
        } catch (SQLException throwables) {
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
    public List<StudentOutcome> getStudentOutcomes(int swpID, int courseID, int termID) {
        List<StudentOutcome> soList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select student_outcome_id,student_outcome_title,swp.swp_grade from student_outcome " +
                    "join fulfills f on student_outcome.student_outcome_id = f.fk_fulfills_so " +
                    "join student_work_product swp on f.fk_fulfills_swp = swp.swp_id " +
                    "where swp.fk_swp_term=? AND swp.fk_swp_course=? AND swp.swp_id=?;");
            ps.setInt(1, termID);
            ps.setInt(2, courseID);
            ps.setInt(3, swpID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentOutcome so = new StudentOutcome();
                so.setSoID(rs.getInt("student_outcome_id"));
                so.setPerformance(rs.getFloat("swp_grade"));
                so.setTitle(rs.getString("student_outcome_title"));
                soList.add(so);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return soList;
    }



    @Override
    public boolean assocStudentOutcomes(List<StudentOutcome> studentOutcomes, List<StudentWorkProduct> swpList) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            ps = conn.prepareStatement("insert into fulfills " +
                    "(fk_fulfills_swp, fk_fulfills_so) values(?,?);");
            for (StudentWorkProduct swp : swpList) {
                for(StudentOutcome so : studentOutcomes) {
                    ps.setInt(1,swp.getSwpID());
                    ps.setInt(2, so.getSoID());
                    ps.addBatch();
                }
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
                assert ps != null;
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean assocStudentOutcomes(Connection conn, List<StudentOutcome> studentOutcomes, List<StudentWorkProduct> swpList) {
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            ps = conn.prepareStatement("insert into fulfills " +
                    "(fk_fulfills_swp, fk_fulfills_so) values(?,?);");
            for (StudentWorkProduct swp : swpList) {
                for(StudentOutcome so : studentOutcomes) {
                    ps.setInt(1,swp.getSwpID());
                    ps.setInt(2, so.getSoID());
                    ps.addBatch();
                }
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
                assert ps != null;
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }
    /*needs to go through each student, associating a SWP with each student in the course, setting grade to null
    * initially */
    @Override
    public boolean createSwp(String swpTitle, int courseID, int termID, String instructorUname) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.getStudentsEnrolled2Course(courseID,termID);
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into student_work_product " +
                    "(swp_title, fk_swp_instructor, fk_swp_course, fk_swp_term, fk_swp_student) " +
                    "VALUES(?,?,?,?,?);");
            for (Student s :
                    students) {
                ps.setString(1, swpTitle);
                ps.setString(2, instructorUname);
                ps.setInt(3, courseID);
                ps.setInt(4, termID);
                ps.setInt(5, s.getStudentId());
                ps.addBatch();
            }
            assert ps!=null;
            ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException e){
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                assert ps != null;
                ps.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean createOldSwp4NewStudents(Connection conn, List<Student> students, String swpTitle, int courseID, int termID, String instructorUname) {
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into student_work_product " +
                    "(swp_title, fk_swp_instructor, fk_swp_course, fk_swp_term, fk_swp_student) " +
                    "VALUES(?,?,?,?,?);");
            for (Student s :
                    students) {
                ps.setString(1, swpTitle);
                ps.setString(2, instructorUname);
                ps.setInt(3, courseID);
                ps.setInt(4, termID);
                ps.setInt(5, s.getStudentId());
                ps.addBatch();
            }
            assert ps!=null;
            ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException e){
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                assert ps != null;
                ps.close();
            } catch (SQLException f) {
                f.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
}
