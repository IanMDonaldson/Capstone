package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class termDaoImpl implements termDao {

    @Override
    public boolean addTerm(term term) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(term_id,term_name,term_year)"+ "VALUES (?,?,?);");
            ps.setInt(1,term.getTermId());
            ps.setString(2,term.getTermName());
            ps.setInt(3,term.getTermYear());


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
    public boolean updateTerm(term term) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update term" + "set term_id = ?,term_name = ?" + "where term_year=?");
            ps.setInt(1,term.getTermId());
            ps.setInt(2,term.getTermYear());
            ps.setString(3,term.getTermName() );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }





    @Override
    public List<term> getAllterms() {
        return null;
    }
}
