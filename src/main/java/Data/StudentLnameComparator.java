package Data;

import java.util.Comparator;

public class StudentLnameComparator implements Comparable<Student> {


    public Comparator<Student> lastNameComparator = new Comparator<Student>() {
        public int compare(Student s1, Student s2) {
            return s1.getStudentLname().compareTo(s2.getStudentLname());
        }
    };
    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
