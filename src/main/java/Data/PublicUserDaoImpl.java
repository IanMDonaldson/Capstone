package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublicUserDaoImpl implements PublicUserDao {

    @Override
    public PublicUser getPublicUser(String username) {
        PublicUser pubUser = new PublicUser();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from public_user " +
                    "where public_user_uname = ?;");
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pubUser.setUsername(username);
                pubUser.setPassword(rs.getString("public_user_pw"));
                pubUser.setEmail(rs.getString("public_user_email"));
                pubUser.setFirstName(rs.getString("public_user_fname"));
                pubUser.setLastName(rs.getString("public_user_lname"));
                pubUser.setAccessLevel(rs.getString("public_user_type"));
            }
            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pubUser;
    }

    @Override
    public boolean swapToUserTable(PublicUser pubUser) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into user " +
                    "(user_uname, user_pw, user_email, user_fname, user_lname, user_type) " +
                    "VALUES (?,?,?,?,?,?)");
            ps.setString(1,pubUser.getUsername());
            ps.setString(2,pubUser.getPassword());
            ps.setString(3,pubUser.getEmail());
            ps.setString(4,pubUser.getFirstName());
            ps.setString(5,pubUser.getLastName());
            ps.setString(6,pubUser.getAccessLevel());
            if (ps.execute()) {
                ps.close();
                conn.close();
                return true;
            }
            ps.close();
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePublicUser(String username) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from public_user " +
                    "where public_user_uname = ?;");
            ps.setString(1, username);
            int rowchanged = ps.executeUpdate();
            if (rowchanged > 0 ) {
                ps.close();
                conn.close();
                return true;
            }
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PublicUser> getAllPublicUsers() {
        List<PublicUser> publicUserList = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select public_user_uname,public_user_fname,public_user_lname, " +
                    "public_user_email,public_user_type " +
                    "from public_user;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PublicUser pub = new PublicUser();
                pub.setUsername(rs.getString("public_user_uname"));
                pub.setFirstName(rs.getString("public_user_fname"));
                pub.setLastName(rs.getString("public_user_lname"));
                pub.setEmail(rs.getString("public_user_email"));
                pub.setAccessLevel(rs.getString("public_user_type"));
                publicUserList.add(pub);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return publicUserList;
    }

    @Override
    public boolean addPublicUser(PublicUser pubUser) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into " +
                    "public_user (public_user_email, public_user_fname, public_user_lname, " +
                    "public_user_pw, public_user_uname,public_user_type) VALUES(?,?,?,?,?,?);");
            ps.setString(1,pubUser.getEmail());
            ps.setString(2,pubUser.getFirstName());
            ps.setString(3,pubUser.getLastName());
            ps.setString(4,pubUser.getPassword());
            ps.setString(5,pubUser.getUsername());
            ps.setString(6,pubUser.getAccessLevel());
            int rowchanged = ps.executeUpdate();
            if (rowchanged == 0) {
                conn.close();
                ps.close();
                return false;
            }
            conn.close();
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
