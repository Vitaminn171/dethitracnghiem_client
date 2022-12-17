package GUI;

import BLL.Controller;
import BLL.SendMail;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class OTP extends javax.swing.JFrame {

    boolean flag = true;

    public OTP(JSONObject json) {
        initComponents();

        this.setTitle("Quiz Exam OTP");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jFormattedTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField1.putClientProperty("JTextField.placeholderText", "OTP");
        jButton_submit.putClientProperty("JButton.buttonType", "roundRect");

        Controller controller = new Controller();
        String otp = controller.generateOTP();
        SendMail.SendOTP(json.getString("username"), otp);

        jButton_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!check()) {
                    JOptionPane.showMessageDialog(null, "OTP expired! Please register again!");
                } else {
                    try {
                        checkOtpValid(jFormattedTextField1.getText(), json, otp);
                    } catch (Exception ex) {
                        Logger.getLogger(OTP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            long countdownStarter = 10 * 60000; //convert minute to milisecond
            
            public void run() {
                //milliseconds to minutes
                long minutes = (countdownStarter / 1000) / 60;
                // milliseconds to seconds
                long seconds = (countdownStarter / 1000) % 60;
                // Print the output
                jLabel_time.setText(String.valueOf("OTP hết hiệu lực sau: " + minutes + ":" + seconds));
                countdownStarter--;

                if (countdownStarter == 0) {
                    flag = false;
                    scheduler.shutdown();
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton_submit = new javax.swing.JButton();
        jLabel_time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("OTP Verification");

        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jFormattedTextField1.setMaximumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField1.setMinimumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField1.setPreferredSize(new java.awt.Dimension(250, 40));

        jButton_submit.setBackground(new java.awt.Color(104, 185, 132));
        jButton_submit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_submit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_submit.setText("Submit");
        jButton_submit.setBorderPainted(false);
        jButton_submit.setMaximumSize(new java.awt.Dimension(100, 40));
        jButton_submit.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton_submit.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel_time.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_time.setText("OTP expires after: 10:00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_time, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Kiểm tra đầu vào OTP
    public void checkOtpValid(String otpData, JSONObject json, String correctOtp) throws Exception {
        if (!Controller.validateOTP(otpData)) {
            JOptionPane.showMessageDialog(this, "OTP invalid type! Need to be 6 digits");
        } else {
            try {
                otpVerify(otpData, json, correctOtp);
            } catch (IOException ex) {
                Logger.getLogger(OTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Kiểm tra xác thực OTP
    public void otpVerify(String otpData, JSONObject json, String correctOtp) throws IOException, Exception {
        Controller controller = new Controller();
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("func", "otp");
        jsonSend.put("otpData", otpData);
        jsonSend.put("correctOtp", correctOtp);

        String receiveData = controller.SendReceiveData(jsonSend.toString());
        JSONObject jsonDataOtpReceive = new JSONObject(receiveData);//string convert into json object
        String receive;
        if (jsonDataOtpReceive.getBoolean("status")) {
            switch (json.getString("func")) {
                case "signup": {
                    Signup signup = new Signup();
                    signup.Signup(json.toString(), controller);
                    break;
                }
                case "changePass": {
                    ChangePassword changePass = new ChangePassword(json.getString("username"));
                    changePass.changePass(String.valueOf(json), controller);
                    break;
                }
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Wrong OTP!");
            switch (json.getString("func")) {
                case "signup":
                    new Signup().setVisible(true);
                    break;
            }
        }

    }

    public boolean check() {
        return flag;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_submit;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_time;
    // End of variables declaration//GEN-END:variables
}
