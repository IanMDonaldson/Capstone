package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeachesdaoImpl implements TeachesDao{


    @Override
    public boolean addTeaches(Teaches Teaches) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(teaches_id,fk_teaches_course,fk_teaches_instructor,fk_teaches_term)"+ "VALUES (?,?,?);");
            ps.setInt(1,Teaches.getTeachesId());
            ps.setInt(2,Teaches.getFkteachescourse());
            ps.setString(3,Teaches.getFkTeachesinstructor());
            ps.setInt(4,Teaches.getFkTeachesterm());


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
    public boolean updateTeaches(Teaches Teaches) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update teaches" + "set teaches_id = ?,fk_teaches_course = ?" + "where fk_teaches_instructor=?" + "fk_teaches_terms=?");
            ps.setInt(1,Teaches.getTeachesId());
            ps.setInt(2,Teaches.getFkteachescourse());
            ps.setString(3,Teaches.getFkTeachesinstructor());
            ps.setInt(4,Teaches.getFkTeachesterm());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }




    @Override
    public List<term> getAllterms() {
        return null;
    }
}
