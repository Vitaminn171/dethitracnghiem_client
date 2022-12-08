
import BLL.Controller;
import GUI.Login;
import static BLL.Controller.startConnectToServer;
import static BLL.Controller.closeConnectToServer;
import com.formdev.flatlaf.FlatLightLaf;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Quoc An
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FlatLightLaf.setup();   
        new Login().setVisible(true);
        startConnectToServer();
        
    }
}
