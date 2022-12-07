package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.ExamQuestionDTO;

public class ExamQuestionDAL extends MyDatabaseManager {

    public ExamQuestionDAL() {
        ExamQuestionDAL.connectDB();
    }

    public ArrayList readExamQ() throws SQLException {
        String query = "SELECT * FROM examquestion";
        ResultSet rs = ExamQuestionDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                ExamQuestionDTO eq = new ExamQuestionDTO();
                eq.setExamID(rs.getInt("ExamID"));
                eq.setNumber(rs.getInt("Number"));
                eq.setQuestion(rs.getString("Question"));
                eq.setChoice1(rs.getString("Choice1"));
                eq.setChoice2(rs.getString("Choice2"));
                eq.setChoice3(rs.getString("Choice3"));
                eq.setChoice4(rs.getString("Choice4"));
                eq.setAnswer(rs.getString("Answer"));
                list.add(eq);
            }
        }
        return list;
    }

    /*
     * public ExamChoiceDTO getUser(int UserID) throws SQLException {
     * String query = "SELECT * FROM exam WHERE UserID = ?";
     * PreparedStatement p = ExamChoiceDAL.getConnection().prepareStatement(query);
     * p.setInt(1, UserID);
     * ResultSet rs = p.executeQuery();
     * ExamChoiceDTO eq = new ExamChoiceDTO();
     * if (rs != null) {
     * while (rs.next()) {
     * eq.setUserID(rs.getInt("UserID"));
     * eq.setUsername(rs.getString("Username"));
     * eq.setFullname(rs.getString("Fullname"));
     * eq.setDateofBirth(Date.valueOf(rs.getString("Birth")));
     * eq.setGender(rs.getBoolean("Gender"));
     * eq.setLogStatus(rs.getBoolean("LogStatus"));
     * eq.setBlocked(rs.getBoolean("BlockStatus"));
     * }
     * }
     * return eq;
     * }
     */

    //Tạo câu hỏi
    public int insertQ(ExamQuestionDTO eq) throws SQLException {
        String query = "INSERT INTO exam (Number, Question, Choice1, Choice2, Choice3, Choice4, Answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, eq.getNumber());
        p.setString(2, eq.getQuestion());
        p.setString(3, eq.getChoice1());
        p.setString(4, eq.getChoice2());
        p.setString(5, eq.getChoice3());
        p.setString(6, eq.getChoice4());
        p.setString(7, eq.getAnswer());
        int result = p.executeUpdate();
        return result;
    }

    //Cập nhật câu hỏi
    public int updateQ(ExamQuestionDTO eq) throws SQLException {
        String query = "UPDATE examquestion SET Question = ?, Choice1 = ? , Choice2 = ?, Choice3 = ?, Choice4 = ?, Answer = ? WHERE ExamID = ? AND Number = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setString(1, eq.getQuestion());
        p.setString(2, eq.getChoice1());
        p.setString(3, eq.getChoice2());
        p.setString(4, eq.getChoice3());
        p.setString(5, eq.getChoice4());
        p.setString(6, eq.getAnswer());
        p.setInt(7, eq.getExamID());
        p.setInt(8, eq.getNumber());
        
        int result = p.executeUpdate();
        return result;
    }

    //Xóa câu hỏi
    public int deleteQ(int ExamID, int number) throws SQLException {
        String query = "DELETE FROM examquestion WHERE ExamID = ? AND Number = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setInt(2, number);
        int result = p.executeUpdate();

        return result;
    }

    // So sánh kết quả (WIP)
    public int Compare(String choice) throws SQLException {
        String query = "";
        // Nếu choice (chuỗi text lựa chọn) trùng với examquestion.Answer (đáp án) trả về 0
            return 0;
    }
}
