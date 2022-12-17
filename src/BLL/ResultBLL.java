package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAL.ResultDAL;

public class ResultBLL {
    ResultDAL rDAL;

    public ResultBLL() {
        rDAL = new ResultDAL();
    }
    
    public List getResult() throws SQLException {
        List list = new ArrayList();
        list = rDAL.readResult_1();
        return list;
    }

    public int insertResult(int ExamID, String Examinee, float Score, String Date, int Correct, int Wrong) throws SQLException {
        int result = rDAL.insertResult(ExamID, Examinee, Score, Date, Correct, Wrong);
        return result;
    }

    public int getRank(int ExamID, String Examinee, String Date) throws SQLException {
        return rDAL.getRank(ExamID, Examinee, Date);
    }

    public int getNumOfDo(int ExamID) throws SQLException {
        return rDAL.getNumOfDo(ExamID);
    }
    
    public int Lowest(int ExamID) throws SQLException {
        return rDAL.Lowest(ExamID);
    }

    public int Highest(int ExamID) throws SQLException {
        return rDAL.Highest(ExamID);
    }

    public float AvgScore(int ExamID) throws SQLException {
        return rDAL.AvgScore(ExamID);
    }
}
