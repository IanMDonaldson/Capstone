package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao {
    @Override
    public boolean addInstructor(Instructor instructor) {
        boolean isAddSuccessful = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(student_id,student_fname,student_lname)+ VALUES(?,?,?);");
            ps.setString(1,instructor.getInstructor_uname());
            ps.setString(2,instructor.getInstructor_pw());
            ps.setString(3,instructor.getInstructor_email());
            ps.setString(4,instructor.getInstructor_fname());
            ps.setString(5,instructor.getInstructor_lname());
            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0)
            {
                return false;
            }
            else
            {
                isAddSuccessful = true;
                ps.close();
                conn.close();
                return isAddSuccessful;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isAddSuccessful;
    }

    @Override
    public boolean updateInstructor(Instructor instructor) {
        boolean isUpdated = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student" + "set Instructor_pw = ?,Instructor_email = ?,Instructor_fname = ?,Instructor_lname=?" + "where Instructor_uname=?");
            ps.setString(1,instructor.getInstructor_uname());
            ps.setString(2,instructor.getInstructor_pw());
            ps.setString(3,instructor.getInstructor_email());
            ps.setString(4,instructor.getInstructor_fname());
            ps.setString(5,instructor.getInstructor_lname());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Instructor> GetAllEnrollment() {
        Connection conn = data.ConnectionFactory.getConnection();
        Instructor instructor = new Instructor();
        List<Instructor> instructors = new LinkedList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "instructor");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                instructor.setInstructor_uname(rs.getString("Instructor_uname"));
                instructor.setInstructor_pw(rs.getString("Instructor_pw"));
                instructor.setInstructor_email(rs.getString("Instructor_pw"));
                instructor.setInstructor_fname(rs.getString("Instructor_pw"));
                instructor.setInstructor_lname(rs.getString("Instructor_pw"));
                instructors.add(instructor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return instructors;
    }
}
