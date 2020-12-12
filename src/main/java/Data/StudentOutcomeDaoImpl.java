package Data;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentOutcomeDaoImpl implements StudentOutcomeDao {
    @Override
    public boolean addSO(StudentOutcome so) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student_outcome(student_outcome_id, student_outcome_title) VALUES (?,?);");
            ps.setInt(1,so.getSoID());
            ps.setString(2,so.getTitle());
            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0)
            {
                return false;
            }
            else
            {
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
    public boolean updateSO(StudentOutcome so) {
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student_outcome " +
                    "set student_outcome_title=? where student_outcome_id=?;");
            ps.setString(1, so.getTitle());
            ps.setInt(2, so.getSoID());
            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0)
            {
                return false;
            }
            else
            {
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
    public StudentOutcome getSO(int soID) {
        StudentOutcome so = new StudentOutcome();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student_outcome " +
                    "where student_outcome_id = ?;");
            ps.setInt(1,soID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            so.setTitle(rs.getString("student_outcome_title"));
            so.setSoID(soID);
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return so;
    }

    @Override
    public List<StudentOutcome> getAllSO() {
        List<StudentOutcome> soList = new LinkedList<StudentOutcome>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from student_outcome;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentOutcome so = new StudentOutcome();
                so.setSoID(rs.getInt("student_outcome_id"));
                so.setTitle(rs.getString("student_outcome_title"));
                soList.add(so);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soList;
    }

    @Override
    public List<StudentOutcome> getAllSOforCourse(Course course) {
        List<StudentOutcome> soList = new LinkedList<StudentOutcome>();
        return null;
    }

    @Override
    public StudentOutcome getSOforSWP(StudentWorkProduct swp) {
        return null;
    }
}
