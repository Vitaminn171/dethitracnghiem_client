/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Quoc An
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class server_demo {
    private static ServerSocket server = null;
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(1234);
            System.out.println("Server started...");
            socket = server.accept();
            System.out.println("Client " + socket.getInetAddress() + " connected...");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            
            while(true) {
                // Server nhận dữ liệu từ client qua stream
                String line = in.readLine();
                if (line.equals("bye"))
                    break;
                
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
            socket.close();
            server.close();
        } catch (IOException e) { 
            System.err.println(e); 
        }
    }


}
//https://tiki.vn/dien-thoai-samsung-galaxy-s22-ultra-5g-8gb-128gb-hang-chinh-hang-p162593114.html?
//itm_campaign=HMP_YPD_TKA_PLA_UNK_ALL_UNK_UNK_UNK_UNK_X.119346_Y.1152823_Z.2854061_CN.Product-Ads-Manual&itm_medium=CPC&itm_source=tiki-ads&spid=162593120
