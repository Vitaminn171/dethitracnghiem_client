package BLL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DAL.SubjectDAL;
import DTO.SubjectDTO;

public class SubjectBLL {
    SubjectDAL sDAL;

    public SubjectBLL() {
        sDAL = new SubjectDAL();
    }
    
    public List readSubject() throws SQLException {
        List list = sDAL.readSubject();
        return list;
    }
    
    public SubjectDTO getSubjectByID(int ID) throws SQLException {
        return new SubjectDAL().getSubjectByID(ID);
    }
}
