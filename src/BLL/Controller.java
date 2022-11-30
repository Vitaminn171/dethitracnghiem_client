/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import com.google.gson.Gson;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Quoc An
 */
public class Controller {
    public static Socket socket =null;
    public static BufferedReader in;
    public static BufferedWriter out;
    
    public static Gson gson = new Gson();
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
//^                                   # start of line
//  (?=.*[0-9])                       # positive lookahead, digit [0-9]
//  (?=.*[a-z])                       # positive lookahead, one lowercase character [a-z]
//  (?=.*[A-Z])                       # positive lookahead, one uppercase character [A-Z]
//  (?=.*[!@#&()–[{}]:;',?/*~$^+=<>]) # positive lookahead, one of the special character in this [..]
//  .                                 # matches anything
//  {8,20}                            # length at least 8 characters and maximum of 20 characters
//$                                   # end of line

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    public static boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
    
    public static boolean startConnectToServer(){
        
        try{
            socket = new Socket("localhost", 1234);//kết nối tới server
            System.out.println("connecting...");
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
         catch (IOException e) {
            System.out.println("Server chưa online");
            closeConnectToServer();
            return false;
        }
        return true;
    }
    
    public static boolean closeConnectToServer(){
        //close
        try{
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        } catch (NullPointerException ex){
            return false;
        }
        return true;
    }
    
    public static String SendAndReceiveData(String data, Container parentComponent){
        String receive = "";
        try{
            out.write(data);
            out.newLine();
            out.flush();
            //System.out.println(data);
            receive = in.readLine();
            System.out.println(receive);
        } catch (IOException e) {
            System.out.println("Server closed!!!");
            JOptionPane.showMessageDialog(parentComponent,"Server not available!");
            System.exit(0);
        }
        return receive;//trả kết quả chuỗi cho hàm
    }

   
    
}
