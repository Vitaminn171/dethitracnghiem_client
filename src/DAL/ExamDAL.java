package DAL;

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
                e.setTime(rs.getInt("LimitTime"));
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
                e.setTime(rs.getInt("LimitTime"));
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
                e.setTime(rs.getInt("LimitTime"));
                list.add(e);
            }
        }
        return list;
    }

    public int insertExam(String ExamTitle, int Creator, int SubjectID, int NumOfQuiz, int LimitTime) throws SQLException {
        String query = "INSERT INTO exam (ExamTitle, Creator, SubjectID, NumOfQuiz, LimitTime) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        p.setString(1, ExamTitle);
        p.setInt(2, Creator);
        p.setInt(3, SubjectID);
        p.setInt(4, NumOfQuiz);
        p.setInt(5, LimitTime);
        p.executeUpdate();
        ResultSet rs = p.getGeneratedKeys();
        if (rs.next())
            return rs.getInt(1);
        return 0;
    }

    public int updateExam(int ExamID, String ExamTitle, int SubjectID, int LimitTime)
            throws SQLException {
        String query = "UPDATE exam SET ExamTitle = ?, SubjectID = ?, LimitTime = ? WHERE ExamID = ?";
        PreparedStatement p = ExamDAL.getConnection().prepareStatement(query);
        p.setString(1, ExamTitle);
        p.setInt(2, SubjectID);
        p.setInt(3, LimitTime);
        p.setInt(4, ExamID);
        int result = p.executeUpdate();
        return result;
    }

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
                e.setTime(rs.getInt("LimitTime"));
                // update by Quoc An newest
                // delete examstatus
                list.add(e);
            }
        }
        return list;
    }

    // Tìm theo ID
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
                e.setTime(rs.getInt("LimitTime"));
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
}
