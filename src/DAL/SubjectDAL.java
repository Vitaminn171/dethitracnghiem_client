package DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.SubjectDTO;

public class SubjectDAL extends MyDatabaseManager {

    public SubjectDAL() {
        SubjectDAL.connectDB();
    }

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
}
