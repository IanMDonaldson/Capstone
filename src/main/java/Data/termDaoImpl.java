package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class termDaoImpl implements termDao {



    @Override
    public static boolean addTerm(term term) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(term_id,term_name,term_year)" + "VALUES (?,?,?);");
            ps.setInt(1, term.getTermId());
            ps.setString(2, term.getTermName());
            ps.setInt(3, term.getTermYear());


            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0) {
                return isAddSuccessful;
            } else {
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
        try {
            PreparedStatement ps = conn.prepareStatement("update term" + "set term_id = ?,term_name = ?" + "where term_year=?");
            ps.setInt(1, term.getTermId());
            ps.setInt(2, term.getTermYear());
            ps.setString(3, term.getTermName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }


    @Override
    public List<term> getAllterms() {
        Connection conn = ConnectionFactory.getConnection();
        List<term> term = new LinkedList<term>();
        try {

            PreparedStatement ps = conn.prepareStatement("select * from term;" + "where term_year = ?");
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    term terms = new term();
                    terms.setTermId(rs.getInt("term_id"));
                    terms.setTermYear(rs.getInt("term_year"));
                    terms.setTermName(rs.getString("term_name"));

                }
                return term;
            }

            } catch (SQLException e) {
            e.printStackTrace();
        }
    return null;
        }

    @Override
    public static boolean termExists(term term) {
        boolean termExists = true;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from term;" +"where term_year = ?");
            ps.setInt(1, term.getTermId());
            ps.setInt(2, term.getTermYear());
            ps.setString(3, term.getTermName());
            ResultSet rs = ps.executeQuery();
            if(rs != null)
            {
                termExists = false;
                        return termExists;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return termExists;
    }
}


