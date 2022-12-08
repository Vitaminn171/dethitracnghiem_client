package BLL;

import DTO.ExamDTO;
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

    public JSONObject checkFunction(JSONObject json) throws IOException, SQLException {

        switch (json.getString("func")) {
            case "login" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password"));
                if (user == null) {
                    json.put("status", false);
                } else {
                    json.put("userid", user.getUserID());
                    json.put("fullname", user.getFullname());
                    json.put("birth", user.getDateofBirth());
                    json.put("blockStatus", user.isBlocked());
                    json.put("gender", user.isGender());
                    json.put("status", true);
                    uBLL.Logon(user.getUserID());
                }
                break;
            }
            case "signup" -> {
                if (uBLL.insertUser(json.getString("username"), json.getString("password"), json.getString("fullname"), json.getBoolean("gender"), json.getString("birth")) == 0) {
                    System.out.println("Không");
                    json.put("status", false);
                } else {
                    System.out.println("Có");
                    json.put("status", true);
                }
                break;
            }
            case "user" -> {
                UserDTO user = uBLL.getUserByUsername(json.getString("username"));
                if (user == null) {
                    json.put("status", false);
                } else {
                    json.put("userid", user.getUserID());
                    json.put("fullname", user.getFullname());
                    json.put("birth", user.getDateofBirth());
                    json.put("blockStatus", user.isBlocked());
                    json.put("gender", user.isGender());
                    json.put("status", true);
                }
                break;
            }
            case "otp" -> {
                if (!json.getString("otpData").equals(json.getString("correctOtp"))) {
                    json.put("status", false);
                } else {
                    json.put("status", true);
                }
                break;
            }

            case "changePass" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password_old"));
                if (user == null) {
                    json.put("status", false);
                } else if (json.getString("password_old").equals(json.getString("password_new"))) {
                    json.put("status", "Oldpassword!");
                } else {
                    uBLL.changePassword(json.getString("username"), json.getString("password_new"));
                    json.put("status", true);
                }
                break;
            }
            case "editUserInfor" -> {
                if (uBLL.updateUser(json.getString("username"), json.getString("fullname"), json.getBoolean("gender"), json.getString("birth")) == 0) {
                    json.put("status", false);
                } else {
                    json.put("blockStatus", false);
                    json.put("status", true);
                }
                break;
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
                                                    .put("creator", examDTO.getFullname()));
                }
                json.put("examlist", examlist);
                System.out.println(json.toString());
                break;
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
                                                    .put("creator", examDTO.getFullname()));
                }
                json.put("examlist", examlist);
                System.out.println(json.toString());
                break;
            }
            
            case "getSubject" -> {
                List list = sBLL.readSubject();
                JSONArray subjectlist = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    SubjectDTO e = (SubjectDTO) list.get(i);
                    subjectlist.put(new JSONObject().put("subjectid", e.getSubjectID()).put("subjectname", e.getSubjectname()));
                }
                json.put("subjectlist", subjectlist);
                System.out.println(json.toString());
                break;
            }

            case "logout" -> {
                if (uBLL.Logout(json.getInt("userid")) == 0) {
                    json.put("status", false);
                } else {
                    json.put("status", true);
                }
                break;
            }

            default -> {

            }
        }
        return json;
    }

    public String EncryptServerData(String data) throws IOException, Exception {
        Key key = AES.generateRandomKey();
        String encryptedData = AES.encrypt(data, key);
        String keyValue = AES.convertKeytoString(key);
        data = keyValue + "///" + encryptedData;
        System.out.println("Encrypted Value with key: " + data);
        return data;
    }
}
