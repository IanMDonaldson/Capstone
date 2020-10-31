package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class termDaoImpl implements termDao {

    @Override
    public boolean Addterm(term term) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(term_id,term_name,term_year)"+ "VALUES (?,?,?);");
            ps.setInt(1,term.getTerm_id());
            ps.setString(2,term.getTerm_name());
            ps.setInt(3,term.getTerm_year());


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
    public boolean Updateterm(term term) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update term" + "set term_id = ?,term_name = ?" + "where term_year=?");
            ps.setInt(1,term.getTerm_id());
            ps.setInt(2,term.getTerm_year());
            ps.setString(3,term.getTerm_name());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }





    @Override
    public List<term> GetAllTerms() {
        return null;
    }
}
