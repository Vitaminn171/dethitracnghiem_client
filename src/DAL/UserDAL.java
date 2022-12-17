package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.UserDTO;

public class UserDAL extends MyDatabaseManager {

    public UserDAL() {
        UserDAL.connectDB();
    }

    // Lấy danh sách người dùng
    public ArrayList readUser() throws SQLException {
        String query = "SELECT * FROM user";
        ResultSet rs = UserDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                UserDTO u = new UserDTO();
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullname(rs.getString("Fullname"));
                u.setDateofBirth(rs.getDate("Birth"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLogStatus(rs.getBoolean("LogStatus"));
                u.setBlockLogin(rs.getBoolean("BlockLogin"));
                u.setBlockAddExam(rs.getBoolean("BlockAddExam"));
                u.setBlockTakeExam(rs.getBoolean("BlockTakeExam"));
                list.add(u);
            }
        }
        return list;
    }

    // Lấy danh sách người dùng đang online
    public ArrayList readOnlineUser() throws SQLException {
        String query = "SELECT * FROM user WHERE LogStatus = 1";
        ResultSet rs = UserDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                UserDTO u = new UserDTO();
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullname(rs.getString("Fullname"));
                u.setDateofBirth(rs.getDate("Birth"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLogStatus(rs.getBoolean("LogStatus"));
                u.setBlockLogin(rs.getBoolean("BlockLogin"));
                u.setBlockAddExam(rs.getBoolean("BlockAddExam"));
                u.setBlockTakeExam(rs.getBoolean("BlockTakeExam"));
                list.add(u);
            }
        }
        return list;
    }

    // Lấy thông tin người dùng theo userID
    public UserDTO getUserByID(int UserID) throws SQLException {
        String query = "SELECT * FROM user WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setInt(1, UserID);
        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            UserDTO u = new UserDTO();
            u.setUserID(rs.getInt("UserID"));
            u.setUsername(rs.getString("Username"));
            u.setFullname(rs.getString("Fullname"));
            u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
            u.setGender(rs.getBoolean("Gender"));
            u.setBlockLogin(rs.getBoolean("BlockLogin"));
            u.setBlockAddExam(rs.getBoolean("BlockAddExam"));
            u.setBlockTakeExam(rs.getBoolean("BlockTakeExam"));
            return u;
        }

        return null;
    }

    // Lấy thông tin người dùng theo Username (email)
    public UserDTO getUserByUsername(String Username) throws SQLException {
        String query = "SELECT * FROM user WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        ResultSet rs = p.executeQuery();
        UserDTO u = new UserDTO();
        if (rs.next()) {
            u.setUserID(rs.getInt("UserID"));
            u.setUsername(rs.getString("Username"));
            u.setFullname(rs.getString("Fullname"));
            u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
            u.setGender(rs.getBoolean("Gender"));
            return u;
        }
        return null;
    }

    // Lấy thông tin người dùng khi đăng nhập
    public UserDTO getUser(String Username, String Password) throws SQLException {
        String query = "SELECT * FROM user WHERE Username = ? AND Password = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        p.setString(2, Password);
        ResultSet rs = p.executeQuery();
        UserDTO u = new UserDTO();

        if (rs.next()) {
            u.setUserID(rs.getInt("UserID"));
            u.setUsername(rs.getString("Username"));
            u.setFullname(rs.getString("Fullname"));
            u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
            u.setGender(rs.getBoolean("Gender"));
            u.setPassword(rs.getString("Password"));
            return u;
        }
        return null;
    }

    // Lưu thông tin đăng ký
    public int insertUser(String Username, String Password, String Fullname, boolean Gender, String Birth)
            throws SQLException {
        String query = "INSERT INTO user (Username, Password, Fullname, Gender, Birth) VALUES (?, ?, ?, ? ,?)";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        p.setString(2, Password);
        p.setString(3, Fullname);
        p.setBoolean(4, Gender);
        p.setString(5, Birth);
        int result = p.executeUpdate();
        return result;
    }

    // Thay đổi thông tin cá nhân
    public int updateUser(String Username, String Fullname, boolean Gender, String Birth) throws SQLException {
        String query = "UPDATE user SET Fullname = ? , Gender = ?, Birth = ? WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Fullname);
        p.setBoolean(2, Gender);
        p.setString(3, Birth);
        p.setString(4, Username);
        int result = p.executeUpdate();
        return result;
    }

    // Lấy trạng thái block của người dùng
    public UserDTO getBlockStatus(String Username) throws SQLException {
        String query = "SELECT UserID, BlockAddExam, BlockTakeExam FROM user WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        ResultSet rs = p.executeQuery();
        UserDTO u = new UserDTO();

        if (rs.next()) {
            u.setUserID(rs.getInt("UserID"));
            u.setBlockAddExam(rs.getBoolean("BlockAddExam"));
            u.setBlockTakeExam(rs.getBoolean("BlockTakeExam"));
            return u;
        }
        return null;
    }

    // Server chặn đăng nhập
    public int blockLogin(int UserID, boolean block) throws SQLException {
        String query = "UPDATE user SET BlockLogin = ? WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setBoolean(1, block);
        p.setInt(2, UserID);
        int result = p.executeUpdate();
        return result;
    }

    // Server chặn người dùng thêm đề thi
    public int blockAddExam(int UserID, boolean block) throws SQLException {
        String query = "UPDATE user SET BlockAddExam = ? WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setBoolean(1, block);
        p.setInt(2, UserID);
        int result = p.executeUpdate();
        return result;
    }

    // Server chặn người dùng thi 
    public int blockTakeExam(int UserID, boolean block) throws SQLException {
        String query = "UPDATE user SET BlockTakeExam = ? WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setBoolean(1, block);
        p.setInt(2, UserID);
        int result = p.executeUpdate();
        return result;
    }

    // Lấy số lượng người dùng (tất cả hoặc online)
    public int getNumberOfUser(String str) throws SQLException {
        String query;
        if (str.equals("online")) {
            query = "SELECT COUNT(*) as usercount FROM user WHERE LogStatus = 1";
        } else {
            query = "SELECT COUNT(*) as usercount FROM user";
        }
        ResultSet rs = UserDAL.doReadQuery(query);
        rs.next();
        int count = rs.getInt("usercount");
        return count;
    }

    // Thay đổi mật khẩu
    public int changePassword(String Username, String Password) throws SQLException {
        String query = "UPDATE user SET Password = ? WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Password);
        p.setString(2, Username);
        int result = p.executeUpdate();
        return result;
    }

    // Đăng nhập
    public int Logon(int UserID) throws SQLException {
        String query = "UPDATE user SET LogStatus = 1 WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setInt(1, UserID);
        int result = p.executeUpdate();
        return result;
    }

    // Đăng xuất
    public int Logout(int UserID) throws SQLException {
        String query = "UPDATE user SET LogStatus = 0 WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setInt(1, UserID);
        int result = p.executeUpdate();
        return result;
    }
}
