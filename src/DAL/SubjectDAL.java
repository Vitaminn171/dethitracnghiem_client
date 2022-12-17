package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import DTO.SubjectDTO;

public class SubjectDAL extends MyDatabaseManager {

    public SubjectDAL() {
        SubjectDAL.connectDB();
    }

    // Lấy danh sách môn
    public ArrayList readSubject() throws SQLException {
        String query = "SELECT * FROM subject";
        ResultSet rs = UserDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                SubjectDTO s = new SubjectDTO();
                s.setSubjectID(rs.getInt("SubjectID"));
                s.setSubjectname(rs.getString("SubjectName"));
                list.add(s);
            }
        }
        return list;
    }
    
    // Lấy thông tin môn theo mã môn
    public SubjectDTO getSubjectByID(int SubID) throws SQLException {
        String query = "SELECT * FROM subject WHERE SubjectID = ?";
        PreparedStatement p = SubjectDAL.getConnection().prepareStatement(query);
        p.setInt(1, SubID);
        ResultSet rs = p.executeQuery();
        SubjectDTO s = null;
        
        if (rs != null) {
            s= new SubjectDTO();
            while (rs.next()) {
                s.setSubjectID(rs.getInt("SubjectID"));
                s.setSubjectname(rs.getString("SubjectName"));
            }
        }
        return s;
    }
}
