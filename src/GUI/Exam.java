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
import javax.swing.JFrame;
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
    int number = 1;
    double score = 0;
    int correct = 0;
    public long countdownStarter;
    
    private JFrame jframe = this;
    public Exam(JSONObject jsonExam, String username, int UserID) throws IOException, Exception {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        
        countdownStarter = jsonExam.getInt("limitTime") * 60000;//convert minute to milisecond
        // set the time with "limit time" and countdownW
        jButton_next.putClientProperty("JButton.buttonType", "roundRect");
        jButton_next.putClientProperty("JButton.focusWidth", 1);
//        jButton_prev.putClientProperty("JButton.buttonType", "roundRect");
//        jButton_prev.putClientProperty("JButton.focusWidth", 1);
//        jButton_finish.putClientProperty("JButton.buttonType", "roundRect");
//        jButton_finish.putClientProperty("JButton.focusWidth", 1);

        //update by Quoc An newest
        //move jRadioButton_answer_1.setActionCommand to func setDataToExam()

        
        G = new ButtonGroup();
        G.add(jRadioButton_answer_1);
        G.add(jRadioButton_answer_2);
        G.add(jRadioButton_answer_3);
        G.add(jRadioButton_answer_4);
        
        
       
        jsonExam.put("username", username);
        jsonExam.put("number", number);
        jsonExam.put("func", "getExamQuest");
        Controller controller = new Controller();
        JSONObject jsonReceive = getExamReceive(controller,jsonExam);
        jsonReceive.put("score", score);
        setDataToExam(jsonReceive,G);
        
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
//            long countdownStarter = jsonExam.getInt("limitTime") * 60000;//convert minute to milisecond

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
                    
                    if(G.getSelection() != null){
                        try {
                            JSONObject jsonSend = new JSONObject();
                            jsonSend.put("examID", jsonExam.getInt("examID"));
                            jsonSend.put("number", number);
                            jsonSend.put("answer", G.getSelection().getActionCommand());
                            jsonSend.put("username", username);
                            jsonSend.put("correct", correct);
                            jsonSend.put("func", "receiveAnswer");
                            
                            JSONObject jsonReceive = getExamReceive(controller,jsonSend);
                            if(jsonReceive.getInt("correct") != correct){
                                score = jsonReceive.getInt("correct") * Double.parseDouble(String.valueOf(10 / jsonExam.getInt("numOfQuiz")));
                                correct = jsonReceive.getInt("correct");
                            }
     
                            JSONObject jsonResult = new JSONObject();
                            jsonResult.put("userID", UserID);
                            jsonResult.put("examID", jsonExam.getInt("examID"));
                            jsonResult.put("examinee", username);
                            jsonResult.put("correct", correct);
                            jsonResult.put("score", score);
                            jsonResult.put("time", jsonExam.getInt("limitTime") * 60000);
                            jsonResult.put("wrong", jsonExam.getInt("numOfQuiz") - correct);
                            new Result(jsonResult).setVisible(true);
                            jframe.dispose();
                        } catch (Exception ex) {
                            Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        try {
                            JSONObject jsonResult = new JSONObject();
                            jsonResult.put("userID", UserID);
                            jsonResult.put("examID", jsonExam.getInt("examID"));
                            jsonResult.put("examinee", username);
                            jsonResult.put("correct", correct);
                            jsonResult.put("score", score);
                            jsonResult.put("wrong", jsonExam.getInt("numOfQuiz") - correct);
                            new Result(jsonResult).setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.MILLISECONDS);
       
        
        jButton_next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(G.getSelection() != null){
                    String answer = G.getSelection().getActionCommand();
                
                
                    JSONObject jsonSend = new JSONObject();
                    jsonSend.put("examID", jsonExam.getInt("examID"));
                    jsonSend.put("number", number);
                    jsonSend.put("answer", answer);
                    jsonSend.put("username", username); 
                    jsonSend.put("correct", correct); 
                    jsonSend.put("func", "receiveAnswer"); 
                    G.clearSelection();
                    try {
                        JSONObject jsonReceive = getExamReceive(controller,jsonSend);
                        if(jsonReceive.getInt("correct") != correct){
                            score = jsonReceive.getInt("correct") * Double.parseDouble(String.valueOf(10 / jsonExam.getInt("numOfQuiz")));
                            correct = jsonReceive.getInt("correct");
                            JOptionPane.showMessageDialog(null, "Correct answer!");
                            
                        }
                        
                        if(number == jsonExam.getInt("numOfQuiz")){
                            JOptionPane.showMessageDialog(null, "Finish!");
                            JSONObject jsonResult = new JSONObject();
                            jsonResult.put("userID", UserID);
                            jsonResult.put("examID", jsonExam.getInt("examID"));
                            jsonResult.put("examinee", username);     
                            jsonResult.put("correct", correct);
                            jsonResult.put("score", score);
                            jsonResult.put("wrong", jsonExam.getInt("numOfQuiz") - correct);
                            long time = (jsonExam.getInt("limitTime") * 60000) - countdownStarter;
                            jsonResult.put("time", time);
                                    
                            new Result(jsonResult).setVisible(true);
                            jframe.dispose();
                            
                        }else{
                            number = jsonSend.getInt("number") + 1;
                            JSONObject jsonSend_1 = new JSONObject();
                            jsonSend_1.put("examID", jsonExam.getInt("examID"));
                            jsonSend_1.put("number", number);
                            jsonSend_1.put("username", username);    
                            jsonSend_1.put("func", "getExamQuest"); 
                            JSONObject jsonReceive_1 = getExamReceive(controller,jsonSend_1);
                            jsonReceive_1.put("score", score);
                            setDataToExam(jsonReceive_1, G);
                        }
                        

                       
                        
                        

    //                int j = i + 1;
    //                if (j < jsonExam.getInt("numOfQuiz")) {
    //                    //JSONObject answer = examQuestion.getJSONObject(i);
    //                    if (G.getSelection() != null) {
    //                        JSONObject answer = examQuestion.getJSONObject(i);
    //                        answer.put("answer", G.getSelection().getActionCommand());
    //                        examQuestion.put(i, answer);
    //                    }
    ////                    answer.put("answer", G.getSelection().getActionCommand());
    ////                    examQuestion.put(answer);
    //                    i++;
    //                    JSONObject answer_next = examQuestion.getJSONObject(i);
    //                    try {
    //                        switch (answer_next.getString("answer")) {
    //                            case "A":
    //                                jRadioButton_answer_1.setSelected(true);
    //                                break;
    //                            case "B":
    //                                jRadioButton_answer_2.setSelected(true);
    //                                break;
    //                            case "C":
    //                                jRadioButton_answer_3.setSelected(true);
    //                                break;
    //                            case "D":
    //                                jRadioButton_answer_4.setSelected(true);
    //                                break;
    //                        }
    //                    } catch (Exception ex) {
    //                        G.clearSelection();
    //                    }
    //                    //G.clearSelection();
    //                    int num = answer_next.getInt("number");
    //                    jLabel_questNum.setText("Question " + num + ":");
    //                    jLabel_questTitle.setText(answer_next.getString("question"));
    //                    jRadioButton_answer_1.setText(answer_next.getString("choice1"));
    //                    jRadioButton_answer_2.setText(answer_next.getString("choice2"));
    //                    jRadioButton_answer_3.setText(answer_next.getString("choice3"));
    //                    jRadioButton_answer_4.setText(answer_next.getString("choice4"));
    //
    //                } else {
    //
    //                }
                    } catch (Exception ex) {
                        Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else{
                    //did not fill in the answer
                    JOptionPane.showMessageDialog(null, "Did not fill in the answer");
                }
            }
        });
        
//        jButton_prev.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                if (i > 0) {
//                    JSONObject answer = examQuestion.getJSONObject(i);
//                    if (G.getSelection() != null) {
//                        answer.put("answer", G.getSelection().getActionCommand());
//                        examQuestion.put(i, answer);
//                    }
//                    i--;
//                    JSONObject answer_prev = examQuestion.getJSONObject(i);
//                    //answer_prev.put("answer", G.getSelection().getActionCommand());
//                    try {
//                        switch (answer_prev.getString("answer")) {
//                            case "A":
//                                jRadioButton_answer_1.setSelected(true);
//                                break;
//                            case "B":
//                                jRadioButton_answer_2.setSelected(true);
//                                break;
//                            case "C":
//                                jRadioButton_answer_3.setSelected(true);
//                                break;
//                            case "D":
//                                jRadioButton_answer_4.setSelected(true);
//                                break;
//                        }
//                    } catch (Exception ex) {
//                        G.clearSelection();
//                    }
//
//                    int num = answer_prev.getInt("number");
//                    jLabel_questNum.setText("Question " + num + ":");
//                    jLabel_questTitle.setText(answer_prev.getString("question"));
//                    jRadioButton_answer_1.setText(answer_prev.getString("choice1"));
//                    jRadioButton_answer_2.setText(answer_prev.getString("choice2"));
//                    jRadioButton_answer_3.setText(answer_prev.getString("choice3"));
//                    jRadioButton_answer_4.setText(answer_prev.getString("choice4"));
//
//                }
//
//            }
//        });
//
//        jButton_finish.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                JSONObject answer = examQuestion.getJSONObject(i);
//                if (G.getSelection() != null) {
//                    //JSONObject answer = examQuestion.getJSONObject(i);
//                    answer.remove("answer");
//                    answer.put("answer", G.getSelection().getActionCommand());
//                    examQuestion.put(i, answer);
//                } else {
//                    answer.put("answer", "none");
//                    examQuestion.put(i, answer);
//                }
//                for (int i = 0; i < examQuestion.length(); i++) {
//                    JSONObject data = examQuestion.getJSONObject(i);
//                    try {
//                        data.getString("answer");
//                    } catch (Exception ex) {
//                        data.put("answer", "none");
//                        examQuestion.put(i, data);
//                    }
//
//                }
//
//                JSONObject jsonSend = new JSONObject();
//                jsonSend.put("username", username);
//                jsonSend.put("func", "receiveAnswer");
//                jsonSend.put("examID", jsonExam.getInt("examID"));
//                jsonSend.put("data", examQuestion);
//
//                Controller controller = new Controller();
//                String data;
//                try {
//                    data = controller.SendReceiveData(jsonSend.toString());
//                    JSONObject jsonResult = new JSONObject(data);
//                    Result result = new Result(jsonResult);
//                    result.setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (Exception ex) {
//                    Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//
//        });

        

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
        jButton_next = new javax.swing.JButton();
        jLabel_time = new javax.swing.JLabel();
        jLabel_score = new javax.swing.JLabel();

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

        jButton_next.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jButton_next.setText("Next");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
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

        jLabel_score.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_score.setText("Score : 123");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(18, 0, 50, 42);
        getContentPane().add(jLabel_score, gridBagConstraints);

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

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jRadioButton_answer_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_answer_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_answer_1ActionPerformed

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

    private JSONObject getExamReceive(Controller controller, JSONObject json) throws IOException, Exception {
        String dataReceive = controller.SendReceiveData(json.toString());
        JSONObject jsonReceive = new JSONObject(dataReceive);
        return jsonReceive;
    }

//    private JSONObject getExamAnswer(Controller controller, JSONObject json) throws IOException, Exception {
////        JSONObject jsonSend = new JSONObject();
////        jsonSend.put("username", json.getString("username"));
////        jsonSend.put("func", "getExamAnswer");
////        jsonSend.put("number", json.getString("number"));
////        jsonSend.put("examID", json.getInt("examID"));
////        jsonSend.put("numOfQuiz", json.getInt("numOfQuiz"));
////        jsonSend.put("answer", json.getString("answer"));
//        
//        String dataReceive = controller.SendReceiveData(json.toString());
//        JSONObject jsonReceive = new JSONObject(dataReceive);
//        return jsonReceive;
//    }
    
    private void setDataToExam(JSONObject json, ButtonGroup G) throws Exception{
        
        
        int num = json.getInt("number");
        jLabel_questNum.setText("Question " + num + ":");
        jLabel_questTitle.setText(json.getString("question"));
        jRadioButton_answer_1.setText("A. " + json.getString("choice1"));
        jRadioButton_answer_2.setText("B. " + json.getString("choice2"));
        jRadioButton_answer_3.setText("C. " + json.getString("choice3"));
        jRadioButton_answer_4.setText("D. " + json.getString("choice4"));
        jLabel_score.setText("Score : "+ json.getDouble("score"));
        
        //update by Quoc An newest
        jRadioButton_answer_1.setActionCommand(json.getString("choice1"));
        jRadioButton_answer_2.setActionCommand(json.getString("choice2"));
        jRadioButton_answer_3.setActionCommand(json.getString("choice3"));
        jRadioButton_answer_4.setActionCommand(json.getString("choice4"));
        //update by Quoc An newest
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_next;
    private javax.swing.JLabel jLabel_questNum;
    private javax.swing.JLabel jLabel_questTitle;
    private javax.swing.JLabel jLabel_score;
    private javax.swing.JLabel jLabel_time;
    private javax.swing.JRadioButton jRadioButton_answer_1;
    private javax.swing.JRadioButton jRadioButton_answer_2;
    private javax.swing.JRadioButton jRadioButton_answer_3;
    private javax.swing.JRadioButton jRadioButton_answer_4;
    // End of variables declaration//GEN-END:variables
}
