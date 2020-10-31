package data;

import java.util.List;

public interface StudentDaoIface {
    public void getAllStudents();
    public List<Grade> getGradesForStudent(Student student);
    public void saveStudent(Student student);
    public void saveStudentssss(List<Student> students);
}
