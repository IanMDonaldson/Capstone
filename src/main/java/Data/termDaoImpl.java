package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class termDaoImpl implements termDao {

    @Override
    public boolean addTerm(term term) {
        boolean isAddSuccessful = false;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO term(term_id,term_name,term_year)" + "VALUES (?,?,?);");
            ps.setInt(1, term.getTermId());
            ps.setString(2, term.getTermName());
            ps.setInt(3, term.getTermYear());


            int rowChanged = ps.executeUpdate();
            if (rowChanged == 0) {
                return isAddSuccessful;
            } else {
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
    public boolean updateTerm(term term) {
        boolean isUpdated = false;
        Connection conn = ConnectionFactory.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("update term " + " set term_id = ?,term_name = ?" + "where term_year=?");
            ps.setInt(1, term.getTermId());
            ps.setInt(2, term.getTermYear());
            ps.setString(3, term.getTermName());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public List<String> getAllterms() {
        return null;
    }


    // @Override
   /// public List<String> getAllterms() {
      //  List<String> dataList =new ArrayList<>();
      //  String data="";
       // String dataHolder="";
//        try {
          //  String query = "select * from term";
           // Statement st = conn.createStatement();
            //ResultSet rs = st.executeQuery(query);

           // while (rs.next()) {
               // int id = rs.getInt("id");
               // String termName = rs.getString("TermName");
                //int tYear = rs.getInt("TermYear");

                // print the results
               // System.out.format("%s, %s, %s\n", id,termName,tYear);

                //dataHolder=termName+" "+tYear;

                       // dataList.add(id-1,dataHolder);
            //}
           // st.close();
       // } catch (Exception e) {
           // System.err.println("Got an exception! ");
           //System.err.println(e.getMessage());
       // }
        //return dataList;
    //}
}



