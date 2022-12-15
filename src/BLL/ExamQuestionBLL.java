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

//    public List LoadExamQuestion(int page, int examID, int num) throws SQLException {
//        int numofrecords = 30;
//        ArrayList list = eqDAL.readExamQ(examID,limit);
//        int size = list.size();
//        int from, to;
//        from = (page - 2) * numofrecords;
//        to = page * numofrecords;
//        return list.subList(from, Math.min(to, size));
//    }
    
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

    public int insertExamQuestion(ExamQuestionDTO eq) throws SQLException {
        int result = eqDAL.insertQ(eq);
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
}
