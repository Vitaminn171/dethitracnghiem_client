package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.ExamQuestionDTO;
import DAL.ExamQuestionDAL;

public class ExamQuestionBLL {
    ExamQuestionDAL eqDAL;

    public ExamQuestionBLL() {
        eqDAL = new ExamQuestionDAL();
    }
    
    //update 16/12 by Quoc An
    public List getExamQuestion(int examID) throws SQLException{
        ArrayList list = eqDAL.readExamQ(examID);
        return list;
    }
    //update 16/12 by Quoc An
    
    public ExamQuestionDTO getExamQuestion(int examID, int num) throws SQLException{
        ExamQuestionDTO eq = eqDAL.readExam(examID,num);
        return eq;
    }
    public ExamQuestionDTO getExamAnswer(int examID, int num) throws SQLException{
        ExamQuestionDTO eq = eqDAL.readAnswer(examID,num);
        return eq;
    }

    public int insertQ(int ExamID, int Number, String Question, String Choice1, String Choice2, String Choice3, String Choice4) throws SQLException {
        int result = eqDAL.insertQ(ExamID, Number, Question, Choice1, Choice2, Choice3, Choice4);
        return result;
    }

    public int updateExamQuestion(ExamQuestionDTO eq) throws SQLException {
        int result = eqDAL.updateQ(eq);
        return result;
    }
    
    /* public ExamQuestionDTO getExam(int ExamID) throws SQLException {
        ExamQuestionDTO eq = eqDAL.getExam(ExamID);
        return eq;
    } */

    public int deleteExamQuestion(int ExamID, int number) throws SQLException {
        int result = eqDAL.deleteQ(ExamID, number);
        return result;
    }

    public int getNumOfQuiz(int ExamID) throws SQLException {
        return eqDAL.getNumOfQuiz(ExamID);
    }
}
