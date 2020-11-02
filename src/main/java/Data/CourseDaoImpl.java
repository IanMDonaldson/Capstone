package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean addCourse(Course course) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(course_id,course_title,department_id,fk_course_instructor)+ VALUES(?,?,?,?);");
            ps.setInt(1,course.getCourse_id());
            ps.setString(2,course.getCourse_title());
            ps.setString(3,course.getDepartment_id());
            ps.setString(4,course.getFk_course_instructor());
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
    public boolean updateCourse(Course course) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update course" + "set course_id = ?,course_title = ?" + "where department_id=?"+"where fk_course_instructor");
            ps.setInt(1,course.getCourse_id());
            ps.setString(2,course.getCourse_title());
            ps.setString(3,course.getDepartment_id());
            ps.setString(4,course.getFk_course_instructor());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }



    @Override
    public List<Course> GetAllCourses() {
        return null;
    }
}
