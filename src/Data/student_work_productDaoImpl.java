package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class student_work_productDaoImpl implements student_work_productDao {

    @Override
    public boolean AddStudent_work_product(student_work_product swp) {
        boolean isAddSuccessful = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO student(student_work_product_id,Grade,title)+ VALUES(?,?,?);");
            ps.setInt(1,swp.getStudent_work_product_id());
            ps.setInt(2,swp.getGrade());
            ps.setString(3, swp.getTitle());
            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0)
            {
                return false;
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
    public boolean UpdateStudent_work_product(student_work_product swp) {
        boolean isUpdated = false;
        Connection conn = data.ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update student_work_product" + "set title = ?, grade = ?" + "where student_work_product_id=?");
            ps.setInt(1,swp.getStudent_work_product_id());
            ps.setString(2,swp.getTitle());
            ps.setInt(3,swp.getGrade());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<student_work_product> getAllStudent_work_product() {
        Connection conn = data.ConnectionFactory.getConnection();
        student_work_product swp = new student_work_product();
        List<student_work_product> student_work_product = new LinkedList<>();
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * from ?;");
            statement.setString(1, "student_work_product");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                swp.setStudent_work_product_id(rs.getInt("student_work_product_id"));
                swp.setTitle(rs.getString("title"));
                swp.setGrade(rs.getInt("grade"));


                student_work_product.add(swp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student_work_product;
    }
}
