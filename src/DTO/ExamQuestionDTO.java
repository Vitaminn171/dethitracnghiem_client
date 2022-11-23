package DTO;

public class ExamQuestionDTO {
    String Question, Choice1, Choice2, Choice3, Choice4, Answer;
    int ExamID, Number;

    public ExamQuestionDTO(){
    }

    public ExamQuestionDTO(int ExamID, String Question, String Choice1, String Choice2, String Choice3, String Choice4, String Answer, int Number) {
        this.ExamID = ExamID;
        this.Question = Question;
        this.Choice1 = Choice1;
        this.Choice2 = Choice2;
        this.Choice3 = Choice3;
        this.Choice4 = Choice4;
        this.Answer = Answer;
        this.Number = Number;
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int ExamID) {
        this.ExamID = ExamID;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getChoice1() {
        return Choice1;
    }

    public void setChoice1(String Choice1) {
        this.Choice1 = Choice1;
    }
    
    
    public String getChoice2() {
        return Choice2;
    }

    public void setChoice2(String Choice2) {
        this.Choice2 = Choice2;
    }

    public String getChoice3() {
        return Choice3;
    }

    public void setChoice3(String Choice3) {
        this.Choice3 = Choice3;
    }

    public String getChoice4() {
        return Choice4;
    }

    public void setChoice4(String Choice4) {
        this.Choice4 = Choice4;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;
    }
}
