package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class enrollmentDaoImpl implements EnrollmentDao {
    @Override
    public boolean addEnrollment(Enrollment enrollment)
         {
            boolean isAddSuccessful = false;
            Connection conn = ConnectionFactory.getConnection();
            try{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO enrollment(enrollment_id,enrollment_student,fk_enrollment_course,fk_enrollment_term)"+ "VALUES (?,?,?,?);");
                ps.setInt(1,enrollment.getEnrollmentId());
                ps.setInt(2,enrollment.getFkEnrollmentstudent());
                ps.setInt(3,enrollment.getFkEnrollmentcourse());
                ps.setInt(4,enrollment.getFkEnrollmentterm());

                int rowChanged = ps.executeUpdate();
                if (rowChanged == 0)
                {
                    return isAddSuccessful;
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
        public boolean updateEnrollment(Enrollment enrollment) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update enrollment" + "set enrollment_id = ?,fk_enrollment_course = ? , fk_enrollment_student=? ,fk_enrollment_term");
            ps.setInt(1,enrollment.getEnrollmentId());
            ps.setInt(2,enrollment.getFkEnrollmentcourse());
            ps.setInt(3,enrollment.getFkEnrollmentstudent());
            ps.setInt(4,enrollment.getFkEnrollmentterm());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Enrollment> GetAllEnrollment() {
        return null;
    }

}
