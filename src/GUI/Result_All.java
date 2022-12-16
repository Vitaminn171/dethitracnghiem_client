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
public class Result_All extends javax.swing.JPanel {

    

    /**
     * Creates new form Exam_All
     */
    public JPanel getjPanel1() {
        return jPanel1;
    }

    public Result_All(String username) throws IOException, Exception {
        initComponents();
        setCenterTable();

        Controller controller = new Controller();

        

        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getResultAll");
        String dataReceive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jSONObject = new JSONObject(dataReceive);
        JSONArray arrayReceive = jSONObject.getJSONArray("data");
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        setDataToTable(arrayReceive, model);
        //tableMouseClick(arrayReceive);
        
        
//        jComboBox_subject.addItem("All");
//        jComboBox_subject.addItem("Me");
//        jComboBox_subject.setSelectedItem("ALL");
//        for (int i = 0; i < listSubject.size(); i++) {
//            jComboBox_subject.addItem(listSubject.get(i).toString());
//        }
//
//        if (jComboBox_subject.getSelectedItem().equals("All")) {
//            setDataToTable(arrayExam, model);
//            tableMouseClick(arrayExam, username);
//        }
//
//        //get subject when click in combo box 
//        jComboBox_subject.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//
//                //clear data exam before select another subject 
//                int rowCount = model.getRowCount();
//                //Remove rows one by one from the end of the table
//                for (int i = rowCount - 1; i >= 0; i--) {
//                    model.removeRow(i);
//                }
//                //clear data exam before select another subject 
//
//                //send data to get exam with subject or get ALL with subject selected "ALL"
//                int subjectSelected = jComboBox_subject.getSelectedIndex();
//                if (subjectSelected == 0) {
//                    setDataToTable(arrayExam, model);
//                    tableMouseClick(arrayExam, username);
//                } else if (subjectSelected == 1) {
//                    JSONObject jsonExamUser = new JSONObject();
//                    jsonExamUser.put("username", username);
//                    jsonExamUser.put("func", "getExamByUser");
//                    try {
//
//                        String dataExamUser = controller.SendReceiveData(jsonExamUser.toString());
//                        JSONObject jSONExamUser = new JSONObject(dataExamUser);
//                        JSONArray arrayExamUser = jSONExamUser.getJSONArray("examlist");
//
//                        setDataToTable(arrayExamUser, model);
//                        tableMouseClick(arrayExamUser, username);
//                    } catch (Exception ex) {
//                        Logger.getLogger(Result_All.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else {
//                    JSONObject jsonSend_1 = new JSONObject();
//                    //send data
//                    jsonSend_1.put("username", username);
//                    jsonSend_1.put("func", "getExam");
//                    jsonSend_1.put("subjectID", subjectSelected - 1);
//
//                    String dataReceive_1;
//
//                    try {
//                        //receive data and push it to table 
//                        dataReceive_1 = controller.SendReceiveData(jsonSend_1.toString());
//                        JSONObject jSONObject_1 = new JSONObject(dataReceive_1);
//                        System.out.println(dataReceive_1);
//                        JSONArray arrayReceive_1 = jSONObject_1.getJSONArray("examlist");
//                        // get json array inside json object
//
//                        setDataToTable(arrayReceive_1, model);
//
//                        tableMouseClick(arrayReceive_1, username);
//
//                    } catch (Exception ex) {
//                        Logger.getLogger(Result_All.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            }
//        });

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(0, 0));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Result ID", "Exam ID", "Examinee", "Score", "Correct Quiz", "Wrong Quiz", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setEnabled(false);
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1475;
        gridBagConstraints.ipady = 983;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

 

    private void setCenterTable() {
        //center data in table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setDefaultRenderer(String.class, centerRenderer);
    }

    private void setDataToTable(JSONArray jSONArray, DefaultTableModel model) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jOBJ = jSONArray.getJSONObject(i);
            Object[] row = {jOBJ.get("resultID").toString(),
                jOBJ.get("examID").toString(),
                jOBJ.get("examinee").toString(),
                jOBJ.get("score").toString(),
                jOBJ.get("correct").toString(),
                jOBJ.get("wrong").toString(),
                jOBJ.get("date").toString()
                };

            //add exam to each row
            model.addRow(row);
        }
    }

//    private void tableMouseClick(JSONArray jSONArray) {
//        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                int row = jTable1.rowAtPoint(evt.getPoint());
//                String s = jTable1.getModel().getValueAt(row, 0) + "";
//                int id = Integer.parseInt(s);
//                if (jComboBox_subject.getSelectedIndex() == 1) {
//                    Controller controller = new Controller();
//                    JSONObject jsonExam = new JSONObject();
//                    jsonExam.put("examID", id);
//                    String[] options = {"Xem", "Sửa", "Xóa"};
//                    int option = JOptionPane.showOptionDialog(null, null, null,
//                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
//                            null, options, null);
//                    if (option == 0) {
//                        JOptionPane.showMessageDialog(null, "Xem chi tiết");
//                    }
//                    int NumOfDo = Integer.parseInt(jTable1.getModel().getValueAt(row, 6).toString());
//                    if ((option == 1 || option == 2) && NumOfDo > 0) {
//                        JOptionPane.showMessageDialog(null, "Không thể sửa hoặc xóa khi đã được thi qua!");
//                    } else {
//                        if (option == 1) {
//                            //jsonExam.put("func", "editExam");
//                            jsonExam.put("examTitle", jTable1.getModel().getValueAt(row, 1) + "");
//                            jsonExam.put("subjectName", jTable1.getModel().getValueAt(row, 2) + "");
//                            jsonExam.put("numOfQuiz", jTable1.getModel().getValueAt(row, 4) + "");
//                            jsonExam.put("limitTime", jTable1.getModel().getValueAt(row, 5) + "");
//                            System.out.println(jsonExam.toString());
//                            EditExam editExam = new EditExam(jsonExam);
//                            editExam.setVisible(true);
//                        }
//                        if (option == 2) {
//                            jsonExam.put("func", "deleteExam");
//
//                            String response;
//                            try {
//                                response = controller.SendReceiveData(jsonExam.toString());
//                                JSONObject jResponse = new JSONObject(response);
//                                if (jResponse.getBoolean("status")) {
//                                    JOptionPane.showMessageDialog(null, jResponse.getString("message"));
//                                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
//                                    model.removeRow(row);
//                                } else {
//                                    JOptionPane.showMessageDialog(null, jResponse.getString("message"));
//                                }
//                            } catch (Exception ex) {
//                                Logger.getLogger(Result_All.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
//                    }
//                } else {
//
//                    String title = jTable1.getModel().getValueAt(row, 1) + "";
//
//                    int a = JOptionPane.showConfirmDialog(null, "Start the exam ID " + s + ", with title " + title + " ?");
//                    if (a == JOptionPane.YES_OPTION) {
//
//                        JSONObject jsonExam = new JSONObject();
//                        for (int i = 0; i < jSONArray.length(); i++) {
//                            JSONObject jsonTemp = jSONArray.getJSONObject(i);
//                            if (jsonTemp.getInt("examID") == id) {
//                                jsonExam = jsonTemp;
//                            }
//                        }
//                        Window window = SwingUtilities.getWindowAncestor(getjPanel1());
//                        window.dispose();
//                        getjPanel1().setVisible(false);
//
//                        //JSONObject jsonExam = jSONArray.getJSONObject(id);
//                        try {
//                            new Exam(jsonExam, username).setVisible(true);
//
//                        } catch (IOException ex) {
//                            Logger.getLogger(Result_All.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (Exception ex) {
//                            Logger.getLogger(Result_All.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}