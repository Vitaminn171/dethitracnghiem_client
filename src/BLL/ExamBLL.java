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

    public List LoadExam(int page) throws SQLException {
        int numofrecords = 30;
        ArrayList list = eDAL.readExam();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }

    public List findExam(String str) throws SQLException {
        List list = new ArrayList();
        list = eDAL.findExam(str);
        return list;
    }

    public int insertExam(ExamDTO e) throws SQLException {
        int result = eDAL.insertExam(e);
        return result;
    }

    public int updateExam(ExamDTO e) throws SQLException {
        int result = eDAL.updateExam(e);
        return result;
    }
    
    /* public ExamDTO getExam(int ExamID) throws SQLException {
        ExamDTO e = eDAL.getExam(ExamID);
        return e;
    } */

    public int deleteExam(int ExamID) throws SQLException {
        int result = eDAL.deleteExam(ExamID);
        return result;
    }

    public int getNumberOfExam() throws SQLException{
        return eDAL.getNumberOfExam();
    }
}
