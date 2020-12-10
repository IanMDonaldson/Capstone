package Data;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SwpDaoImpl implements SwpDao {
    @Override
    public List<StudentOutcome> getStudentOutcomes(int swpID, int courseID, int termID) {
        List<StudentOutcome> soList = new LinkedList<>();
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("select student_outcome_id,student_outcome_title,swp.swp_grade from student_outcome " +
                    "join fulfills f on student_outcome.student_outcome_id = f.fk_fulfills_so " +
                    "join student_work_product swp on f.fk_fulfills_swp = swp.swp_id " +
                    "where swp.fk_swp_term=? AND swp.fk_swp_course=? AND swp.swp_id=?;");
            ps.setInt(1, termID);
            ps.setInt(2, courseID);
            ps.setInt(3, swpID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentOutcome so = new StudentOutcome();
                so.setSoID(rs.getInt("student_outcome_id"));
                so.setPerformance(rs.getFloat("swp_grade"));
                so.setTitle(rs.getString("student_outcome_title"));
                soList.add(so);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return soList;
    }

    @Override
    public boolean assocStudentOutcomes(List<StudentOutcome> studentOutcomes, int swpID) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);//if one of the inserts fails, this allows for rollbacks
            for (StudentOutcome so : studentOutcomes) {
                ps = conn.prepareStatement("insert into fulfills " +
                        "(fk_fulfills_swp, fk_fulfills_so) values(?,?);");
                ps.setInt(1, swpID);
                ps.setInt(2, so.getSoID());
                ps.addBatch();
            }
            assert ps != null;
            ps.executeBatch();
            ps.close();
            conn.setAutoCommit(true);
            conn.close();
            return true;
        } catch (SQLException throwables) {
            /* if execute batch throws an exception( means one of them failed to execute )
             *   we can rollback the changes*/
            try {
                conn.rollback();
                conn.setAutoCommit(true);
                assert ps != null;
                ps.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return false;
    }
}
