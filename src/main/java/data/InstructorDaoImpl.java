package Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorDaoImpl implements InstructorDao {
    public InstructorDaoImpl() {

    }

    @Override
    public boolean instructorExists(Instructor instructor) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from instructor where instructor.instructor_uname = ? AND instructor.instructor_pw = ?;");
            ps.setString(1, instructor.getUsername());
            ps.setString(2, instructor.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
