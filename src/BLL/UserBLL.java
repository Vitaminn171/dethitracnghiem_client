package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.UserDTO;
import DAL.UserDAL;

public class UserBLL {

    UserDAL uDAL;

    public UserBLL() {
        uDAL = new UserDAL();
    }

    public List LoadAllUser() throws SQLException {
        ArrayList list = uDAL.readUser();
        return list;
    }

    public List LoadOnlineUser() throws SQLException {
        ArrayList list = uDAL.readOnlineUser();
        return list;
    }

    public int insertUser(String Username, String Password, String Fullname, boolean Gender, String Birth)
            throws SQLException {
        int result = uDAL.insertUser(Username, Password, Fullname, Gender, Birth);
        return result;
    }

    public int updateUser(String Username, String Fullname, boolean Gender, String Birth) throws SQLException {
        int result = uDAL.updateUser(Username, Fullname, Gender, Birth);
        return result;
    }

    public UserDTO getUserByID(int UserID) throws SQLException {
        UserDTO u = uDAL.getUserByID(UserID);
        return u;
    }

    public UserDTO getUserByUsername(String Username) throws SQLException {
        UserDTO u = uDAL.getUserByUsername(Username);
        return u;
    }

    public UserDTO getUser(String Username, String Password) throws SQLException {
        UserDTO u = uDAL.getUser(Username, Password);
        return u;
    }

    public int Logon(int UserID) throws SQLException {
        int result = uDAL.Logon(UserID);
        return result;
    }

    public int Logout(int UserID) throws SQLException {
        int result = uDAL.Logout(UserID);
        return result;
    }

    public UserDTO getBlockStatus(String Username) throws SQLException {
        UserDTO u = uDAL.getBlockStatus(Username);
        return u;
    }

    public int blockLogin(int UserID, boolean block) throws SQLException {
        int blockLoginResult = uDAL.blockLogin(UserID, block);
        int blockAddExamResult = uDAL.blockAddExam(UserID, block);
        int blockTakeExamResult = uDAL.blockTakeExam(UserID, block);
        int result = (blockLoginResult==1 && blockAddExamResult==1 && blockTakeExamResult==1) ? 1 : 0;
        return result;
    }

    public int blockAddExam(int UserID, boolean block) throws SQLException {
        int result = uDAL.blockAddExam(UserID, block);
        return result;
    }

    public int blockTakeExam(int UserID, boolean block) throws SQLException {
        int result = uDAL.blockTakeExam(UserID, block);
        return result;
    }
    
    public int getNumberOfUser(String str) throws SQLException {
        return uDAL.getNumberOfUser(str);
    }

    public int changePassword(String Username, String Password) throws SQLException {
        return uDAL.changePassword(Username, Password);
    }
}
