package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.ExamDTO;

public class ExamDAL extends MyDatabaseManager {

    public ExamDAL() {
        ExamDAL.connectDB();
    }

    public ArrayList readExam() throws SQLException {
        String query = "SELECT * FROM exam, user, subject WHERE user.UserID = exam.Creator AND subject.SubjectID = exam.SubjectID AND exam.Visible = 1";
        ResultSet rs = ExamDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                ExamDTO e = new ExamDTO();
                e.setExamID(rs.getInt("ExamID"));
                e.setTitle(rs.getString("ExamTitle"));
                e.setFullname(rs.getString("Fullname"));
                e.setSubjectname(rs.getString("SubjectName"));
                e.setNumOfQuiz(rs.getInt("NumOfQuiz"));
                e.setTime(rs.getInt("LimitTime"));
                e.setStatus(rs.getBoolean("ExamStatus"));
                e.setNumOfDo(rs.getInt("NumOfDo"));
                e.setHighest(rs.getFloat("HighestScore"));
                e.setLowest(rs.getFloat("LowestScore"));
                e.setAvg(rs.getFloat("AvgScore"));
                list.add(e);
            }
        }
        return list;
    }

    public ArrayList getExamBySubject(int SubjectID) throws SQLException {
        String query = "SELECT * FROM exam, user, subject WHERE user.UserID = exam.Creator AND subject.SubjectID = exam.SubjectID AND exam.SubjectID = ? AND exam.Visible = 1";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, SubjectID);
        ResultSet rs = p.executeQuery();
        ArrayList list = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                ExamDTO e = new ExamDTO();
                e.setExamID(rs.getInt("ExamID"));
                e.setTitle(rs.getString("ExamTitle"));
                e.setFullname(rs.getString("Fullname"));
                e.setSubjectname(rs.getString("SubjectName"));
                e.setNumOfQuiz(rs.getInt("NumOfQuiz"));
                e.setTime(rs.getInt("LimitTime"));
                e.setStatus(rs.getBoolean("ExamStatus"));
                e.setNumOfDo(rs.getInt("NumOfDo"));
                e.setHighest(rs.getFloat("HighestScore"));
                e.setLowest(rs.getFloat("LowestScore"));
                e.setAvg(rs.getFloat("AvgScore"));
                list.add(e);
            }
        }
        return list;
    }

    public ArrayList getExamByUser(String Username) throws SQLException {
        String query = "SELECT * FROM exam, user, subject WHERE user.UserID = exam.Creator AND subject.SubjectID = exam.SubjectID AND user.Username = ? AND exam.Visible = 1";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        ResultSet rs = p.executeQuery();
        ArrayList list = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                ExamDTO e = new ExamDTO();
                e.setExamID(rs.getInt("ExamID"));
                e.setTitle(rs.getString("ExamTitle"));
                e.setFullname(rs.getString("Fullname"));
                e.setSubjectname(rs.getString("SubjectName"));
                e.setNumOfQuiz(rs.getInt("NumOfQuiz"));
                e.setTime(rs.getInt("LimitTime"));
                e.setStatus(rs.getBoolean("ExamStatus"));
                e.setNumOfDo(rs.getInt("NumOfDo"));
                e.setHighest(rs.getFloat("HighestScore"));
                e.setLowest(rs.getFloat("LowestScore"));
                e.setAvg(rs.getFloat("AvgScore"));
                list.add(e);
            }
        }
        return list;
    }

    // DEFAULT: Creator là người thêm đề, ExamStatus là 0, NumOfDo là 0, các Score
    // là NULL (chưa test)
    public int insertExam(int ExamID, String ExamTitle, int Creator, int SubjectID, int NumOfQuiz, int LimitTime) throws SQLException {
        String query = "INSERT INTO exam (ExamID, ExamTitle, Creator, SubjectID, NumOfQuiz, LimitTime) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        p.setString(2, ExamTitle);
        p.setInt(3, Creator);
        p.setInt(4, SubjectID);
        p.setInt(5, NumOfQuiz);
        p.setInt(6, LimitTime);
        int result = p.executeUpdate();
        return result;
    }

    // Điều kiện để sửa: ExamStatus = 0 hoặc là NumOfDo = 0
    public int updateExam(int ExamID, String ExamTitle, int SubjectID, int NumOfQuiz, int LimitTime) throws SQLException {
        String query = "UPDATE exam SET ExamTitle = ?, SubjectID = ? , NumOfQuiz = ?, LimitTime = ? WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setString(1, ExamTitle);
        p.setInt(2, SubjectID);
        p.setInt(3, NumOfQuiz);
        p.setInt(4, LimitTime);
        p.setInt(5, ExamID);
        int result = p.executeUpdate();
        return result;
    }

    // Điều kiện để xóa: ExamStatus = 0 hoặc là NumOfDo = 0
    public int deleteExam(int ExamID) throws SQLException {
        String query = "UPDATE exam SET Visible = 0 WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        int result = p.executeUpdate();
        return result;
    }

    // Tìm theo tên tác giả hoặc tên đề
    public List findExam(String str) throws SQLException {
        String query = "SELECT * FROM exam e, user u, subject s WHERE u.UserID = e.Creator AND s.SubjectID = e.SubjectID AND (e.ExamTitle LIKE ? OR u.Fullname LIKE ?)";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setString(1, "%" + str + "%");
        p.setString(2, "%" + str + "%");
        ResultSet rs = p.executeQuery();
        List list = new ArrayList();
        if (rs != null) {
            while (rs.next()) {
                ExamDTO e = new ExamDTO();
                e.setExamID(rs.getInt("ExamID"));
                e.setTitle(rs.getString("ExamTitle"));
                e.setFullname(rs.getString("Fullname"));
                e.setSubjectname(rs.getString("SubjectName"));
                e.setNumOfQuiz(rs.getInt("NumOfQuiz"));
                e.setTime(rs.getInt("LimitTime"));
                e.setStatus(rs.getBoolean("ExamStatus"));
                e.setNumOfDo(rs.getInt("NumOfDo"));
                e.setHighest(rs.getFloat("HighestScore"));
                e.setLowest(rs.getFloat("LowestScore"));
                e.setAvg(rs.getFloat("AvgScore"));
                list.add(e);
            }
        }
        return list;
    }
    
    //Tìm theo ID
    public ExamDTO getExamByID(int ID) throws SQLException {
        String query = "SELECT * FROM exam e, user u, subject s WHERE u.UserID = e.Creator AND s.SubjectID = e.SubjectID AND ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ID);
        ResultSet rs = p.executeQuery();
        ExamDTO e = null;
        if (rs != null) {
            e = new ExamDTO();
            while (rs.next()) {
                e.setExamID(rs.getInt("ExamID"));
                e.setTitle(rs.getString("ExamTitle"));
                e.setSubjectname(rs.getString("SubjectName"));
                e.setFullname(rs.getString("FullName"));
                e.setNumOfQuiz(rs.getInt("NumOfQuiz"));
                e.setTime(rs.getInt("LimitTime"));
                e.setHighest(rs.getFloat("HighestScore"));
                e.setLowest(rs.getFloat("LowestScore"));
                e.setAvg(rs.getFloat("AvgScore"));
            }
        }
        return e;
    }

    // Thống kê tổng số đề
    public int getNumberOfExam() throws SQLException {
        String query = "SELECT COUNT(*) FROM exam";
        ResultSet rs = UserDAL.doReadQuery(query);
        rs.next();
        return rs.getInt(1);
    }

    // Tăng NumOfDo (Tổng số lần thi theo đề) (Chưa test)
    public int Increase(int ExamID) throws SQLException {
        String query = "UPDATE exam SET NumOfDo = NumOfDo + 1 WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setInt(1, ExamID);
        int result = p.executeUpdate();
        return result;
    }

    // Tính AvgScore (Điểm trung bình tổng lần thi theo đề) (Chưa test)
    public int CalAvg(int ExamID) throws SQLException {
        String query1 = "SELECT AVG(Score) FROM result WHERE ExamID = ?";
        PreparedStatement p1 = ExamDAL.getConnection().prepareStatement(query1);
        p1.setInt(1, ExamID);
        ResultSet rs = p1.executeQuery();
        rs.next();

        String query2 = "UPDATE exam SET AvgScore = ? WHERE ExamID = ?";
        PreparedStatement p2 = ExamDAL.getConnection().prepareStatement(query2);
        p2.setFloat(1, rs.getFloat(1));
        p2.setInt(2, ExamID);
        int result = p2.executeUpdate();
        return result;
    }

    // Cập nhật HighestScore (Chưa test)
    public int Highest(int ExamID, float Score) throws SQLException {
        String query1 = "SELECT HighestScore FROM exam WHERE ExamID = ?";
        PreparedStatement p1 = ExamDAL.getConnection().prepareStatement(query1);
        p1.setInt(1, ExamID);
        ResultSet rs = p1.executeQuery();
        rs.next();
        if (Score > rs.getFloat(1)) {
            String query2 = "UPDATE exam SET HighestScore = ? WHERE ExamID = ?";
            PreparedStatement p2 = ExamDAL.getConnection().prepareStatement(query2);
            p2.setFloat(1, Score);
            p2.setInt(2, ExamID);
            p2.executeUpdate();
            return 1;
        }
        return 0;
    }

    // Cập nhật LowestScore (Chưa test)
    public int Lowest(int ExamID, float Score) throws SQLException {
        String query1 = "SELECT LowestScore FROM exam WHERE ExamID = ?";
        PreparedStatement p1 = ExamDAL.getConnection().prepareStatement(query1);
        p1.setInt(1, ExamID);
        ResultSet rs = p1.executeQuery();
        rs.next();
        if (Score < rs.getFloat(1)) {
            String query2 = "UPDATE exam SET LowestScore = ? WHERE ExamID = ?";
            PreparedStatement p2 = ExamDAL.getConnection().prepareStatement(query2);
            p2.setFloat(1, Score);
            p2.setInt(2, ExamID);
            p2.executeUpdate();
            return 1;
        }
        return 0;
    }
}
