package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class TermDaoImpl implements TermDao {



    @Override
    public boolean termExists(Term term) {
        boolean termExists = true;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from dcia.term as i where i.term_id=? AND i.term_year = ? AND i.term_name = ?;");
            ps.setInt(1, term.getTermId());
            ps.setInt(2, term.getTermYear());
            ps.setString(3, term.getTermName());
            ResultSet rs = ps.executeQuery();
            if(rs != null)
            {
                termExists = false;
            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return termExists;
    }

    @Override
    public boolean addTerm(Term term) {
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
    public boolean updateTerm(Term term) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("update term " + "set term_id=?, term_year=?" + "where term_name=?;");
            ps.setInt(1, term.getTermId());
            ps.setInt(2, term.getTermYear());
            ps.setString(3, term.getTermName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public Term getTerm(int Id) {
        Term term = new Term();
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from term where term.term_id = ?;");
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                term.setTermId(rs.getInt("termID"));
                term.setTermName(rs.getString("last_name"));
                term.setTermId(rs.getInt("actor_id"));

            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return term;
    }

    @Override
    public int getNewTermID() {
        return 0;
    }

    @Override
    public boolean assocCourse(Term term, String newlyAssocCourseID) {
        List<Course> newCourse = new LinkedList<Course>();
        StringBuilder statement = new StringBuilder("INSERT INTO()")
    }


}
