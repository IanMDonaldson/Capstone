package Data;

import java.sql.Connection;
import java.util.List;

public interface StudentDao {
 boolean deleteStudent(int studentID);
 public boolean addStudent(Student student);
 public boolean updateStudent(Student student);
 public List<Student> getAllStudents();
 boolean addStudents(List<Student> students);
 List<Student> getStudentsUnassoc(int termID, int courseID);
 List<Student> sortStudents(List<Student> students);
 Student getStudent(int studentID);
 List<Student> getStudentsEnrolled2Course(int courseID, int termID);
 List<StudentWorkProduct> getStudentSwps(int courseID, int termID, int studentID);
}
