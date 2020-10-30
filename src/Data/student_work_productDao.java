package Data;

import java.util.List;

public interface student_work_productDao {
    public boolean AddStudent_work_product(student_work_product swp);
    public boolean UpdateStudent_work_product(student_work_product swp);
    public List<student_work_product> getAllStudent_work_product();
}
