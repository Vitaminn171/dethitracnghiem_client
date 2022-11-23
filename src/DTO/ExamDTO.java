package DTO;

public class ExamDTO {
    String Title, Fullname, Subjectname;
    int ExamID, UserID, SubjectID, NumOfQuiz, Time, NumOfDo;
    float Highest, Lowest, Avg;
    boolean Status;

    public ExamDTO() {
    }

    public ExamDTO(String Title, String Fullname, String Subjectname, int ExamID, int UserID, int SubjectID, int NumOfQuiz, int Time, int NumOfDo, float Highest, float Lowest, float Avg, boolean Status) {
        this.Title = Title;
        this.Fullname = Fullname;
        this.Subjectname = Subjectname;
        this.ExamID = ExamID;
        this.UserID = UserID;
        this.SubjectID = SubjectID;
        this.NumOfQuiz = NumOfQuiz;
        this.Time = Time;
        this.NumOfDo = NumOfDo;
        this.Highest = Highest;
        this.Lowest = Lowest;
        this.Avg = Avg;
        this.Status = Status;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int ExamID) {
        this.ExamID = ExamID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String Subjectname) {
        this.Subjectname = Subjectname;
    }
    
    public int getNumOfQuiz() {
        return NumOfQuiz;
    }

    public void setNumOfQuiz(int NumOfQuiz) {
        this.NumOfQuiz = NumOfQuiz;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }

    public int getNumOfDo() {
        return NumOfDo;
    }

    public void setNumOfDo(int NumOfDo) {
        this.NumOfDo = NumOfDo;
    }

    public float getHighest() {
        return Highest;
    }

    public void setHighest(float Highest) {
        this.Highest = Highest;
    }

    public float getLowest() {
        return Lowest;
    }

    public void setLowest(float Lowest) {
        this.Lowest = Lowest;
    }

    public float getAvg() {
        return Avg;
    }

    public void setAvg(float Avg) {
        this.Avg = Avg;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
    
}
