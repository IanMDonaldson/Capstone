package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RootUserDaoImpl implements RootUserDao {

    @Override
    public RootUser getRootUser(String username) {
        RootUser root = new RootUser();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from user " +
                    "where user_uname = ?;" );
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                root.setUsername(username);
                root.setFirstName(rs.getString("user_fname"));
                root.setLastName(rs.getString("user_lname"));
                root.setEmail(rs.getString("user_email"));
                root.setPassword(rs.getString("user_pw"));
                root.setAccessLevel(rs.getString("user_type"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return root;
    }
}
