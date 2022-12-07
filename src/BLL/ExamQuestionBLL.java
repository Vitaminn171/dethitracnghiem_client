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

    public List LoadExamQuestion(int page) throws SQLException {
        int numofrecords = 30;
        ArrayList list = eqDAL.readExamQ();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
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
