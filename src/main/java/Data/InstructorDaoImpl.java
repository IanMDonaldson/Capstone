package Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao {
    @Override
    public List<Instructor> getAllInstructor() {
        Connection conn = ConnectionFactory.getConnection();
        List<Instructor> instructorList = new LinkedList<Instructor>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where user_type = 'instructor';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setUsername(rs.getString("user_uname"));
                instructor.setFirstName(rs.getString("user_fname"));
                instructor.setLastName(rs.getString("user_lname"));
                instructor.setEmail(rs.getString("user_email"));
                instructorList.add(instructor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return instructorList;
    }

    public InstructorDaoImpl() {

    }

    @Override
    public boolean instructorExists(Instructor instructor) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where user.user_uname = ? AND user.user_pw = ?;");
            ps.setString(1, instructor.getUsername());
            ps.setString(2, instructor.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                rs.close();
                ps.close();
                conn.close();
                return true;
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Course> getCoursesTaught(String uname, int termID) {
        List<Course> courseList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from course " +
                    "join teaches t on course.course_id = t.fk_teaches_course " +
                    "where t.fk_teaches_instructor = ? AND t.fk_teaches_term = ?;");
            ps.setString(1, uname);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseID(rs.getInt("course_id"));
                course.setCourseNumber(rs.getInt("course_number"));
                course.setCourseTitle(rs.getString("course_title"));
                course.setDepartment(rs.getString("department_id"));
                courseList.add(course);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return courseList;
    }

    @Override
    public List<Instructor> getAllInstructor() {
        Connection conn = ConnectionFactory.getConnection();
        List<Instructor> instructorList = new LinkedList<Instructor>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user where user_type = 'instructor';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setUsername(rs.getString("user_uname"));
                instructor.setFirstName(rs.getString("user_fname"));
                instructor.setLastName(rs.getString("user_lname"));
                instructor.setEmail(rs.getString("user_email"));
                instructorList.add(instructor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return instructorList;
    }
}



