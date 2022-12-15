/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BLL.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import BLL.ExamBLL;
import DTO.ExamDTO;
import java.awt.Image;
import java.awt.List;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class Exam_All extends javax.swing.JPanel {

    ExamBLL examBLL = new ExamBLL();
    JSONObject jSONtemp;

    /**
     * Creates new form Exam_All
     */
    public JPanel getjPanel1() {
        return jPanel1;
    }

    public Exam_All(String username) throws IOException, Exception {
        initComponents();
        setCenterTable();

        Controller controller = new Controller();
        JSONObject jsonBlock = new JSONObject();
        jsonBlock.put("username", username);
        jsonBlock.put("func", "getBlockStatus");
        String blockStatus = controller.SendReceiveData(jsonBlock.toString());
        jSONtemp = new JSONObject(blockStatus);
        System.out.println(jSONtemp.toString());
        
        JSONObject jsonExam = new JSONObject();
        jsonExam.put("username", username);
        jsonExam.put("func", "getExamAll");
        String dataExam = controller.SendReceiveData(jsonExam.toString());
        JSONObject jSONExam = new JSONObject(dataExam);
        JSONArray arrayExam = jSONExam.getJSONArray("examlist");

        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getSubject");
        String dataReceive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jSONObject = new JSONObject(dataReceive);
        JSONArray arrayReceive = jSONObject.getJSONArray("subjectlist");

        ArrayList listSubject = new ArrayList();
        // get json array inside json object 

        for (int i = 0; i < arrayReceive.length(); i++) {
            JSONObject jOBJ = arrayReceive.getJSONObject(i);
            listSubject.add(jOBJ.get("subjectName").toString());
        }

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        jComboBox_subject.addItem("All");
        jComboBox_subject.addItem("Me");
        jComboBox_subject.setSelectedItem("ALL");
        for (int i = 0; i < listSubject.size(); i++) {
            jComboBox_subject.addItem(listSubject.get(i).toString());
        }

        if (jComboBox_subject.getSelectedItem().equals("All")) {
            setDataToTable(arrayExam, model);
            tableMouseClick(arrayExam, username);
        }

        //get subject when click in combo box 
        jComboBox_subject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //clear data exam before select another subject 
                int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                //clear data exam before select another subject 

                //send data to get exam with subject or get ALL with subject selected "ALL"
                int subjectSelected = jComboBox_subject.getSelectedIndex();
                if (subjectSelected == 0) {
                    setDataToTable(arrayExam, model);
                    tableMouseClick(arrayExam, username);
                } else if (subjectSelected == 1) {
                    JSONObject jsonExamUser = new JSONObject();
                    jsonExamUser.put("username", username);
                    jsonExamUser.put("func", "getExamByUser");
                    try {

                        String dataExamUser = controller.SendReceiveData(jsonExamUser.toString());
                        JSONObject jSONExamUser = new JSONObject(dataExamUser);
                        JSONArray arrayExamUser = jSONExamUser.getJSONArray("examlist");

                        setDataToTable(arrayExamUser, model);
                        tableMouseClick(arrayExamUser, username);
                    } catch (Exception ex) {
                        Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JSONObject jsonSend_1 = new JSONObject();
                    //send data
                    jsonSend_1.put("username", username);
                    jsonSend_1.put("func", "getExam");
                    jsonSend_1.put("subjectID", subjectSelected - 1);

                    String dataReceive_1;

                    try {
                        //receive data and push it to table 
                        dataReceive_1 = controller.SendReceiveData(jsonSend_1.toString());
                        JSONObject jSONObject_1 = new JSONObject(dataReceive_1);
                        System.out.println(dataReceive_1);
                        JSONArray arrayReceive_1 = jSONObject_1.getJSONArray("examlist");
                        // get json array inside json object

                        setDataToTable(arrayReceive_1, model);

                        tableMouseClick(arrayReceive_1, username);

                    } catch (Exception ex) {
                        Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });

        jButton_AddExam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jSONtemp.getBoolean("blockAddExam")) {
                    JOptionPane.showMessageDialog(null, "Tài khoản đang bị khóa thêm đề thi, vui lòng liên hệ quản trị viên!");
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản không bị khóa thêm đề thi");
                }
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox_subject = new javax.swing.JComboBox<>();
        jButton_AddExam = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setName(""); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Subject", "Creator", "Num of Quiz", "Time", "Num Of Do", "Highest Score", "Lowest Score", "Avg Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox_subject.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_subject.setModel(new javax.swing.DefaultComboBoxModel<>());

        jButton_AddExam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_AddExam.setText("THÊM ĐỀ THI");
        jButton_AddExam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddExamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(457, 457, 457)
                        .addComponent(jButton_AddExam))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1491, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_AddExam, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton_AddExamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddExamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_AddExamActionPerformed

    private ImageIcon setImageIcon(String path, int x, int y) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    private void setCenterTable() {
        //center data in table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setDefaultRenderer(String.class, centerRenderer);
    }

    private void setDataToTable(JSONArray jSONArray, DefaultTableModel model) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jOBJ = jSONArray.getJSONObject(i);
            Object[] row = {jOBJ.get("examID").toString(),
                jOBJ.get("examTitle").toString(),
                jOBJ.get("subjectName").toString(),
                jOBJ.get("creator").toString(),
                jOBJ.get("limitTime").toString(),
                jOBJ.get("numOfDo").toString(),
                jOBJ.get("numOfQuiz").toString(),
                jOBJ.get("highestScore").toString(),
                jOBJ.get("lowestScore").toString(),
                jOBJ.get("avgScore").toString()};

            //add exam to each row
            model.addRow(row);
        }
    }

    private void tableMouseClick(JSONArray jSONArray, String username) {
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                String s = jTable1.getModel().getValueAt(row, 0) + "";
                int id = Integer.parseInt(s);
                if (jComboBox_subject.getSelectedIndex() == 1) {
                    Controller controller = new Controller();
                    JSONObject jsonExam = new JSONObject();
                    jsonExam.put("examID", id);
                    String[] options = {"Xem", "Sửa", "Xóa"};
                    int option = JOptionPane.showOptionDialog(null, null, null,
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, null);
                    if (option == 0) {
                        JOptionPane.showMessageDialog(null, "Xem chi tiết");
                    }
                    int NumOfDo = Integer.parseInt(jTable1.getModel().getValueAt(row, 6).toString());
                    if ((option == 1 || option == 2) && NumOfDo > 0) {
                        JOptionPane.showMessageDialog(null, "Không thể sửa hoặc xóa khi đã được thi qua!");
                    } else {
                        if (option == 1) {
                            //jsonExam.put("func", "editExam");
                            jsonExam.put("examTitle", jTable1.getModel().getValueAt(row, 1) + "");
                            jsonExam.put("subjectName", jTable1.getModel().getValueAt(row, 2) + "");
                            jsonExam.put("numOfQuiz", jTable1.getModel().getValueAt(row, 4) + "");
                            jsonExam.put("limitTime", jTable1.getModel().getValueAt(row, 5) + "");
                            System.out.println(jsonExam.toString());
                            EditExam editExam = new EditExam(jsonExam);
                            editExam.setVisible(true);
                        }
                        if (option == 2) {
                            jsonExam.put("func", "deleteExam");

                            String response;
                            try {
                                response = controller.SendReceiveData(jsonExam.toString());
                                JSONObject jResponse = new JSONObject(response);
                                if (jResponse.getBoolean("status")) {
                                    JOptionPane.showMessageDialog(null, jResponse.getString("message"));
                                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                                    model.removeRow(row);
                                } else {
                                    JOptionPane.showMessageDialog(null, jResponse.getString("message"));
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                } else if (jSONtemp.getBoolean("blockTakeExam")) {
                    JOptionPane.showMessageDialog(null, "Tài khoản đang bị khóa thi, vui lòng liên hệ quản trị viên!");
                } else {
                    String title = jTable1.getModel().getValueAt(row, 1) + "";

                    int a = JOptionPane.showConfirmDialog(null, "Start the exam ID " + s + ", with title " + title + " ?");
                    if (a == JOptionPane.YES_OPTION) {

                        JSONObject jsonExam = new JSONObject();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jsonTemp = jSONArray.getJSONObject(i);
                            if (jsonTemp.getInt("examID") == id) {
                                jsonExam = jsonTemp;
                            }
                        }
                        Window window = SwingUtilities.getWindowAncestor(getjPanel1());
                        window.dispose();
                        getjPanel1().setVisible(false);

                        //JSONObject jsonExam = jSONArray.getJSONObject(id);
                        try {
                            new Exam(jsonExam, username).setVisible(true);

                        } catch (IOException ex) {
                            Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AddExam;
    private javax.swing.JComboBox<String> jComboBox_subject;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
