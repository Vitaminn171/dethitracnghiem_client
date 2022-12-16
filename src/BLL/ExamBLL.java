package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.ExamDTO;
import DAL.ExamDAL;

public class ExamBLL {

    ExamDAL eDAL;

    public ExamBLL() {
        eDAL = new ExamDAL();
    }

    public List getExamByUser(String Username) throws SQLException {
        List list = eDAL.getExamByUser(Username);
        return list;
    }
    

    public List getExamBySubject(int SubjectID) throws SQLException {
        List list = eDAL.getExamBySubject(SubjectID);
        return list;
    }

    public List readExam() throws SQLException {
        List list = eDAL.readExam();
        return list;
    }

    public List LoadAllExam() throws SQLException {
        ArrayList list = eDAL.readExam();
        return list;
    }

    public List findExam(String str) throws SQLException {
        List list = new ArrayList();
        list = eDAL.findExam(str);
        return list;
    }

    public int insertExam(int ExamID, String ExamTitle, int Creator, int SubjectID, int NumOfQuiz, int LimitTime) throws SQLException {
        int result = eDAL.insertExam(ExamID, ExamTitle, Creator, SubjectID, NumOfQuiz, LimitTime);
        return result;
    }

    public int updateExam(int ExamID, String ExamTitle, int SubjectID, int NumOfQuiz, int LimitTime)
            throws SQLException {
        int result = eDAL.updateExam(ExamID, ExamTitle, SubjectID, NumOfQuiz, LimitTime);
        return result;
    }

    public ExamDTO getExamByID(int ExamID) throws SQLException {
        ExamDTO e = eDAL.getExamByID(ExamID);
        return e;
    }

    public int deleteExam(int ExamID) throws SQLException {
        int result = eDAL.deleteExam(ExamID);
        return result;
    }

    public int getNumberOfExam() throws SQLException {
        return eDAL.getNumberOfExam();
    }

    public void calAvg(int ExamID) throws SQLException {
        eDAL.CalAvg(ExamID);
    }

    public void calHighest(int ExamID, float score) throws SQLException {
        eDAL.Highest(ExamID, score);
    }

    public void calLowest(int ExamID, float score) throws SQLException {
        eDAL.Lowest(ExamID, score);
    }
    
    //update by Quoc An newest
    public void increase(int ExamID) throws SQLException {
        eDAL.Increase(ExamID);
    }
    //update by Quoc An newest
}
