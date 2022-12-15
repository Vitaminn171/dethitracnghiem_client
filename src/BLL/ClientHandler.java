/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.Key;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


/**
 *
 * @author thieu
 */
public class ClientHandler implements Runnable{
    public Socket client ;
    private BufferedReader in;
    private BufferedWriter out;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }
    
    
    
    @Override
    public void run() {
        try {
            while (true) {                
                // Server nhận dữ liệu từ client qua stream
                String line = in.readLine();
                if (line.equals("bye")) {
                    break;
                }
                if (line.contains("///")) {
                    String dataReceiveKey = line.split("///")[0];
                    String encryptedDataReceive = line.split("///")[1];
//                    line = AES.decrypt(encryptedDataReceive, AES.generateKey(dataReceiveKey));
                }
                Controller_Server con_admin = new Controller_Server();
                JSONObject json = new JSONObject(line);
                json = con_admin.checkFunction(json);
                line = json.toString();
                StringBuilder newline = new StringBuilder();
                newline.append(line);
                line = newline.toString();
                System.out.println(line);
//                Key key= AES.generateRandomKey();
//                String encryptedData= AES.encrypt(line, key);
//                String keyValue= AES.convertKeytoString(key);
//                String data= keyValue+"///"+encryptedData;
//                out.write(data);
                out.newLine();
                out.flush();
            }
            System.out.println("Server closed connection");
            // Đóng kết nối
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            System.err.println(e);
        } 
    }
    
    
}
