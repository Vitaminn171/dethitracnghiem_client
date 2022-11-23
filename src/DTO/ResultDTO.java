package DTO;

import java.util.Date;

public class ResultDTO {
    int ResultID, UserID, CorrectQuiz, WrongQuiz;
    float Score;
    String ExamID, Title, Fullname;
    Date date;

    public ResultDTO() {
    }

    public ResultDTO(int ResultID, int UserID, int CorrectQuiz, int WrongQuiz, float Score, String ExamID, String Title, String Fullname, Date date) {
        this.ResultID = ResultID;
        this.UserID = UserID;
        this.CorrectQuiz = CorrectQuiz;
        this.WrongQuiz = WrongQuiz;
        this.Score = Score;
        this.ExamID = ExamID;
        this.Title = Title;
        this.Fullname = Fullname;
        this.date = date;
    }

    public int getResultID() {
        return ResultID;
    }

    public void setResultID(int ResultID) {
        this.ResultID = ResultID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getCorrectQuiz() {
        return CorrectQuiz;
    }

    public void setCorrectQuiz(int CorrectQuiz) {
        this.CorrectQuiz = CorrectQuiz;
    }

    public int getWrongQuiz() {
        return WrongQuiz;
    }

    public void setWrongQuiz(int WrongQuiz) {
        this.WrongQuiz = WrongQuiz;
    }
    
    public float getScore() {
        return Score;
    }

    public void setScore(float Score) {
        this.Score = Score;
    }

    public String getExamID() {
        return ExamID;
    }

    public void setExamID(String ExamID) {
        this.ExamID = ExamID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}