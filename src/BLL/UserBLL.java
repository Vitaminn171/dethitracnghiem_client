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

    public List LoadUser(int page) throws SQLException {
        int numofrecords = 30;
        ArrayList list = uDAL.readUser();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }

    /*
     * public List findUser(String str) throws SQLException {
     * List list = new ArrayList();
     * list = uDAL.findUser(str);
     * return list;
     * }
     */

    public int insertUser(String Username, String Password, String Fullname, boolean Gender, String Birth) throws SQLException {
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

    public int blockUser(int UserID, boolean block) throws SQLException {
        int result = uDAL.blockUser(UserID, block);
        return result;
    }

    //WIP
    public int getNumberOfUser(String str) throws SQLException {
        return uDAL.getNumberOfUser(str); 
    }
    
    public int changePassword(String Username, String Password) throws SQLException{
        return uDAL.changePassword(Username, Password);
    }
}
