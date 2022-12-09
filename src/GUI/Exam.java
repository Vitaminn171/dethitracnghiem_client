/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.Controller;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class Exam extends javax.swing.JFrame {

    /**
     * Creates new form Exam
     */
    int i = 0;
    public ButtonGroup G;

    public Exam(JSONObject jsonExam, String username) throws IOException, Exception {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        // set the time with "limit time" and countdown
        jButton_next.putClientProperty("JButton.buttonType", "roundRect");
        jButton_next.putClientProperty("JButton.focusWidth", 1);
        jButton_prev.putClientProperty("JButton.buttonType", "roundRect");
        jButton_prev.putClientProperty("JButton.focusWidth", 1);
        jButton_finish.putClientProperty("JButton.buttonType", "roundRect");
        jButton_finish.putClientProperty("JButton.focusWidth", 1);

        jRadioButton_answer_1.setActionCommand("A");
        jRadioButton_answer_2.setActionCommand("B");
        jRadioButton_answer_3.setActionCommand("C");
        jRadioButton_answer_4.setActionCommand("D");

        G = new ButtonGroup();
        G.add(jRadioButton_answer_1);
        G.add(jRadioButton_answer_2);
        G.add(jRadioButton_answer_3);
        G.add(jRadioButton_answer_4);

        Controller controller = new Controller();
        JSONObject json = getExamQuest(controller, username, jsonExam.getInt("examID"));
        JSONArray examQuestion = json.getJSONArray("data");
        JSONObject data = examQuestion.getJSONObject(i);
        int num = data.getInt("number");
        jLabel_questNum.setText("Question " + num + ":");
        jLabel_questTitle.setText(data.getString("question"));
        jRadioButton_answer_1.setText(data.getString("choice1"));
        jRadioButton_answer_2.setText(data.getString("choice2"));
        jRadioButton_answer_3.setText(data.getString("choice3"));
        jRadioButton_answer_4.setText(data.getString("choice4"));

        jButton_next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int j = i + 1;
                if (j < jsonExam.getInt("numOfQuiz")) {
                    //JSONObject answer = examQuestion.getJSONObject(i);
                    if (G.getSelection() != null) {
                        JSONObject answer = examQuestion.getJSONObject(i);
                        answer.put("answer", G.getSelection().getActionCommand());
                        examQuestion.put(i, answer);
                    }
//                    answer.put("answer", G.getSelection().getActionCommand());
//                    examQuestion.put(answer);
                    i++;
                    JSONObject answer_next = examQuestion.getJSONObject(i);
                    try {
                        switch (answer_next.getString("answer")) {
                            case "A":
                                jRadioButton_answer_1.setSelected(true);
                                break;
                            case "B":
                                jRadioButton_answer_2.setSelected(true);
                                break;
                            case "C":
                                jRadioButton_answer_3.setSelected(true);
                                break;
                            case "D":
                                jRadioButton_answer_4.setSelected(true);
                                break;
                        }
                    } catch (Exception ex) {
                        G.clearSelection();
                    }
                    //G.clearSelection();
                    int num = answer_next.getInt("number");
                    jLabel_questNum.setText("Question " + num + ":");
                    jLabel_questTitle.setText(answer_next.getString("question"));
                    jRadioButton_answer_1.setText(answer_next.getString("choice1"));
                    jRadioButton_answer_2.setText(answer_next.getString("choice2"));
                    jRadioButton_answer_3.setText(answer_next.getString("choice3"));
                    jRadioButton_answer_4.setText(answer_next.getString("choice4"));

                } else {

                }
            }
        });
        jButton_prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (i > 0) {
                    JSONObject answer = examQuestion.getJSONObject(i);
                    if (G.getSelection() != null) {
                        answer.put("answer", G.getSelection().getActionCommand());
                        examQuestion.put(i, answer);
                    }
                    i--;
                    JSONObject answer_prev = examQuestion.getJSONObject(i);
                    //answer_prev.put("answer", G.getSelection().getActionCommand());
                    try {
                        switch (answer_prev.getString("answer")) {
                            case "A":
                                jRadioButton_answer_1.setSelected(true);
                                break;
                            case "B":
                                jRadioButton_answer_2.setSelected(true);
                                break;
                            case "C":
                                jRadioButton_answer_3.setSelected(true);
                                break;
                            case "D":
                                jRadioButton_answer_4.setSelected(true);
                                break;
                        }
                    } catch (Exception ex) {
                        G.clearSelection();
                    }

                    int num = answer_prev.getInt("number");
                    jLabel_questNum.setText("Question " + num + ":");
                    jLabel_questTitle.setText(answer_prev.getString("question"));
                    jRadioButton_answer_1.setText(answer_prev.getString("choice1"));
                    jRadioButton_answer_2.setText(answer_prev.getString("choice2"));
                    jRadioButton_answer_3.setText(answer_prev.getString("choice3"));
                    jRadioButton_answer_4.setText(answer_prev.getString("choice4"));

                }

            }
        });

        jButton_finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JSONObject answer = examQuestion.getJSONObject(i);
                if (G.getSelection() != null) {
                    //JSONObject answer = examQuestion.getJSONObject(i);
                    answer.remove("answer");
                    answer.put("answer", G.getSelection().getActionCommand());
                    examQuestion.put(i, answer);
                } else {
                    answer.put("answer", "none");
                    examQuestion.put(i, answer);
                }
                for (int i = 0; i < examQuestion.length(); i++) {
                    JSONObject data = examQuestion.getJSONObject(i);
                    try {
                        data.getString("answer");
                    } catch (Exception ex) {
                        data.put("answer", "none");
                        examQuestion.put(i, data);
                    }

                }

                JSONObject jsonSend = new JSONObject();
                jsonSend.put("username", username);
                jsonSend.put("func", "receiveAnswer");
                jsonSend.put("examID", jsonExam.getInt("examID"));
                jsonSend.put("data", examQuestion);

                Controller controller = new Controller();
                String data;
                try {
                    data = controller.SendReceiveData(jsonSend.toString());
                    JSONObject jsonResult = new JSONObject(data);
                    Result result = new Result(jsonResult);
                    result.setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            long countdownStarter = jsonExam.getInt("limitTime") * 60000;//convert minute to milisecond

            public void run() {

                // formula for conversion for
                // milliseconds to minutes.
                long minutes = (countdownStarter / 1000) / 60;

                // formula for conversion for
                // milliseconds to seconds
                long seconds = (countdownStarter / 1000) % 60;

                // Print the output
                jLabel_time.setText(String.valueOf("Time left " + minutes + ":" + seconds + "."));
//                System.out.println(countdownStarter + " Milliseconds = "
//                                   + minutes + " minutes and "
//                                   + seconds + " seconds.");
                countdownStarter--;

                if (countdownStarter == 0) {
                    scheduler.shutdown();
                    JOptionPane.showMessageDialog(null, "Out of time!");
                    for (int i = 0; i < examQuestion.length(); i++) {
                        JSONObject data = examQuestion.getJSONObject(i);
                        try {
                            data.getString("answer");
                        } catch (Exception ex) {
                            data.put("answer", "none");
                            examQuestion.put(i, data);
                        }

                    }
                    Controller controller = new Controller();
                    JSONObject jsonSend = new JSONObject();
                    jsonSend.put("username", username);
                    jsonSend.put("func", "receiveAnswer");
                    jsonSend.put("examID", jsonExam.getInt("examID"));
                    jsonSend.put("data", examQuestion);

                    String data;
                    try {
                        data = controller.SendReceiveData(jsonSend.toString());
                        JSONObject jsonResult = new JSONObject(data);
                        Result result = new Result(jsonResult);
                        result.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.MILLISECONDS);

        // if (time == 0)
        //      then finish quiz and save all the answer
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel_questNum = new javax.swing.JLabel();
        jLabel_questTitle = new javax.swing.JLabel();
        jRadioButton_answer_1 = new javax.swing.JRadioButton();
        jRadioButton_answer_2 = new javax.swing.JRadioButton();
        jRadioButton_answer_3 = new javax.swing.JRadioButton();
        jRadioButton_answer_4 = new javax.swing.JRadioButton();
        jButton_prev = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jLabel_time = new javax.swing.JLabel();
        jButton_finish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setSize(new java.awt.Dimension(1000, 600));
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel_questNum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_questNum.setText("Question ?:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(50, 42, 0, 0);
        getContentPane().add(jLabel_questNum, gridBagConstraints);

        jLabel_questTitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel_questTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_questTitle.setText("Question ......................");
        jLabel_questTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel_questTitle.setMaximumSize(new java.awt.Dimension(133, 60));
        jLabel_questTitle.setMinimumSize(new java.awt.Dimension(133, 60));
        jLabel_questTitle.setPreferredSize(new java.awt.Dimension(133, 60));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 42, 0, 42);
        getContentPane().add(jLabel_questTitle, gridBagConstraints);

        jRadioButton_answer_1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_answer_1.setText("A");
        jRadioButton_answer_1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_answer_1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 42);
        getContentPane().add(jRadioButton_answer_1, gridBagConstraints);

        jRadioButton_answer_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_answer_2.setText("B");
        jRadioButton_answer_2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_answer_2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 42);
        getContentPane().add(jRadioButton_answer_2, gridBagConstraints);

        jRadioButton_answer_3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_answer_3.setText("C");
        jRadioButton_answer_3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_answer_3ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 42);
        getContentPane().add(jRadioButton_answer_3, gridBagConstraints);

        jRadioButton_answer_4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_answer_4.setText("D");
        jRadioButton_answer_4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_4.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jRadioButton_answer_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_answer_4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 42);
        getContentPane().add(jRadioButton_answer_4, gridBagConstraints);

        jButton_prev.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_prev.setText("Previous");
        jButton_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_prevActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 13;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 50, 0);
        getContentPane().add(jButton_prev, gridBagConstraints);

        jButton_next.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_next.setText("Next");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 50, 0);
        getContentPane().add(jButton_next, gridBagConstraints);

        jLabel_time.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_time.setText("Time : 00.00.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(50, 481, 0, 42);
        getContentPane().add(jLabel_time, gridBagConstraints);

        jButton_finish.setBackground(new java.awt.Color(255, 255, 102));
        jButton_finish.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_finish.setForeground(new java.awt.Color(51, 51, 51));
        jButton_finish.setText("Finish");
        jButton_finish.setBorderPainted(false);
        jButton_finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_finishActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(18, 491, 50, 42);
        getContentPane().add(jButton_finish, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_answer_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_answer_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_answer_3ActionPerformed

    private void jRadioButton_answer_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_answer_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_answer_4ActionPerformed

    private void jRadioButton_answer_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_answer_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_answer_2ActionPerformed

    private void jButton_finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_finishActionPerformed
        // TODO add your handling code here:
        this.dispose();
        //set data to label
    }//GEN-LAST:event_jButton_finishActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jRadioButton_answer_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_answer_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_answer_1ActionPerformed

    private void jButton_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_prevActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton_prevActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Exam().setVisible(true);
//                
//            }
//        });

    }

    private JSONObject getExamQuest(Controller controller, String username, int id) throws IOException, Exception {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getExamQuest");
        jsonSend.put("examID", id);

        String dataReceive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jsonReceive = new JSONObject(dataReceive);
        return jsonReceive;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_finish;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_prev;
    private javax.swing.JLabel jLabel_questNum;
    private javax.swing.JLabel jLabel_questTitle;
    private javax.swing.JLabel jLabel_time;
    private javax.swing.JRadioButton jRadioButton_answer_1;
    private javax.swing.JRadioButton jRadioButton_answer_2;
    private javax.swing.JRadioButton jRadioButton_answer_3;
    private javax.swing.JRadioButton jRadioButton_answer_4;
    // End of variables declaration//GEN-END:variables
}
