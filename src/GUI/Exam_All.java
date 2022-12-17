package GUI;

import BLL.Controller;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
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

public class Exam_All extends javax.swing.JPanel {

    JSONObject jSONtemp;

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
        
        // Get json array inside json object 
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

        // Get subject when click in combo box 
        jComboBox_subject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Clear data exam before select another subject 
                int rowCount = model.getRowCount();
                
                // Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }

                // Send data to get exam with subject or get ALL with subject selected "ALL"
                int subjectSelected = jComboBox_subject.getSelectedIndex();
                
                // Tất cả đề thi
                if (subjectSelected == 0) {
                    setDataToTable(arrayExam, model);
                    tableMouseClick(arrayExam, username);
                } else if (subjectSelected == 1) { // Danh sách đề thi chỉ do người đang dùng chương trình thêm vào
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
                } else { // Danh sách đề thi theo từng môn
                    JSONObject jsonSend_1 = new JSONObject();
                    
                    // Send data
                    jsonSend_1.put("username", username);
                    jsonSend_1.put("func", "getExam");
                    jsonSend_1.put("subjectID", subjectSelected - 1);
                    String dataReceive_1;

                    try {
                        
                        // Receive data and push it to table
                        dataReceive_1 = controller.SendReceiveData(jsonSend_1.toString());
                        JSONObject jSONObject_1 = new JSONObject(dataReceive_1);
                        JSONArray arrayReceive_1 = jSONObject_1.getJSONArray("examlist");
                        
                        // Get json array inside json object
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
                    AddExam ae = new AddExam(jSONtemp.getInt("userID"));
                    ae.setVisible(true);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Subject", "Creator", "Time (minute)", "Num Of Do", "Num of Quiz", "Highest Score", "Lowest Score", "Avg Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1475;
        gridBagConstraints.ipady = 983;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 6, 6);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jComboBox_subject.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_subject.setModel(new javax.swing.DefaultComboBoxModel<>());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 108;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 6, 0, 0);
        jPanel1.add(jComboBox_subject, gridBagConstraints);

        jButton_AddExam.setBackground(new java.awt.Color(34, 133, 225));
        jButton_AddExam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_AddExam.setForeground(new java.awt.Color(255, 255, 255));
        jButton_AddExam.setText("Add Exam");
        jButton_AddExam.setBorderPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 457, 0, 0);
        jPanel1.add(jButton_AddExam, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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

            // Add exam to each row
            model.addRow(row);
        }
    }

    // Các sự kiện sẽ diễn ra khi click vào đề thi trong table
    private void tableMouseClick(JSONArray jSONArray, String username) {
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.rowAtPoint(evt.getPoint());
                String s = jTable1.getModel().getValueAt(row, 0) + "";
                int id = Integer.parseInt(s);
                
                // Sự kiện trong danh sách đề thi của người đang dùng chương trình
                if (jComboBox_subject.getSelectedIndex() == 1) {
                    Controller controller = new Controller();
                    JSONObject jsonExam = new JSONObject();
                    jsonExam.put("examID", id);
                    String[] options = {"Detail", "Edit", "Delete"};
                    int option = JOptionPane.showOptionDialog(null, "Choese option below.", "Option",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, null);

                    // Mở form chi tiết đề thi
                    if (option == 0) {
                        try {
                            new ExamDetail(id, username).setVisible(true);
                            Window window = SwingUtilities.getWindowAncestor(getjPanel1());
                            window.dispose();
                            getjPanel1().setVisible(false);
                        } catch (Exception ex) {
                            Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    int NumOfDo = Integer.parseInt(jTable1.getModel().getValueAt(row, 5).toString());
                    
                    // Kiểm tra đề đã được thi chưa
                    if ((option == 1 || option == 2) && NumOfDo > 0) {
                        JOptionPane.showMessageDialog(null, "Không thể sửa hoặc xóa khi đã được thi qua!");
                    } else {
                        
                        // Mở form cập nhật đề thi
                        if (option == 1) {
                            jsonExam.put("examTitle", jTable1.getModel().getValueAt(row, 1) + "");
                            jsonExam.put("subjectName", jTable1.getModel().getValueAt(row, 2) + "");
                            jsonExam.put("numOfQuiz", jTable1.getModel().getValueAt(row, 6) + "");
                            jsonExam.put("limitTime", jTable1.getModel().getValueAt(row, 4) + "");
                            EditExam editExam = new EditExam(jsonExam);
                            editExam.setVisible(true);
                        }
                        
                        // Gửi yêu cầu xóa đề thi sang server
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
                    } // Sự kiện thi trong danh sách tất cả đề (hoặc theo môn)
                } else if (jSONtemp.getBoolean("blockTakeExam")) { // Kiểm tra trạng thái chặn thi của người dùng
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
                        try {
                            
                            // Bắt đầu chức năng thi
                            new Exam(jsonExam, username, jSONtemp.getInt("userID")).setVisible(true);
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
