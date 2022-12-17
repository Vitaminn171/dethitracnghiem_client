package GUI;

import BLL.Controller;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;

public class ExamDetail extends javax.swing.JFrame {

    public ExamDetail(int examid, String username) throws Exception {
        initComponents();
        this.setTitle("Quiz Exam Result Detail");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jButton_close.putClientProperty("JButton.buttonType", "roundRect");
        jButton_close.putClientProperty("JButton.focusWidth", 1);

        // Thực hiện yêu cầu lấy chi tiết đề thi
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getExamByID");
        jsonSend.put("examID", examid);
        Controller controller = new Controller();
        String receive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jsonReceiveExam = new JSONObject(receive);

        // Hiển thị chi tiết đề thi trên các label
        jLabel_examid_data.setText(String.valueOf(jsonReceiveExam.getInt("examID")));
        jLabel_avg_data.setText(String.valueOf(jsonReceiveExam.getInt("avg")));
        jLabel_creator_data.setText(jsonReceiveExam.getString("creator"));
        jLabel_subject_data.setText(jsonReceiveExam.getString("subject"));
        jLabel_title_data.setText(jsonReceiveExam.getString("title"));
        jLabel_numofquiz_data.setText(String.valueOf(jsonReceiveExam.getInt("numOfQuiz")));
        jLabel_highest_data.setText(String.valueOf(jsonReceiveExam.getInt("highest")));
        jLabel_lowest_data.setText(String.valueOf(jsonReceiveExam.getInt("lowest")));
        jLabel_time_data.setText(String.valueOf(jsonReceiveExam.getInt("time")));

        // Thực hiện yêu cầu lấy bộ câu hỏi trong đề thi
        jsonSend.remove("func");
        jsonSend.put("func", "getExamDetailByID");
        String receiveQuiz = controller.SendReceiveData(jsonSend.toString());
        JSONObject jsonReceiveQuiz = new JSONObject(receiveQuiz);

        JSONArray arrayQuiz = jsonReceiveQuiz.getJSONArray("data");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        setDataToTable(arrayQuiz, model);

        jButton_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Dashboard(username).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ExamDetail.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel_creator_data = new javax.swing.JLabel();
        jLabel_lowest = new javax.swing.JLabel();
        jLabel_subject = new javax.swing.JLabel();
        jLabel_time_data = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_creator = new javax.swing.JLabel();
        jLabel_highest = new javax.swing.JLabel();
        jLabel_avg_data = new javax.swing.JLabel();
        jLabel_subject_data = new javax.swing.JLabel();
        jLabel_examid_data = new javax.swing.JLabel();
        jLabel_title_data = new javax.swing.JLabel();
        jLabel_avg = new javax.swing.JLabel();
        jLabel_highest_data = new javax.swing.JLabel();
        jLabel_time = new javax.swing.JLabel();
        jLabel_title = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_examid = new javax.swing.JLabel();
        jLabel_numofquiz = new javax.swing.JLabel();
        jLabel_numofquiz_data = new javax.swing.JLabel();
        jLabel_lowest_data = new javax.swing.JLabel();
        jButton_close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(773, 700));
        setSize(new java.awt.Dimension(1030, 900));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel_creator_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_creator_data.setText("creator");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 174);
        getContentPane().add(jLabel_creator_data, gridBagConstraints);

        jLabel_lowest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_lowest.setText("Lowest score:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 43, 0, 18);
        getContentPane().add(jLabel_lowest, gridBagConstraints);

        jLabel_subject.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_subject.setText("Subject: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(jLabel_subject, gridBagConstraints);

        jLabel_time_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_time_data.setText("time");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_time_data, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("VIEW EXAM DETAIL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(18, 25, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel_creator.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_creator.setText("Creator:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 41;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(jLabel_creator, gridBagConstraints);

        jLabel_highest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_highest.setText("Highest score:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 43, 0, 18);
        getContentPane().add(jLabel_highest, gridBagConstraints);

        jLabel_avg_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_avg_data.setText("avg");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 174);
        getContentPane().add(jLabel_avg_data, gridBagConstraints);

        jLabel_subject_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_subject_data.setText("subject");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 174);
        getContentPane().add(jLabel_subject_data, gridBagConstraints);

        jLabel_examid_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examid_data.setText("exam id");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_examid_data, gridBagConstraints);

        jLabel_title_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_title_data.setText("title");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_title_data, gridBagConstraints);

        jLabel_avg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_avg.setText("Avg score:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        getContentPane().add(jLabel_avg, gridBagConstraints);

        jLabel_highest_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_highest_data.setText("highest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_highest_data, gridBagConstraints);

        jLabel_time.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_time.setText("Limit time: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 174, 0, 0);
        getContentPane().add(jLabel_time, gridBagConstraints);

        jLabel_title.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_title.setText("Exam title:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 174, 0, 0);
        getContentPane().add(jLabel_title, gridBagConstraints);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Number", "Question", "Choice 1", "Choice 2", "Choice 3", "Choice 4", "Answer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 980;
        gridBagConstraints.ipady = 574;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(30, 6, 6, 6);
        getContentPane().add(jScrollPane2, gridBagConstraints);

        jLabel_examid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examid.setText("Exam ID :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 174, 0, 0);
        getContentPane().add(jLabel_examid, gridBagConstraints);

        jLabel_numofquiz.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_numofquiz.setText("Num of quiz:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 43, 0, 18);
        getContentPane().add(jLabel_numofquiz, gridBagConstraints);

        jLabel_numofquiz_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_numofquiz_data.setText("num of quiz");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_numofquiz_data, gridBagConstraints);

        jLabel_lowest_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_lowest_data.setText("lowest");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 18);
        getContentPane().add(jLabel_lowest_data, gridBagConstraints);

        jButton_close.setBackground(new java.awt.Color(255, 102, 102));
        jButton_close.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_close.setForeground(new java.awt.Color(255, 255, 255));
        jButton_close.setText("Close");
        jButton_close.setMaximumSize(new java.awt.Dimension(100, 40));
        jButton_close.setMinimumSize(new java.awt.Dimension(100, 40));
        jButton_close.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_closeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        getContentPane().add(jButton_close, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_closeActionPerformed

    private void setDataToTable(JSONArray jSONArray, DefaultTableModel model) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jOBJ = jSONArray.getJSONObject(i);
            Object[] row = {jOBJ.get("number").toString(),
                jOBJ.get("question").toString(),
                jOBJ.get("choice1").toString(),
                jOBJ.get("choice2").toString(),
                jOBJ.get("choice3").toString(),
                jOBJ.get("choice4").toString(),
                jOBJ.get("answer").toString(),};

            // Add exam to each row
            model.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_avg;
    private javax.swing.JLabel jLabel_avg_data;
    private javax.swing.JLabel jLabel_creator;
    private javax.swing.JLabel jLabel_creator_data;
    private javax.swing.JLabel jLabel_examid;
    private javax.swing.JLabel jLabel_examid_data;
    private javax.swing.JLabel jLabel_highest;
    private javax.swing.JLabel jLabel_highest_data;
    private javax.swing.JLabel jLabel_lowest;
    private javax.swing.JLabel jLabel_lowest_data;
    private javax.swing.JLabel jLabel_numofquiz;
    private javax.swing.JLabel jLabel_numofquiz_data;
    private javax.swing.JLabel jLabel_subject;
    private javax.swing.JLabel jLabel_subject_data;
    private javax.swing.JLabel jLabel_time;
    private javax.swing.JLabel jLabel_time_data;
    private javax.swing.JLabel jLabel_title;
    private javax.swing.JLabel jLabel_title_data;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
