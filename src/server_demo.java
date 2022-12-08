import BLL.Controller_Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class server_demo {

    private static ServerSocket server = null;
    private static Socket socket1 = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;

    public static void main(String[] args) {
        try {
            //server lấy local IP bằng cách tạo socket đến 1 website tạm
            Socket socket = new Socket("thongtindaotao.sgu.edu.vn", 80);
            String localIP = socket.getLocalAddress().toString().substring(1);
            // SV tự generate API tại https://retool.com/api-generator/
            String api = "https://retoolapi.dev/FFY4oG/data/1"; // Ghi vào dòng 1 trong DB
            String jsonData = "{\"ip\":\"" + localIP + "\"}";
            
            //Đưa local ip lên trang tạm
            Jsoup.connect(api)
                    .ignoreContentType(true).ignoreHttpErrors(true)
                    .header("Content-Type", "application/json")
                    .requestBody(jsonData)
                    .method(Connection.Method.PUT).execute();
            System.out.println("Server local ip: " + localIP);
            
            server = new ServerSocket(6666);
            System.out.println("Server started...");
            socket1 = server.accept();
            System.out.println("Client " + socket1.getInetAddress() + " connected...");
            
            in = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));

            while (true) {
                // Server nhận dữ liệu từ client qua stream
                String line = in.readLine();
                if (line.equals("bye")) {
                    break;
                }
                
                Controller_Server con_admin = new Controller_Server();
                
                JSONObject json = new JSONObject(line);
                
                try {
                    json = con_admin.checkFunction(json);
                } catch (SQLException ex) {
                    Logger.getLogger(server_demo.class.getName()).log(Level.SEVERE, null, ex);
                }
                line = json.toString();
                StringBuilder newline = new StringBuilder();
                newline.append(line);
                line = newline.toString();
                System.out.println(line);

                out.write(line);
                out.newLine();
                out.flush();

            }
            System.out.println("Server closed connection");
            // Đóng kết nối
            in.close();
            out.close();
            socket1.close();
            server.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}

