package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import DTO.UserDTO;

public class UserDAL extends MyDatabaseManager {

    public UserDAL() {
        UserDAL.connectDB();
    }

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
                //u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
                u.setDateofBirth(rs.getDate("Birth"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLogStatus(rs.getBoolean("LogStatus"));
                u.setBlocked(rs.getBoolean("BlockStatus"));
                list.add(u);
            }
        }
        return list;
    }
    
    public ArrayList readOnlineUser() throws SQLException {
        String query = "SELECT * FROM user WHERE LogStatus=1";
        ResultSet rs = UserDAL.doReadQuery(query);
        ArrayList list = new ArrayList();

        if (rs != null) {
            while (rs.next()) {
                UserDTO u = new UserDTO();
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullname(rs.getString("Fullname"));
                //u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
                u.setDateofBirth(rs.getDate("Birth"));
                u.setGender(rs.getBoolean("Gender"));
                u.setLogStatus(rs.getBoolean("LogStatus"));
                u.setBlocked(rs.getBoolean("BlockLogin"));
                list.add(u);
            }
        }
        return list;
    }

    public UserDTO getUserByUsername(String Username) throws SQLException {
        String query = "SELECT * FROM user WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Username);
        ResultSet rs = p.executeQuery();
        UserDTO u = null;
        if (rs != null) {
            u = new UserDTO();
            while (rs.next()) {
                u.setUserID(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setFullname(rs.getString("Fullname"));
                u.setDateofBirth(Date.valueOf(rs.getString("Birth")));
                u.setGender(rs.getBoolean("Gender"));
                u.setBlocked(rs.getBoolean("BlockLogin"));
            }
        }
        return u;
    }

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
            u.setBlocked(rs.getBoolean("BlockLogin"));
            return u;
        }
        return null;
    }

    public int insertUser(String Username, String Password, String Fullname, boolean Gender, String Birth) throws SQLException {
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

    public int blockLogin(int UserID, boolean block) throws SQLException {
        String query = "UPDATE user SET BlockLogin = ? WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setBoolean(1, block);
        p.setInt(2, UserID);
        int result = p.executeUpdate();
        return result;
    }

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
    
    public int changePassword(String Username, String Password) throws SQLException {
        String query = "UPDATE user SET Password = ? WHERE Username = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setString(1, Password);
        p.setString(2, Username);
        int result  = p.executeUpdate();
        return result;
    }

    public int Logon(int UserID) throws SQLException {
        String query = "UPDATE user SET LogStatus = 1 WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setInt(1, UserID);
        int result = p.executeUpdate();
        return result;
    }

    public int Logout(int UserID) throws SQLException {
        String query = "UPDATE user SET LogStatus = 0 WHERE UserID = ?";
        PreparedStatement p = UserDAL.getConnection().prepareStatement(query);
        p.setInt(1, UserID);
        int result = p.executeUpdate();
        return result;
    }
}
