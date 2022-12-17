package GUI;

import BLL.Controller;
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
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Exam extends javax.swing.JFrame {
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

        countdownStarter = jsonExam.getInt("limitTime") * 60000; // Convert minute to milisecond
        
        // Set the time with "limit time" and countdown
        jButton_next.putClientProperty("JButton.buttonType", "roundRect");
        jButton_next.putClientProperty("JButton.focusWidth", 1);
        G = new ButtonGroup();
        G.add(jRadioButton_answer_1);
        G.add(jRadioButton_answer_2);
        G.add(jRadioButton_answer_3);
        G.add(jRadioButton_answer_4);

        // Gửi yêu cầu lấy câu hỏi đầu tiên
        jsonExam.put("username", username);
        jsonExam.put("number", number);
        jsonExam.put("func", "getExamQuest");
        Controller controller = new Controller();
        JSONObject jsonReceive = getExamReceive(controller,jsonExam);
        jsonReceive.put("score", score);
        setDataToExam(jsonReceive,G);
        
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            public void run() {
                
                // formula for conversion for milliseconds to minutes.
                long minutes = (countdownStarter / 1000) / 60;

                // formula for conversion for milliseconds to seconds
                long seconds = (countdownStarter / 1000) % 60;

                // Hiển thị thời gian còn lại
                jLabel_time.setText(String.valueOf("Time left " + minutes + ":" + seconds + "."));
                countdownStarter--;

                // Sự kiện diễn ra khi thời gian kết thúc
                if (countdownStarter == 0) {
                    scheduler.shutdown();
                    JOptionPane.showMessageDialog(null, "Out of time!");
                    
                    // Nếu hết thời gian mà có đang tick trắc nghiệm thì gửi yêu kiểm tra kết quả câu đó
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
     
                            // Kết thúc thi và mở form kết quả bài thi
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
                    }else{ // Hết thời gian nhưng đang bỏ trống lựa chọn
                        try {
                            
                            // Kết thúc thi và mở form kết quả bài thi
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
       
        // Nút bấm chuyển sang câu hỏi kế tiếp
        jButton_next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(G.getSelection() != null){
                    String answer = G.getSelection().getActionCommand();
                    
                    // Gửi yêu cầu kiểm tra kết quả
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
                        
                        // Kiểm tra câu vừa xong có phải là câu cuối hay không
                        if(number == jsonExam.getInt("numOfQuiz")){
                            JOptionPane.showMessageDialog(null, "Finish!");
                            
                            // Kết thúc thi và mở form kết quả bài thi
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
                        } else { // Gửi yêu cầu lấy câu hỏi kế tiếp
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
                    } catch (Exception ex) {
                        Logger.getLogger(Exam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Did not fill in the answer");
                }
            }
        });
    }

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

    // Gửi và nhận kết quả câu trả lời
    private JSONObject getExamReceive(Controller controller, JSONObject json) throws IOException, Exception {
        String dataReceive = controller.SendReceiveData(json.toString());
        JSONObject jsonReceive = new JSONObject(dataReceive);
        return jsonReceive;
    }

    private void setDataToExam(JSONObject json, ButtonGroup G) throws Exception{
        int num = json.getInt("number");
        jLabel_questNum.setText("Question " + num + ":");
        jLabel_questTitle.setText(json.getString("question"));
        jRadioButton_answer_1.setText("A. " + json.getString("choice1"));
        jRadioButton_answer_2.setText("B. " + json.getString("choice2"));
        jRadioButton_answer_3.setText("C. " + json.getString("choice3"));
        jRadioButton_answer_4.setText("D. " + json.getString("choice4"));
        jLabel_score.setText("Score : "+ json.getDouble("score"));
        jRadioButton_answer_1.setActionCommand(json.getString("choice1"));
        jRadioButton_answer_2.setActionCommand(json.getString("choice2"));
        jRadioButton_answer_3.setActionCommand(json.getString("choice3"));
        jRadioButton_answer_4.setActionCommand(json.getString("choice4"));
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
