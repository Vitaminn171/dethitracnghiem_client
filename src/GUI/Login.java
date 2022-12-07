/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.Controller;
import BLL.MD5;
import DTO.UserDTO;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.gson.Gson;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login_test
     */
    
    
    
    public Login() {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        jButton_signup.putClientProperty( "JButton.buttonType", "roundRect" );
        jButton_signup.putClientProperty( "JButton.focusWidth", 1 );
        jButton_login.putClientProperty( "JButton.buttonType", "roundRect" );
        jButton_login.putClientProperty( "JButton.focusWidth", 1 );
        jFormattedTextField1.putClientProperty( "JComponent.roundRect", true );
        jFormattedTextField1.putClientProperty( "JTextField.placeholderText","Username");
        jFormattedTextField1.putClientProperty( "JTextField.padding",12);
        jPasswordField2.putClientProperty( "JComponent.roundRect", true );
        jPasswordField2.putClientProperty( "JTextField.placeholderText","Password");
        
        
        
            
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton_signup = new javax.swing.JButton();
        jButton_login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 100));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1500, 425));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel2.setText("Login");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 7, 60);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Welcome back, please login to your account.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 60, 0, 60);
        jPanel1.add(jLabel1, gridBagConstraints);

        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(150, 40));
        jFormattedTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 0, 60);
        jPanel1.add(jFormattedTextField1, gridBagConstraints);

        jPasswordField2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPasswordField2.setPreferredSize(new java.awt.Dimension(150, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 3;
        gridBagConstraints.insets = new java.awt.Insets(15, 60, 0, 60);
        jPanel1.add(jPasswordField2, gridBagConstraints);

        jButton_signup.setBackground(new java.awt.Color(255, 255, 255));
        jButton_signup.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_signup.setForeground(new java.awt.Color(51, 51, 51));
        jButton_signup.setText("Signup");
        jButton_signup.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_signupActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 40, 0);
        jPanel1.add(jButton_signup, gridBagConstraints);

        jButton_login.setBackground(new java.awt.Color(34, 133, 225));
        jButton_login.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_login.setText("Login");
        jButton_login.setBorderPainted(false);
        jButton_login.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loginActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 40, 60);
        jPanel1.add(jButton_login, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField1ActionPerformed

    private void jButton_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_signupActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Signup signup = new Signup();
        signup.setVisible(true);
    }//GEN-LAST:event_jButton_signupActionPerformed

    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        try {
            // TODO add your handling code here:
            Login();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton_loginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static Thread t;
    public String json;
    Controller controller = new Controller();
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_login;
    private javax.swing.JButton jButton_signup;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables

    private void Login() throws Exception {
        if(jFormattedTextField1.getText().length() != 0 && jPasswordField2.getPassword().length != 0){
            String username = jFormattedTextField1.getText();
            String pass = String.valueOf(jPasswordField2.getPassword());
            String hashPass = MD5.getMd5(pass);//hash md5 for password
            
//            Map<String, String> inputMap = new HashMap<String, String>();
//            inputMap.put("username", username);//push username to inputMap
//            inputMap.put("password", hashPass);//push password hashed to inputMap
//            inputMap.put("func", "login");//push function to inputMap      
//            inputMap.put("status", "true");//push function to inputMap 
//            
            JSONObject jsonSend = new JSONObject();
            jsonSend.put("username", username);
            jsonSend.put("password", hashPass);
            jsonSend.put("func", "login");
            try {
                String dataReceive = controller.SendReceiveData(jsonSend.toString());
                System.out.println(dataReceive);
                JSONObject json = new JSONObject(dataReceive);
                
                if(json.getBoolean("status")){ //status = true -> login
                    Dashboard dashboard = new Dashboard(username);
                    this.dispose();
                    dashboard.setVisible(true);
                }else{
                     JOptionPane.showMessageDialog(this, "Can't login!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //TODO: need to hash data before send to server
        }else{
            JOptionPane.showMessageDialog(this, "Do not leave blank fields!");
        }
    }
}
