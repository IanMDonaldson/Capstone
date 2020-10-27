package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class works_onDaoImpl implements works_onDao {
    @Override
    public boolean AddWorks_on(works_on works) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO works_on(works_on_id,fk_works_on_student,fk_works_on_swp,fk_works_on_term)"+ "VALUES (?,?,?,?);");
            ps.setInt(1,works.getWorks_on_id());
            ps.setInt(2,works.getFk_works_on_student());
            ps.setInt(3,works.getFk_works_on_swp());
            ps.setInt(4,works.getFk_works_on_term());

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
    public boolean updateWorks_on(works_on works) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student" + "set student_fname = ?,student_lname = ?" + "where student_id=?");
            ps.setInt(1,works.getWorks_on_id());
            ps.setInt(2,works.getFk_works_on_term());
            ps.setInt(3,works.getFk_works_on_swp());
            ps.setInt(4,works.getFk_works_on_student());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public List<works_on> getAllWorks() {
        return null;
    }
}
