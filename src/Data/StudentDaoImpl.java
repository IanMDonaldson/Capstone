package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {



    @Override
    public boolean AddStudent(Student student) {
        boolean isAddSuccessful = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(student_id,student_fname,student_lname)+ VALUES(?,?,?);");
            ps.setInt(1,student.getStudent_id());
            ps.setString(2,student.getStudent_fname());
            ps.setString(3, student.getStudent_lname());
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
    public boolean updateStudent(Student student) {
        boolean isUpdated = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
             PreparedStatement ps = conn.prepareStatement("update student" + "set student_fname = ?,student_lname = ?" + "where student_id=?");
             ps.setString(1,student.getStudent_fname());
             ps.setString(2,student.getStudent_lname());
             ps.setInt(3,student.getStudent_id());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Student> getAllStudents()
    {
        Connection conn = data.ConnectionFactory.getConnection();
        Student student = new Student();
        List<Student> students = new LinkedList<Student>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "student");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student.setStudent_id(rs.getInt("student_id"));
                student.setStudent_fname(rs.getString("student_fname"));


                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }
}

