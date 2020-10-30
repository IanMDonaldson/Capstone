package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Student_outcomesDaoImpl implements Student_outcomesDao{
    @Override
    public boolean addStudent_outcomes(student_outcomes SO) {
        boolean isAddSuccessful = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student_outcomes(student_outcomes_id,student_outcomes_title)+ VALUES(?,?);");
            ps.setInt(1,SO.getStudent_outcomes_id());
            ps.setString(2,SO.getStudents_outcomes_title());
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
    public boolean updateStudent_outcomes(student_outcomes SO) {
        boolean isUpdated = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student" + "set student_outcome_title = ?" + "where student_outcome_id=?");
            ps.setString(2,SO.getStudents_outcomes_title());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<student_outcomes> GetSO() {
        Connection conn = data.ConnectionFactory.getConnection();
        student_outcomes so = new student_outcomes();
        List<Student> studentoutcomes = new LinkedList<Student>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "student");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                so.setStudent_outcomes_id(rs.getInt("student_outcomes_id"));
                so.setStudents_outcomes_title(rs.getString("student_outcome_title"));


                studentoutcomes.add(studentoutcomes);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return so;
    }
}
