package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class enrollmentDaoImpl implements enrollmentDao {
    @Override
    public boolean addEnrollment(enrollment enrollment)
         {
            boolean isAddSuccessful = false;
            Connection conn = ConnectionFactory.getConnection();
            try{
                PreparedStatement ps = conn.prepareStatement("INSERT INTO enrollment(enrollment_id,enrollment_student,fk_enrollment_course,fk_enrollment_term)"+ "VALUES (?,?,?,?);");
                ps.setInt(1,enrollment.getEnrollment_id());
                ps.setInt(2,enrollment.getFk_enrollment_student());
                ps.setInt(3,enrollment.getFk_enrollment_course());
                ps.setInt(4,enrollment.getFk_enrollment_term());

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
        public boolean updateEnrollment(enrollment enrollment) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update enrollment" + "set enrollment_id = ?,fk_enrollment_course = ? , fk_enrollment_student=? ,fk_enrollment_term");
            ps.setInt(1,enrollment.getEnrollment_id());
            ps.setInt(2,enrollment.getFk_enrollment_course());
            ps.setInt(3,enrollment.getFk_enrollment_student());
            ps.setInt(4,enrollment.getFk_enrollment_term());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



    @Override
    public List<enrollment> GetAllEnrollment() {
        return null;
    }
}
