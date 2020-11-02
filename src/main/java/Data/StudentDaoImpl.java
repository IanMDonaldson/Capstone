package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {



    @Override
    public boolean addStudent(Student student) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(student_id,student_fname,student_lname)+ VALUES(?,?,?);");
            ps.setInt(1,student.getStudentId());
            ps.setString(2,student.getStudentFname());
            ps.setString(3,student.getStudentLname());
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
    public boolean updateStudent(Student student) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
             PreparedStatement ps = conn.prepareStatement("update student" + "set student_fname = ?,student_lname = ?" + "where student_id=?");
             ps.setString(1,student.getStudentFname());
             ps.setString(2,student.getStudentLname());
             ps.setInt(3,student.getStudentId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAllstudents() {
        return null;
    }
}
