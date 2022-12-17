package GUI;

import BLL.Controller;
import BLL.MD5;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        jButton_login.putClientProperty("JButton.buttonType", "roundRect");
        jButton_login.putClientProperty("JButton.focusWidth", 1);
        jButton_cancel.putClientProperty("JButton.buttonType", "roundRect");
        jButton_cancel.putClientProperty("JButton.focusWidth", 1);
        jFormattedTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField1.putClientProperty("JTextField.placeholderText", "Email");
        jFormattedTextField1.putClientProperty("JTextField.padding", 12);
        jPasswordField2.putClientProperty("JComponent.roundRect", true);
        jPasswordField2.putClientProperty("JTextField.placeholderText", "Password");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton_login = new javax.swing.JButton();
        jButton_cancel = new javax.swing.JButton();
        jLabel_signup = new javax.swing.JLabel();

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
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(38, 60, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Welcome back, please login to your account.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 60, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(150, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 0, 60);
        jPanel1.add(jFormattedTextField1, gridBagConstraints);

        jPasswordField2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPasswordField2.setPreferredSize(new java.awt.Dimension(150, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.ipadx = 216;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(15, 60, 0, 60);
        jPanel1.add(jPasswordField2, gridBagConstraints);

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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 37;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 18, 0);
        jPanel1.add(jButton_login, gridBagConstraints);

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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(30, 60, 18, 60);
        jPanel1.add(jButton_cancel, gridBagConstraints);

        jLabel_signup.setBackground(new java.awt.Color(51, 102, 255));
        jLabel_signup.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel_signup.setText("Create Account");
        jLabel_signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_signupMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        jPanel1.add(jLabel_signup, gridBagConstraints);

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

    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        try {
            Login();
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_loginActionPerformed

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton_cancelActionPerformed

    private void jLabel_signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_signupMouseClicked
        this.dispose();
        new Signup().setVisible(true);
    }//GEN-LAST:event_jLabel_signupMouseClicked

    public static Thread t;
    public String json;
    Controller controller = new Controller();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_login;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_signup;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField2;
    // End of variables declaration//GEN-END:variables

    // Gửi yêu cầu đăng nhập sang server
    private void Login() throws Exception {
        if (jFormattedTextField1.getText().length() != 0 && jPasswordField2.getPassword().length != 0) {
            String username = jFormattedTextField1.getText();
            String pass = String.valueOf(jPasswordField2.getPassword());
            String hashPass = MD5.getMd5(pass); //hash md5 for password

            JSONObject jsonSend = new JSONObject();
            jsonSend.put("username", username);
            jsonSend.put("password", hashPass);
            jsonSend.put("func", "login");
            try {
                String dataReceive = controller.SendReceiveData(jsonSend.toString());
                JSONObject json = new JSONObject(dataReceive);

                if (json.getBoolean("status")) { //status = true -> login
                    Dashboard dashboard = new Dashboard(json);
                    this.dispose();
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, json.getString("message"));
                }
            } catch (IOException ex) {
                Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Do not leave blank fields!");
        }
    }
}
