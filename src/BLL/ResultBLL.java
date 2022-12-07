package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DTO.ResultDTO;
import DAL.ResultDAL;

public class ResultBLL {
    ResultDAL rDAL;

    public ResultBLL() {
        rDAL = new ResultDAL();
    }

    public List LoadResult(int page) throws SQLException {
        int numofrecords = 30;
        ArrayList list = rDAL.readResult();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }

    public List findResult(String str) throws SQLException {
        List list = new ArrayList();
        list = rDAL.findResult(str);
        return list;
    }

    public int insertResult(ResultDTO r) throws SQLException {
        int result = rDAL.insertResult(r);
        return result;
    }
}
