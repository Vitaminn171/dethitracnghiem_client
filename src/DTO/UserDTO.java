package DTO;

import java.util.Date;

public class UserDTO {
    int UserID, OTP;
    String Username, Password, Fullname;
    Date DateofBirth;
    boolean Gender, Logstatus, BlockLogin, BlockAddExam, BlockTakeExam;

    public UserDTO() {
    }

    public UserDTO(String Username, String Password, String Fullname, Date DateofBirth, boolean Gender,
            boolean LogStatus, boolean BlockLogin, boolean BlockAddExam, boolean BlockTakeExam) {
        this.Username = Username;
        this.Password = Password;
        this.Fullname = Fullname;
        this.DateofBirth = DateofBirth;
        this.Gender = Gender;
        this.Logstatus = LogStatus;
        this.BlockLogin = BlockLogin;
        this.BlockAddExam = BlockAddExam;
        this.BlockTakeExam = BlockTakeExam;
    }

    public int getUserID() {
        return UserID;
    }

    public int getOTP() {
        return OTP;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getFullname() {
        return Fullname;
    }

    public Date getDateofBirth() {
        return DateofBirth;
    }

    public boolean isGender() {
        return Gender;
    }

    public boolean isLogStatus() {
        return Logstatus;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public void setDateofBirth(Date DateofBirth) {
        this.DateofBirth = DateofBirth;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public void setLogStatus(boolean Logstatus) {
        this.Logstatus = Logstatus;
    }

    public boolean isBlockLogin() {
        return BlockLogin;
    }

    public void setBlockLogin(boolean Blocked) {
        this.BlockLogin = Blocked;
    }

    public boolean isBlockAddExam() {
        return BlockAddExam;
    }

    public void setBlockAddExam(boolean Blocked) {
        this.BlockAddExam = Blocked;
    }

    public boolean isBlockTakeExam() {
        return BlockTakeExam;
    }

    public void setBlockTakeExam(boolean Blocked) {
        this.BlockTakeExam = Blocked;
    }
}
