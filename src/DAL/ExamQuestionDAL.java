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

    // Lấy danh sách câu hỏi trong đề thi
    public ArrayList readExamQ(int ExamID) throws SQLException {
        String query = "SELECT * FROM examquestion WHERE ExamID = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                ExamQuestionDTO eq = new ExamQuestionDTO();
                eq.setNumber(rs.getInt("Number"));
                eq.setQuestion(rs.getString("Question"));
                eq.setChoice1(rs.getString("Choice1"));
                eq.setChoice2(rs.getString("Choice2"));
                eq.setChoice3(rs.getString("Choice3"));
                eq.setChoice4(rs.getString("Choice4"));
                eq.setAnswer(rs.getString("Choice1"));// đáp án luôn là lựa chọn đầu tiên (trước khi đảo câu trả lời)
                list.add(eq);
            }
        }
        return list;
    }

    // Lấy thông tin một câu trong đề thi
    public ExamQuestionDTO readExam(int ExamID, int num) throws SQLException {
        String query = "SELECT * FROM examquestion WHERE ExamID = ? AND Number = ?" ;
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setInt(2, num);
        ResultSet rs = p.executeQuery();
        ExamQuestionDTO eq = new ExamQuestionDTO();
        
        if(rs.next()){
                eq.setExamID(rs.getInt("ExamID"));
                eq.setNumber(rs.getInt("Number"));
                eq.setQuestion(rs.getString("Question"));
                eq.setChoice1(rs.getString("Choice1"));
                eq.setChoice2(rs.getString("Choice2"));
                eq.setChoice3(rs.getString("Choice3"));
                eq.setChoice4(rs.getString("Choice4"));
                return eq;
        }
        return null;
    }
    
    // Lấy đáp án trong câu hỏi
    public ExamQuestionDTO readAnswer(int ExamID, int num) throws SQLException {
        String query = "SELECT * FROM examquestion WHERE ExamID = " + ExamID + " AND Number = " + num;
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        ResultSet rs = p.executeQuery();
        ExamQuestionDTO eq = new ExamQuestionDTO();
        
        if(rs.next()){
                eq.setExamID(rs.getInt("ExamID"));
                eq.setNumber(rs.getInt("Number"));
                eq.setAnswer(rs.getString("Choice1"));
                return eq;
        }
        return null;
    }

    // Thêm câu hỏi
    public int insertQ(int ExamID, int Number, String Question, String Choice1, String Choice2, String Choice3, String Choice4) throws SQLException {
        String query = "INSERT INTO examquestion (ExamID, Number, Question, Choice1, Choice2, Choice3, Choice4) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setInt(2, Number);
        p.setString(3, Question);
        p.setString(4, Choice1);
        p.setString(5, Choice2);
        p.setString(6, Choice3);
        p.setString(7, Choice4);
        int result = p.executeUpdate();
        return result;
    }

    // Cập nhật câu hỏi
    public int updateQ(ExamQuestionDTO eq) throws SQLException {
        String query = "UPDATE examquestion SET Question = ?, Choice1 = ? , Choice2 = ?, Choice3 = ?, Choice4 = ? WHERE ExamID = ? AND Number = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setString(1, eq.getQuestion());
        p.setString(2, eq.getChoice1());
        p.setString(3, eq.getChoice2());
        p.setString(4, eq.getChoice3());
        p.setString(5, eq.getChoice4());
        p.setInt(6, eq.getExamID());
        p.setInt(7, eq.getNumber());
        
        int result = p.executeUpdate();
        return result;
    }

    // Xóa câu hỏi
    public int deleteQ(int ExamID, int number) throws SQLException {
        String query = "DELETE FROM examquestion WHERE ExamID = ? AND Number = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setInt(2, number);
        int result = p.executeUpdate();

        return result;
    }

    // Xóa tất cả câu hỏi
    public int deleteAllQ(int ExamID) throws SQLException {
        String query = "DELETE FROM examquestion WHERE ExamID = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        int result = p.executeUpdate();
        return result;
    }

    // Lấy tổng số lượng câu trong đề thi
    public int getNumOfQuiz(int ExamID) throws SQLException {
        String query = "SELECT COUNT(*) FROM examquestion WHERE ExamID = ?";
        PreparedStatement p = ExamQuestionDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        ResultSet rs = p.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
