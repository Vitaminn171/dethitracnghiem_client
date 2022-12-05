/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.Controller;
import BLL.MD5;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class ChangePassword extends javax.swing.JFrame {

    private boolean flag = false;

    /**
     * Creates new form ChangePassword
     */
    public ChangePassword() {
        initComponents();
        this.setTitle("Quiz Exam Change Password");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        jButton_cancel.putClientProperty( "JButton.buttonType", "roundRect" );
        jButton_submit.putClientProperty( "JButton.buttonType", "roundRect" );
        jPasswordField1.putClientProperty( "JComponent.roundRect", true );
        jPasswordField1.putClientProperty( "JTextField.placeholderText","New Password");
        jPasswordField2.putClientProperty( "JComponent.roundRect", true );
        jPasswordField2.putClientProperty( "JTextField.placeholderText","Confirm New Password");
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/GUI/Image/eye.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(25, 25,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        jButton_eye.setIcon(imageIcon);
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
        jLabel1 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton_cancel = new javax.swing.JButton();
        jButton_submit = new javax.swing.JButton();
        jButton_eye = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CHANGE PASSWORD");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(30, 50, 0, 50);
        jPanel1.add(jLabel1, gridBagConstraints);

        jPasswordField1.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(30, 65, 0, 5);
        jPanel1.add(jPasswordField1, gridBagConstraints);

        jPasswordField2.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(15, 65, 0, 5);
        jPanel1.add(jPasswordField2, gridBagConstraints);

        jButton_cancel.setBackground(new java.awt.Color(255, 102, 102));
        jButton_cancel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_cancel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel.setText("Cancel");
        jButton_cancel.setBorderPainted(false);
        jButton_cancel.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 65, 30, 40);
        jPanel1.add(jButton_cancel, gridBagConstraints);

        jButton_submit.setBackground(new java.awt.Color(34, 133, 225));
        jButton_submit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_submit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_submit.setText("Submit");
        jButton_submit.setBorderPainted(false);
        jButton_submit.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_submitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 40, 30, 5);
        jPanel1.add(jButton_submit, gridBagConstraints);

        jButton_eye.setBackground(new java.awt.Color(255, 255, 255));
        jButton_eye.setBorderPainted(false);
        jButton_eye.setMaximumSize(new java.awt.Dimension(40, 40));
        jButton_eye.setMinimumSize(new java.awt.Dimension(20, 20));
        jButton_eye.setPreferredSize(new java.awt.Dimension(25, 25));
        jButton_eye.setRequestFocusEnabled(false);
        jButton_eye.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eyeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(30, 0, 0, 40);
        jPanel1.add(jButton_eye, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_submitActionPerformed
        // TODO add your handling code here:
        if(jPasswordField1.getPassword().length != 0 && jPasswordField2.getPassword().length != 0){
            Controller controller = new Controller();
            String password = String.valueOf(jPasswordField1.getPassword());
            String password1 = String.valueOf(jPasswordField1.getPassword());
            if(!password.equals(password1)){
                JOptionPane.showMessageDialog(this, "Password doesn't match! Please retype.");
            if(!Controller.validatePassword(password)){
                JOptionPane.showMessageDialog(this, "Password too weak or invalid!");
            }
            }else{
                String hashPass = MD5.getMd5(password);//hash md5 for password

                Map<String, String> inputMap = new HashMap<String, String>();
                inputMap.put("func", "changePass");//push username to inputMap
                inputMap.put("password", hashPass);//push password hashed to inputMap
                inputMap.put("status", "true");
                String data = controller.convertToJSON(inputMap);
                JSONObject json =  new JSONObject(data);
                
                OTP otp = new OTP(json);
                otp.setVisible(true);
                this.dispose();
                
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "Do not leave the password field blank!");
        }
        
    }//GEN-LAST:event_jButton_submitActionPerformed

    private void jButton_eyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eyeActionPerformed
        // TODO add your handling code here:
        if(!flag){
            jPasswordField1.setEchoChar((char)0);
            flag = true;
        }else{
            jPasswordField1.setEchoChar('•');
            flag = false;
        }
    }//GEN-LAST:event_jButton_eyeActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        // TODO add your handling code here:'
        this.dispose();
    }//GEN-LAST:event_jButton_cancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChangePassword().setVisible(true);
            }
        });
    }
    
    public void changePass(String data, Controller controller){
        String pass = String.valueOf(jPasswordField1.getPassword());
        
            
                try {
                    String dataReceive = controller.SendReceiveData(data);
                    System.out.println(dataReceive);
                    JSONObject jsonReceive = new JSONObject(dataReceive);
                    if(jsonReceive.getString("status").equals("true")){
                        JOptionPane.showMessageDialog(this, "Change password success!");
                    }else{
                        JOptionPane.showMessageDialog(this, "Change password fail!");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_eye;
    private javax.swing.JButton jButton_submit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables
}
