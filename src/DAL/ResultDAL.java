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
    
    public ArrayList readResult_1() throws SQLException {
        String query = "SELECT * FROM result";
        ResultSet rs = ResultDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                ResultDTO r = new ResultDTO();
                r.setResultID(rs.getInt("ResultID"));
                r.setExamID(rs.getInt("ExamID"));
                r.setFullname(rs.getString("Examinee"));
                r.setScore(rs.getFloat("Score"));
                r.setCorrectQuiz(rs.getInt("CorrectQuiz"));
                r.setWrongQuiz(rs.getInt("WrongQuiz"));
                r.setDate(Date.valueOf(rs.getString("Date")));
                list.add(r);
            }
        }
        return list;
    }
    
    // DEFAULT: Examinee là user đang login, ExamID là đề vừa thi, Score là điểm thi, CorrectQuiz là số câu đúng, WrongQuiz là số câu sai (WIP)
    public int insertResult(int ExamID, String Examinee, float Score, String Date, int Correct, int Wrong) throws SQLException {
        String query = "INSERT INTO result (ExamID, Examinee, Score, Date, CorrectQuiz, WrongQuiz) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement p = ResultDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setString(2, Examinee);
        p.setFloat(3, Score);
        p.setString(4, Date);
        p.setInt(5, Correct);
        p.setInt(6, Wrong);
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
