package GUI;

import BLL.Controller;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import javax.swing.JFrame;


public class Result extends javax.swing.JFrame {

    private JFrame frame = this;
    public Result(JSONObject jsonResult) throws Exception {
        initComponents();
        this.setTitle("Quiz Exam Result");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton1.putClientProperty("JButton.focusWidth", 1);
        
        jLabel_examinee_data.setText(jsonResult.getString("examinee"));
        jLabel_score_data.setText(String.valueOf(jsonResult.getDouble("score")));
        jLabel_correct_data.setText(String.valueOf(jsonResult.getInt("correct")));
        jLabel_wrong_data.setText(String.valueOf(jsonResult.getInt("wrong")));

        LocalDate date = java.time.LocalDate.now();
        jsonResult.put("date", date.toString());
        jsonResult.put("func", "addResult");
        Controller controller = new Controller();
        String dataReceive = controller.SendReceiveData(jsonResult.toString());
        JSONObject jResponse = new JSONObject(dataReceive);
        
        if (jResponse.getBoolean("status")) {
            jLabel_rank_data.setText(String.valueOf(jResponse.getInt("rank")));
        }

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Dashboard dashboard = new Dashboard(jsonResult.getString("examinee"));
                    dashboard.setVisible(true);
                    frame.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel_examinee = new javax.swing.JLabel();
        jLabel_score = new javax.swing.JLabel();
        jLabel_correct = new javax.swing.JLabel();
        jLabel_wrong = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel_examinee_data = new javax.swing.JLabel();
        jLabel_score_data = new javax.swing.JLabel();
        jLabel_correct_data = new javax.swing.JLabel();
        jLabel_wrong_data = new javax.swing.JLabel();
        jLabel_rank_data = new javax.swing.JLabel();
        jLabel_rank = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Result");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(24, 0, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel_examinee.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examinee.setText("Examinee :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 24;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 118, 0, 0);
        getContentPane().add(jLabel_examinee, gridBagConstraints);

        jLabel_score.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_score.setText("Score :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 54;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 118, 0, 0);
        getContentPane().add(jLabel_score, gridBagConstraints);

        jLabel_correct.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_correct.setText("Correct Quiz :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 118, 0, 0);
        getContentPane().add(jLabel_correct, gridBagConstraints);

        jLabel_wrong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_wrong.setText("Wrong Quiz :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 118, 0, 0);
        getContentPane().add(jLabel_wrong, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("OK");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(34, 0, 6, 0);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel_examinee_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examinee_data.setText("Examinee");
        jLabel_examinee_data.setMaximumSize(null);
        jLabel_examinee_data.setMinimumSize(null);
        jLabel_examinee_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 119);
        getContentPane().add(jLabel_examinee_data, gridBagConstraints);

        jLabel_score_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_score_data.setText("score");
        jLabel_score_data.setMaximumSize(null);
        jLabel_score_data.setMinimumSize(null);
        jLabel_score_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 32;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 119);
        getContentPane().add(jLabel_score_data, gridBagConstraints);

        jLabel_correct_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_correct_data.setText("correct");
        jLabel_correct_data.setMaximumSize(null);
        jLabel_correct_data.setMinimumSize(null);
        jLabel_correct_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 119);
        getContentPane().add(jLabel_correct_data, gridBagConstraints);

        jLabel_wrong_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_wrong_data.setText("wrong");
        jLabel_wrong_data.setMaximumSize(null);
        jLabel_wrong_data.setMinimumSize(null);
        jLabel_wrong_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 119);
        getContentPane().add(jLabel_wrong_data, gridBagConstraints);

        jLabel_rank_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_rank_data.setText("rank");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 119);
        getContentPane().add(jLabel_rank_data, gridBagConstraints);

        jLabel_rank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_rank.setText("Rank:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 118, 0, 0);
        getContentPane().add(jLabel_rank, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_correct;
    private javax.swing.JLabel jLabel_correct_data;
    private javax.swing.JLabel jLabel_examinee;
    private javax.swing.JLabel jLabel_examinee_data;
    private javax.swing.JLabel jLabel_rank;
    private javax.swing.JLabel jLabel_rank_data;
    private javax.swing.JLabel jLabel_score;
    private javax.swing.JLabel jLabel_score_data;
    private javax.swing.JLabel jLabel_wrong;
    private javax.swing.JLabel jLabel_wrong_data;
    // End of variables declaration//GEN-END:variables
}
