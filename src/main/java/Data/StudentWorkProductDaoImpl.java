package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentWorkProductDaoImpl implements StudentWorkProductDao {
    @Override
    public boolean addSWP(StudentWorkProduct swp) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student_work_product(swp_id,swp_title,swp_grade,fk_swp_student,fk_swp_teaches)VALUES(?,?,?,?,?);");
            ps.setInt(1,swp.getSwp_id());
            ps.setString(2,swp.getSwp_name());
            ps.setDouble(3,swp.getSwp_grade());
            ps.setInt(4,swp.getFk_swp_student());
            ps.setInt(5,swp.getFk_swp_teaches());

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
    public boolean updateSWP(StudentWorkProduct swp) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student_work_product set swp_id = ?,swp_title= ?, swp_grade=?" + "where fk_swp_student=?");
            ps.setInt(1,swp.getSwp_id());
            ps.setString(2,swp.getSwp_name());
            ps.setDouble(3,swp.getSwp_grade());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StudentWorkProduct> getAllswp() {
        return null;
    }

}
