package DTO;

public class SubjectDTO {
    int SubjectID;
    String Subjectname;

    public SubjectDTO() {
    }

    public SubjectDTO(int SubjectID, String Subjectname) {
        this.SubjectID = SubjectID;
        this.Subjectname = Subjectname;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public void setSubjectname(String Subjectname) {
        this.Subjectname = Subjectname;
    }
}
