package BLL;

import DTO.ExamDTO;
import DTO.ExamQuestionDTO;
import DTO.SubjectDTO;
import DTO.UserDTO;
import GUI.Login;
import com.google.gson.Gson;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Controller_Server {

    UserBLL uBLL = new UserBLL();
    SubjectBLL sBLL = new SubjectBLL();
    ExamBLL eBLL = new ExamBLL();
    ExamQuestionBLL examQuestionBLL = new ExamQuestionBLL();
    ResultBLL rBLL = new ResultBLL();

    public JSONObject checkFunction(JSONObject json) throws IOException, SQLException {

        switch (json.getString("func")) {
            case "login" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password"));
                if (user == null) {
                    json.put("status", false);
                    json.put("message", "Sai tên tài khoản hoặc mật khẩu!");
                } else if (user.isBlockLogin()) {
                    json.put("status", false);
                    json.put("message", "Tài khoản đang bị khóa, vui lòng liên hệ quản trị viên!");
                } else {
                    json.put("userID", user.getUserID());
                    json.put("fullname", user.getFullname());
                    json.put("birth", user.getDateofBirth());
                    json.put("gender", user.isGender());
                    json.put("status", true);
                    uBLL.Logon(user.getUserID());
                }
            }

            case "signup" -> {
                if (uBLL.insertUser(json.getString("username"), json.getString("password"), json.getString("fullname"),
                        json.getBoolean("gender"), json.getString("birth")) == 0) {
                    json.put("status", false);
                } else {
                    json.put("status", true);
                }
            }
            case "user" -> {
                UserDTO user = uBLL.getUserByUsername(json.getString("username"));
                if (user == null) {
                    json.put("status", false);
                } else {
                    json.put("userID", user.getUserID());
                    json.put("fullname", user.getFullname());
                    json.put("birth", user.getDateofBirth());
                    json.put("blockTakeExam", user.isBlockTakeExam());
                    json.put("blockAddExam", user.isBlockAddExam());
                    json.put("gender", user.isGender());
                    json.put("status", true);
                }
            }
            case "otp" -> {
                if (!json.getString("otpData").equals(json.getString("correctOtp"))) {
                    json.put("status", false);
                } else {
                    json.put("status", true);
                }
            }

            case "changePass" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password_old"));
                if (user == null) {
                    json.put("status", false);
                    json.put("message", "Sai mật khẩu!");
                } else if (json.getString("password_old").equals(json.getString("password_new"))) {
                    json.put("status", false);
                    json.put("message", "Mật khẩu mới phải khác mật khẩu cũ!");
                } else {
                    uBLL.changePassword(json.getString("username"), json.getString("password_new"));
                    json.put("status", true);
                }
            }

            case "editUserInfor" -> {
                if (uBLL.updateUser(json.getString("username"), json.getString("fullname"), json.getBoolean("gender"),
                        json.getString("birth")) == 0) {
                    json.put("status", false);
                } else {
                    json.put("blockLogin", false);
                    json.put("status", true);
                }
            }

            case "getBlockStatus" -> {
                UserDTO user = uBLL.getBlockStatus(json.getString("username"));
                json.put("blockTakeExam", user.isBlockTakeExam());
                json.put("blockAddExam", user.isBlockAddExam());
            }

            case "getExamAll" -> {
                List list = eBLL.readExam();
                JSONArray examlist = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    ExamDTO examDTO = (ExamDTO) list.get(i);
                    examlist.put(new JSONObject().put("examID", examDTO.getExamID())
                            .put("subjectName", examDTO.getSubjectname())
                            .put("examTitle", examDTO.getTitle())
                            .put("numOfQuiz", examDTO.getNumOfQuiz())
                            .put("lowestScore", examDTO.getLowest())
                            .put("highestScore", examDTO.getHighest())
                            .put("avgScore", examDTO.getAvg())
                            .put("limitTime", examDTO.getTime())
                            .put("numOfDo", examDTO.getNumOfDo())
                            .put("creator", examDTO.getFullname()));
                }
                json.put("examlist", examlist);
                System.out.println(json.toString());
            }

            case "getExamByUser" -> {
                List list = eBLL.getExamByUser(json.getString("username"));
                JSONArray examlist = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    ExamDTO examDTO = (ExamDTO) list.get(i);
                    examlist.put(new JSONObject().put("examID", examDTO.getExamID())
                            .put("subjectName", examDTO.getSubjectname())
                            .put("examTitle", examDTO.getTitle())
                            .put("numOfQuiz", examDTO.getNumOfQuiz())
                            .put("lowestScore", examDTO.getLowest())
                            .put("highestScore", examDTO.getHighest())
                            .put("avgScore", examDTO.getAvg())
                            .put("limitTime", examDTO.getTime())
                            .put("numOfDo", examDTO.getNumOfDo())
                            .put("creator", examDTO.getFullname()));
                }
                json.put("examlist", examlist);
                System.out.println(json.toString());
            }

            case "getExam" -> {
                List list = eBLL.getExamBySubject(json.getInt("subjectID"));
                JSONArray examlist = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    ExamDTO examDTO = (ExamDTO) list.get(i);
                    examlist.put(new JSONObject().put("examID", examDTO.getExamID())
                            .put("subjectName", examDTO.getSubjectname())
                            .put("examTitle", examDTO.getTitle())
                            .put("numOfQuiz", examDTO.getNumOfQuiz())
                            .put("lowestScore", examDTO.getLowest())
                            .put("highestScore", examDTO.getHighest())
                            .put("avgScore", examDTO.getAvg())
                            .put("limitTime", examDTO.getTime())
                            .put("numOfDo", examDTO.getNumOfDo())
                            .put("creator", examDTO.getFullname()));
                }
                json.put("examlist", examlist);
                System.out.println(json.toString());
            }

            case "addExam" -> {
            }

            case "editExam" -> {
                if (eBLL.updateExam(json.getInt("examID"), json.getString("examTitle"), json.getInt("subjectID"),
                        json.getInt("numOfQuiz"), json.getInt("limitTime")) == 0) {
                    json.put("status", false);
                    json.put("message", "Cập nhật đề thi thất bại!");
                } else {
                    json.put("status", true);
                    json.put("message", "Cập nhật đề thi thành công!");
                }
            }

            case "deleteExam" -> {
                if (eBLL.deleteExam(json.getInt("examID")) == 0) {
                    json.put("status", false);
                    json.put("message", "Xóa đề thi thất bại!");
                } else {
                    json.put("status", true);
                    json.put("message", "Xóa đề thi thành công");
                }
            }

            case "getExamQuest" -> {

                ExamQuestionDTO examQuest = examQuestionBLL.getExamQuestion(json.getInt("examID"),
                        json.getInt("number"));

                json.put("question", examQuest.getQuestion());
                json.put("choice1", examQuest.getChoice1());
                json.put("choice2", examQuest.getChoice2());
                json.put("choice3", examQuest.getChoice3());
                json.put("choice4", examQuest.getChoice4());

                System.out.println(json.toString());
            }

            case "receiveAnswer" -> {

                ExamQuestionDTO eq = examQuestionBLL.getExamAnswer(json.getInt("examID"), json.getInt("number"));

                int correct = checkAnswer(eq.getAnswer(), json.getString("answer"), json.getInt("correct"));
                // int score = correct * (10 / (jsonSend.getInt("numOfQuiz")));
                json.put("correct", correct);
                // json.put("score", score);

                System.out.println(json.toString());
            }

            case "getSubject" -> {
                List list = sBLL.readSubject();
                JSONArray subjectlist = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    SubjectDTO e = (SubjectDTO) list.get(i);
                    subjectlist.put(
                            new JSONObject().put("subjectID", e.getSubjectID()).put("subjectName", e.getSubjectname()));
                }
                json.put("subjectlist", subjectlist);
                System.out.println(json.toString());
            }

            case "addResult" -> {
                if (rBLL.insertResult(json.getInt("examID"), json.getString("examinee"), json.getFloat("score"),
                        json.getString("date"), json.getInt("correct"), json.getInt("wrong")) == 0) {
                    json.put("status", false);
                    json.put("message", "Thêm kết quả thi thất bại!");
                } else {
                    json.put("status", true);
                    json.put("message", "Thêm kết quả thi thành công!");
                }

            }

            case "logout" -> {
                if (uBLL.Logout(json.getInt("userID")) == 0) {
                    json.put("status", false);
                } else {
                    json.put("status", true);
                }
            }

            default -> {

            }
        }
        return json;
    }

    public int checkAnswer(String answer, String receive, int correct) {
        if (answer.equals(receive)) {
            correct++;
        }
        return correct;
    }
}
