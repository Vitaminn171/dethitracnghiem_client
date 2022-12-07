package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAL.SubjectDAL;

public class SubjectBLL {
    SubjectDAL sDAL;

    public SubjectBLL() {
        sDAL = new SubjectDAL();
    }

    public List LoadSubject(int page) throws SQLException {
        int numofrecords = 30;
        ArrayList list = sDAL.readSubject();
        int size = list.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return list.subList(from, Math.min(to, size));
    }
}
