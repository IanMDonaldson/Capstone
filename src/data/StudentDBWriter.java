package data;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import data.ConnectionFactory;


public class StudentDBWriter implements StudentDaoIface {

    @Override
    public void getAllStudents() {
        Connection conn = ConnectionFactory.getConnection();
        Student student = new Student();
        List<Student> students = new LinkedList<Student>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "student");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                student.setStudentID(rs.getInt("student_id"));
                student.setStudentFname(rs.getString("student_fname"));


                students.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Grade> getGradesForStudent(Student student) {
        return null;
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public void saveStudentssss(List<Student> students) {

    }
}
