/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

package GUI;

import BLL.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class Exam_All extends javax.swing.JPanel {

    /** Creates new form Exam_All */
    //get subject in database
    
    public Exam_All(String username) throws IOException {
        initComponents();
        //subject = { "Physic", "Math", "Chemistry", "English" };
        Controller controller = new Controller();
        
        
        Gson gson = new Gson();
        JSONArray array = new JSONArray();
        for (int i = 0; i < 10; i++) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("subject", String.valueOf(i));
            array.put(jsonObj);
        }
        
        
//        Map<String, String> inputMap = new HashMap<String, String>();
//            inputMap.put("username", username);
//            inputMap.put("func", "getSubject");//push function to inputMap      
//            inputMap.put("status", "true");
//            inputMap.put("data", array.toString().replace("\\", ""));

        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getSubject");      
        jsonSend.put("status", "true");
        jsonSend.put("data", array);

        ArrayList listSubject = new ArrayList();

        String dataReceive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jSONObject = new JSONObject(dataReceive);
        JSONArray arrayReceive = jSONObject.getJSONArray("data");
        // get json array inside json object 
        for (int i = 0; i < arrayReceive.length(); i++) {
            JSONObject jOBJ = arrayReceive.getJSONObject(i);
            listSubject.add(jOBJ.get("subject").toString());
        }
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource("/GUI/Image/search-icon-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(25, 25,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        jButton_search.setIcon(imageIcon);
        
        
        
        
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
      
        
        
        for(int i = 0; i < listSubject.size(); i++){
            jComboBox_subject.addItem(listSubject.get(i).toString());
        }
        
        
        //get subject when click in combo box 
        jComboBox_subject.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                //clear data exam before select another subject 
                int rowCount = model.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }
                //clear data exam before select another subject 
                
                String subjectSelected = jComboBox_subject.getSelectedItem().toString();
                JSONObject jsonSend_1 = new JSONObject();
                jsonSend_1.put("username", username);
                jsonSend_1.put("func", "getExam");
                jsonSend_1.put("subject", subjectSelected);
                JSONArray array_1 = new JSONArray();
                for (int i = 0; i < 5; i++) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("examID", i);
                    jsonObj.put("examTitle", "English");
                    jsonObj.put("numOfquiz", 10);
                    jsonObj.put("creator", "an");
                    jsonObj.put("highestScore", 10);
                    jsonObj.put("lowestScore", 0);
                    jsonObj.put("avgScore", 5);
                    array_1.put(jsonObj);
                }
                jsonSend_1.put("data", array_1);
                String dataReceive_1;
                
                try {
                    dataReceive_1 = controller.SendReceiveData(jsonSend_1.toString());
                    JSONObject jSONObject_1 = new JSONObject(dataReceive_1);
                    System.out.println(dataReceive_1);
                    JSONArray arrayReceive_1 = jSONObject_1.getJSONArray("data");
                    // get json array inside json object
                    for (int i = 0; i < arrayReceive_1.length(); i++) {
                        JSONObject jOBJ = arrayReceive_1.getJSONObject(i);
                        Object[] row = { jOBJ.get("examID").toString(), 
                                            jOBJ.get("examTitle").toString(), 
                                            jOBJ.get("numOfquiz").toString(), 
                                            jOBJ.get("creator").toString(), 
                                            jOBJ.get("highestScore").toString(),
                                            jOBJ.get("lowestScore").toString(),
                                            jOBJ.get("avgScore").toString()};
                        //jOBJ.get("subject").toString());
                        
                        model.addRow(row);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Server error!");
                }
                
            }
        });
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton_search = new javax.swing.JButton();
        jComboBox_subject = new javax.swing.JComboBox<>();
        jFormattedTextField_search = new javax.swing.JFormattedTextField();

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
                "ID", "Title", "Creator", "Num of Quizz", "Highest Score", "Avg Score", "Lowest Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1039;
        gridBagConstraints.ipady = 576;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jButton_search.setBackground(new java.awt.Color(34, 133, 225));
        jButton_search.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton_search.setForeground(new java.awt.Color(0, 0, 0));
        jButton_search.setText("  Find");
        jButton_search.setBorderPainted(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel1.add(jButton_search, gridBagConstraints);

        jComboBox_subject.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_subject.setModel(new javax.swing.DefaultComboBoxModel<>());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 108;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel1.add(jComboBox_subject, gridBagConstraints);

        jFormattedTextField_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField_searchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 211;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
        jPanel1.add(jFormattedTextField_search, gridBagConstraints);

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

    private void jFormattedTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField_searchActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        //get id while clicked row data
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        //int column = source.columnAtPoint( evt.getPoint() );
        String s=source.getModel().getValueAt(row, 0)+"";

        JOptionPane.showMessageDialog(null, s);
        
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox<String> jComboBox_subject;
    private javax.swing.JFormattedTextField jFormattedTextField_search;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
