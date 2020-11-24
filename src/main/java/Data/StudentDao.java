package Data;

import java.util.List;

public interface StudentDao {
 public boolean addStudent(Student student);
 public boolean updateStudent(Student student);
 public List<Student> getAllstudents();
}
