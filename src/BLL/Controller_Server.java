package BLL;

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
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Controller_Server {

    UserBLL uBLL = new UserBLL();

    public JSONObject checkFunction(JSONObject json) throws IOException, SQLException {
        switch (json.getString("func")) {
            case "login" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password"));
                if (user == null) {
                    json.put("status", "false");
                } else {
                    json.put("userid", user.getUserID());
                    json.put("fullname", user.getFullname());
                    json.put("birth", user.getDateofBirth());
                    json.put("blockStatus", user.isBlocked());
                    json.put("gender", user.isGender());
                    uBLL.Logon(user.getUserID());
                }
                break;
            }
            case "signup" -> {
                if (uBLL.insertUser(json.getString("username"), json.getString("password"), json.getString("fullname"), json.getBoolean("gender"), json.getString("birth")) == 0) {
                    json.put("status", "false");
                }
                break;
            }
            case "otp" -> {
                if (!json.getString("otpData").equals(json.getString("correctOtp"))) {
                    json.put("status", "false");
                }
                break;
            }

            case "changePass" -> {
                UserDTO user = uBLL.getUser(json.getString("username"), json.getString("password_old"));
                if (user == null) {
                    json.put("status", "false");
                } else if (json.getString("password_old").equals(json.getString("password_new"))) {
                    json.put("status", "Oldpassword!");
                } else {
                    uBLL.changePassword(json.getString("username"), json.getString("password_new"));
                }
                break;
            }
            case "editUserInfor" -> {
                if (uBLL.updateUser(json.getString("username"), json.getString("fullname"), json.getBoolean("gender"), json.getString("birth")) == 0) {
                    json.put("status", "false");
                } else {
                    json.put("blockStatus", "false");
                }
                break;
            }
            case "logout" -> {
                if (uBLL.Logout(json.getInt("userid")) == 0)
                    json.put("status", "false");
                break;
            }

            default -> {

            }
        }
        return json;
    }
}
