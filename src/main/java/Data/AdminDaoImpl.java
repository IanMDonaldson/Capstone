package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean adminExist(Admin admin) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from admin where admin.admin_uname = ? AND admin.admin_pw = ?;");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
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
