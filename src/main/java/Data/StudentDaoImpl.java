package Data;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private StudentLnameComparator comparator = new StudentLnameComparator();
    private SwpDaoImpl swpDao = new SwpDaoImpl();

    @Override
    public boolean deleteStudent(int studentID) {
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("delete from student " +
                    "where student_id=?;");
            ps.setInt(1,studentID);
            int rowchange = ps.executeUpdate();
            if (rowchange == 0) {
                ps.close();
                conn.close();
                return false;
            }
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean addStudent(Student student) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(student_id,student_fname,student_lname) VALUES(?,?,?);");
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
             PreparedStatement ps = conn.prepareStatement("update student " +
                     "set student_fname = ?,student_lname = ? " +
                     "where student_id=?;");
             ps.setString(1,student.getStudentFname());
             ps.setString(2,student.getStudentLname());
             ps.setInt(3,student.getStudentId());
             int rowchanged = ps.executeUpdate();
             if (rowchanged == 0) {
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

    @Override
    public Student getStudent(int studentID) {
        Student student = new Student();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student where student_id = ?;" );
            ps.setInt(1, studentID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            student.setStudentId(rs.getInt("student_id"));
            student.setStudentFname(rs.getString("student_fname"));
            student.setStudentLname(rs.getString("student_lname"));
            conn.close();
            ps.close();
            rs.close();
            return student;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentWorkProduct> getStudentSwps(int courseID, int termID, int studentID) {
        List<StudentWorkProduct> swpList = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student_work_product " +
                    "join student s on student_work_product.fk_swp_student = s.student_id " +
                    "where fk_swp_course = ? AND fk_swp_term = ? AND student_id=?;" );
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ps.setInt(3, studentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentWorkProduct swp = new StudentWorkProduct(
                        rs.getInt("swp_id"),
                        rs.getString("swp_title"),
                        courseID, termID, rs.getString("fk_swp_instructor"),
                        studentID, rs.getFloat("swp_grade"),
                        swpDao.getStudentOutcomes(rs.getInt("swp_id"),courseID,termID)
                );
                swpList.add(swp);
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return swpList;
    }

    @Override
    public List<Student> getStudentsEnrolled2Course(int courseID, int termID) {
        List<Student> students = new LinkedList<>();
        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("select * from student " +
                    "join enrollment e on student.student_id = e.fk_enrollment_student " +
                    "where fk_enrollment_course = ? AND fk_enrollment_term = ?;" );
            ps.setInt(1, courseID);
            ps.setInt(2, termID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentFname(rs.getString("student_fname"));
                student.setStudentLname(rs.getString("student_lname"));
                student.setSwpList(getStudentSwps(courseID,termID,student.getStudentId()));
                students.add(student);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from student;" );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setStudentFname(rs.getString("student_fname"));
                student.setStudentLname(rs.getString("student_lname"));
                studentList.add(student);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sortStudents(studentList);
    }

    @Override
    public List<Student> getStudentsUnassoc(int termID, int courseID) {

        return null;
    }

    @Override
    public List<Student> sortStudents(List<Student> students) {
        students.sort(comparator.lastNameComparator);
        return students;
    }

    @Override
    public boolean addStudents(List<Student> students) {
        PreparedStatement ps = null;
        Connection conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            for (Student student : students) {
                ps = conn.prepareStatement("insert into student " +
                        "(student_fname, student_lname, isGraduated) VALUES (?,?,?);");
                ps.setString(1, student.getStudentFname() );
                ps.setString(2, student.getStudentLname());
                ps.setInt(3, 0);
                ps.addBatch();
            }
            assert ps != null;
            ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException throwables) {
            /* if execute batch throws an exception( means one of them failed to execute )
             *   we can rollback the changes*/
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }
}
