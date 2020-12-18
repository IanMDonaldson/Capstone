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
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from term where " +
                    "term_name = ? AND term_year = ?;" );
            ps.setString(1, term.getTermName());
            ps.setInt(2, term.getTermYear());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                rs.close();
                ps.close();
                conn.close();
                return false;
                //if resultset returns nothing then term doesn't exist yet
            } else {
                rs.close();
                ps.close();
                conn.close();
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public Term getLastTerm() {
        Term term = new Term();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from term;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.isLast()) {
                    term.setTermName(rs.getString("term_name"));
                    term.setTermYear(rs.getInt("term_year"));
                    term.setTermId(rs.getInt("term_id"));
                }
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return term;
    }

    @Override
    public boolean addTerm(Term term) {
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
                ps.close();
                conn.close();
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
    public boolean updateTerm(Term term) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update term set term_id = ?,term_name = ? where term_year=?");
            ps.setInt(1,term.getTermId());
            ps.setInt(2,term.getTermYear());
            ps.setString(3,term.getTermName() );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public Term getTerm(int Id) {
        return null;
    }

    @Override
    public List<Term> getAllTerms() {
        List<Term> termList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from term;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Term term = new Term();
                term.setTermId(rs.getInt("term_id"));
                term.setTermName(rs.getString("term_name"));
                term.setTermYear(rs.getInt("term_year"));
                termList.add(term);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return termList;
    }
    private int termCount(){
        int count = -1;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select count(*) as count from term;" );
            ResultSet rs = ps.executeQuery();
            rs.next();
            count = rs.getInt("count");
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count;
    }
    public Term[] termList2Arr(List<Term> termList) {
        Term[] terms = new Term[termCount()];
        for (int i = 0; i<termList.size();i++) {
            terms[i] = termList.get(i);
        }
        return terms;
    }

    /*inside 3.3.2 Setup - Department's Courses- Admin will enter the information
     *for the set of courses offered by the department
     * This can mean either, THIS FUNCTION - course is already created and we need to associate it to a term
     * OR CourseDaoImpl.addCourse(Course course) we need to create a new course
     * this inserts into teaches table leaving instructor NULL because the adminSetup associates an instructor to course later*/
    @Override
    public boolean assocCourse(int termID, int courseID) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into teaches(fk_teaches_course, fk_teaches_term) VALUES (?,?);");
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            int rowChange = ps.executeUpdate();
            if (rowChange == 0) {
                ps.close();
                conn.close();
                return false;
            } else {
                ps.close();
                conn.close();
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}
