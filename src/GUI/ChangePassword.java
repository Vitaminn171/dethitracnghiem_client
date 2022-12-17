package GUI;

import BLL.Controller;
import BLL.MD5;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class ChangePassword extends javax.swing.JFrame {
    private JFrame jframe = this;
    private boolean flag = false;

    public ChangePassword(String username) {
        initComponents();
        this.setTitle("Quiz Exam Change Password");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        jButton_cancel.putClientProperty("JButton.buttonType", "roundRect");
        jButton_submit.putClientProperty("JButton.buttonType", "roundRect");
        
        jPassword_Old.putClientProperty("JComponent.roundRect", true);
        jPassword_Old.putClientProperty("JTextField.placeholderText", "Old Password");
        
        jPasswordField_New.putClientProperty("JComponent.roundRect", true);
        jPasswordField_New.putClientProperty("JTextField.placeholderText", "New Password");
        
        jPasswordField_Retype.putClientProperty("JComponent.roundRect", true);
        jPasswordField_Retype.putClientProperty("JTextField.placeholderText", "Confirm New Password");

        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/GUI/Image/eye.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        jButton_eye.setIcon(imageIcon);

        jButton_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jPassword_Old.getPassword().length != 0 && jPasswordField_New.getPassword().length != 0 && jPasswordField_Retype.getPassword().length != 0) {
                    Controller controller = new Controller();
                    String oldpassword = String.valueOf(jPassword_Old.getPassword());
                    String newpassword = String.valueOf(jPasswordField_New.getPassword());
                    String retypepassword = String.valueOf(jPasswordField_Retype.getPassword());
                    if (!newpassword.equals(retypepassword)) {
                        JOptionPane.showMessageDialog(null, "Password doesn't match! Please retype.");
                        if (!Controller.validatePassword(newpassword)) {
                            JOptionPane.showMessageDialog(null, "Password too weak or invalid!");
                        }
                    } else {
                        String hashOldPass = MD5.getMd5(oldpassword);
                        String hashNewPass = MD5.getMd5(newpassword);//hash md5 for password

                        // Mở form nhập OTP
                        JSONObject jsonSend = new JSONObject();
                        jsonSend.put("func", "changePass");
                        jsonSend.put("password_old", hashOldPass);
                        jsonSend.put("password_new", hashNewPass);
                        jsonSend.put("username", username);
                        OTP otp = new OTP(jsonSend);
                        otp.setVisible(true);
                        jframe.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Do not leave the password field blank!");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordField_New = new javax.swing.JPasswordField();
        jPasswordField_Retype = new javax.swing.JPasswordField();
        jButton_cancel = new javax.swing.JButton();
        jButton_submit = new javax.swing.JButton();
        jButton_eye = new javax.swing.JButton();
        jPassword_Old = new javax.swing.JPasswordField();

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
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new java.awt.Insets(46, 0, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jPasswordField_New.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(22, 20, 0, 0);
        jPanel1.add(jPasswordField_New, gridBagConstraints);

        jPasswordField_Retype.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(22, 20, 0, 0);
        jPanel1.add(jPasswordField_Retype, gridBagConstraints);

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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 20, 56, 0);
        jPanel1.add(jButton_cancel, gridBagConstraints);

        jButton_submit.setBackground(new java.awt.Color(34, 133, 225));
        jButton_submit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_submit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_submit.setText("Submit");
        jButton_submit.setBorderPainted(false);
        jButton_submit.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(29, 0, 56, 0);
        jPanel1.add(jButton_submit, gridBagConstraints);

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
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 6, 0, 0);
        jPanel1.add(jButton_eye, gridBagConstraints);

        jPassword_Old.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(22, 20, 0, 0);
        jPanel1.add(jPassword_Old, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Hiện / ẩn mật khẩu
    private void jButton_eyeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eyeActionPerformed
        if (!flag) {
            jPasswordField_New.setEchoChar((char) 0);
            flag = true;
        } else {
            jPasswordField_New.setEchoChar('•');
            flag = false;
        }
    }//GEN-LAST:event_jButton_eyeActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_cancelActionPerformed

    // Đổi mật khẩu
    public void changePass(String data, Controller controller) throws Exception {
        try {
            
            // Gửi yêu cầu đổi mật khẩu sang server
            String dataReceive = controller.SendReceiveData(data);
            JSONObject jsonReceive = new JSONObject(dataReceive);
            if (jsonReceive.getBoolean("status")) {
                JOptionPane.showMessageDialog(this, "Change password success!");
                Dashboard dashboard = new Dashboard(jsonReceive.getString("username"));
                dashboard.setVisible(true);
            } else {
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
    private javax.swing.JPasswordField jPasswordField_New;
    private javax.swing.JPasswordField jPasswordField_Retype;
    private javax.swing.JPasswordField jPassword_Old;
    // End of variables declaration//GEN-END:variables
}
