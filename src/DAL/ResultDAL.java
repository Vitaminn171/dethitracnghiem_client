package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.ResultDTO;

public class ResultDAL extends MyDatabaseManager {

    public ResultDAL() {
        ResultDAL.connectDB();
    }

    public ArrayList readResult() throws SQLException {
        String query = "SELECT * FROM exam e, user u, result r WHERE u.UserID = r.Examinee AND r.ExamID = e.ExamID";
        ResultSet rs = ResultDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                ResultDTO r = new ResultDTO();
                r.setResultID(rs.getInt("ResultID"));
                r.setTitle(rs.getString("ExamTitle"));
                r.setFullname(rs.getString("Fullname"));
                r.setScore(rs.getFloat("Score"));
                r.setDate(Date.valueOf(rs.getString("Date")));
                r.setCorrectQuiz(rs.getInt("CorrectQuiz"));
                r.setWrongQuiz(rs.getInt("WrongQuiz"));
                list.add(r);
            }
        }
        return list;
    }
    
    // DEFAULT: Examinee là user đang login, ExamID là đề
    // Score là điểm thi, CorrectQuiz là số câu đúng, WrongWiz là số câu sai (WIP)
    public int insertResult(ResultDTO r) throws SQLException {
        String query = "INSERT INTO result () VALUES ()";
        PreparedStatement p = ResultDAL.getConnection().prepareStatement(query);
        int result = p.executeUpdate();
        return result;
    }

    // Tìm theo tên người thi, tên đề hoặc ngày thi
    public List findResult(String str) throws SQLException {
        String query = "SELECT * FROM exam e, user u, result r WHERE u.UserID = r.Examinee AND r.ExamID = e.ExamID AND (e.ExamTitle LIKE ? OR u.Fullname LIKE ? OR r.Date LIKE ?)";
        PreparedStatement p = ResultDAL.getConnection().prepareStatement(query);
        p.setString(1, "%" + str + "%");
        p.setString(2, "%" + str + "%");
        p.setString(3, "%" + str + "%");
        ResultSet rs = p.executeQuery();
        List list = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                ResultDTO r = new ResultDTO();
                r.setResultID(rs.getInt("ResultID"));
                r.setTitle(rs.getString("ExamTitle"));
                r.setFullname(rs.getString("Fullname"));
                r.setScore(rs.getFloat("Score"));
                r.setDate(Date.valueOf(rs.getString("Date")));
                r.setCorrectQuiz(rs.getInt("CorrectQuiz"));
                r.setWrongQuiz(rs.getInt("WrongQuiz"));
                list.add(r);
            }
        }
        return list;
    }
}
