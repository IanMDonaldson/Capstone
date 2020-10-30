package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class fulfillsDaoImpl implements fulfillsDao {
    @Override
    public boolean addFulfills(fulfills fulfills) {
        boolean isAddSuccessful = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO fulfills(fulfills_id,fk_fulfills_swp,fk_fulfills_so)+ VALUES(?,?,?);");
            ps.setInt(1,fulfills.getFulfills_id());
            ps.setInt(2,fulfills.getFk_fulfills_swp());
            ps.setInt(3, fulfills.getFk_fulfills_so());
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
    public boolean updateFulfills(fulfills fulfills) {
        boolean isUpdated = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update fulfills" + "set fulfills_id = ?,fk_fulfills_swp = ?,fk_fulfills_so" + "where student_id=?");
            ps.setInt(1,fulfills.getFulfills_id());
            ps.setInt(2,fulfills.getFk_fulfills_swp());
            ps.setInt(3,fulfills.getFk_fulfills_so());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<fulfills> GetAllfulfills() {
        Connection conn = data.ConnectionFactory.getConnection();
        fulfills fulfills = new fulfills();
        List<fulfills> fulfill = new LinkedList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "student_work_product");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                fulfills.setFulfills_id(rs.getInt("fulfills_id"));
                fulfills.setFk_fulfills_swp(rs.getInt("fk_fulfills_swp"));
                fulfills.setFk_fulfills_so(rs.getInt("fk_fulfills_so"));
                fulfill.add(fulfills);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return fulfill;
    }
}
