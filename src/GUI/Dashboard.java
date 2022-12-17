package GUI;

import BLL.Controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.json.JSONObject;

public class Dashboard extends javax.swing.JFrame {

    // Constructor execute when finish other function
    public Dashboard(String username) throws IOException, Exception {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        Controller controller = new Controller();

        // Gửi yêu cầu lấy thông tin cá nhân sang server
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("func", "user");
        jsonSend.put("username", username);

        try {
            String dataReceive = controller.SendReceiveData(jsonSend.toString());
            JSONObject json = new JSONObject(dataReceive);
            JPanel UserInformation = new UserInformation(json);
            JPanel Exam_All = new Exam_All(jsonSend.getString("username"));
            JPanel Result_All = new Result_All(jsonSend.getString("username"));
            jTabbedPane1.addTab("User Information", UserInformation);
            jTabbedPane1.addTab("Exam", Exam_All);
            jTabbedPane1.addTab("Result", Result_All);
        } catch (IOException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Constructor execute when login
    public Dashboard(JSONObject json) throws IOException {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        try {
            JPanel UserInformation = new UserInformation(json);
            JPanel Exam_All = new Exam_All(json.getString("username"));
            JPanel Result_All = new Result_All(json.getString("username"));
            jTabbedPane1.addTab("User Information", UserInformation);
            jTabbedPane1.addTab("Exam", Exam_All);
            jTabbedPane1.addTab("Result", Result_All);
        } catch (Exception ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1183, 659));
        setPreferredSize(new java.awt.Dimension(1000, 900));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTabbedPane1.setMaximumSize(new java.awt.Dimension(0, 0));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTabbedPane1.setPreferredSize(null);
        jTabbedPane1.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
