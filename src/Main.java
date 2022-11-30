
import GUI.Login;
import static BLL.Controller.*;
import com.formdev.flatlaf.FlatLightLaf;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Quoc An
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        startConnectToServer();
        new Login().setVisible(true);
    }
}
