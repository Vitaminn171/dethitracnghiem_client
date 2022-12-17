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

    // Lấy danh sách kết quả thi
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

    // Lưu kết quả thi
    public int insertResult(int ExamID, String Examinee, float Score, String Date, int Correct, int Wrong)
            throws SQLException {
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

    // Lấy xếp hạng của người thi theo đề thi
    public int getRank(int ExamID, String Examinee, String Date) throws SQLException {
        String query = "SELECT Rank FROM (SELECT ExamID, Examinee, Date, Score, DENSE_RANK() OVER( ORDER BY Score DESC ) `Rank` FROM result WHERE ExamID = ?) `DATA`"
                + "WHERE Examinee = ? AND Date = ?";
        PreparedStatement p = ResultDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setString(2, Examinee);
        p.setString(3, Date);
        ResultSet rs = p.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    // Lấy số lần được thi của đề thi
    public int getNumOfDo(int ExamID) throws SQLException {
        String query = "SELECT COUNT(*) FROM result WHERE ExamID = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    // Lấy điểm thấp nhất trong kết quả theo đề thi
    public int Lowest(int ExamID) throws SQLException {
        String query = "SELECT MIN(Score) FROM result WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    // Lấy điểm thấp nhất trong kết quả theo đề thi
    public int Highest(int ExamID) throws SQLException {
        String query = "SELECT MAX(Score) FROM result WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public float AvgScore(int ExamID) throws SQLException {
        String query = "SELECT AVG(Score) FROM result WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            return rs.getFloat(1);
        }
        return -1;
    }
}
