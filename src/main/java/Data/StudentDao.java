package Data;

import java.util.List;

public interface StudentDao {
 public boolean addStudent(Student student);
 public boolean updateStudent(Student student);
 public List<Student> getAllStudents();
 boolean addStudents(List<Student> students);
 List<Student> getStudentsUnassoc(int termID, int courseID);
 List<Student> sortStudents(List<Student> students);
}
