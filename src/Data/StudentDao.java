package Data;

import java.util.List;

public interface StudentDao {
 public boolean AddStudent(Student student);
 public boolean deleteStudent(Student student);
 public boolean updateStudent(Student student);
 public List<Student> getAllStudents();
}
