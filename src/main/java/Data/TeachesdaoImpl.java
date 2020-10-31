package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeachesdaoImpl implements TeachesDao{


    @Override
    public boolean AddTeaches(Teaches Teaches) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(teaches_id,fk_teaches_course,fk_teaches_instructor,fk_teaches_term)"+ "VALUES (?,?,?);");
            ps.setInt(1,Teaches.getTeaches_id());
            ps.setInt(2,Teaches.getFk_teaches_course());
            ps.setString(3,Teaches.getFk_teaches_instructor());


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
    public boolean UpdateTeaches(Teaches Teaches) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = conn.prepareStatement("update teaches" + "set teaches_id = ?,fk_teaches_course = ?" + "where fk_teaches_instructor=?" + "fk_teaches_terms=?");
            ps.setInt(1,Teaches.getTeaches_id());
            ps.setInt(2,Teaches.getFk_teaches_course());
            ps.setString(3,Teaches.getFk_teaches_instructor());
            ps.setInt(4,Teaches.getFk_teaches_term());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }




    @Override
    public List<term> GetAllTerms() {
        return null;
    }
}
