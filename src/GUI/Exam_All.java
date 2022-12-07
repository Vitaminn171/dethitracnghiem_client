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
import java.awt.Window;
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
import javax.swing.SwingUtilities;

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
        
        
        
//        Map<String, String> inputMap = new HashMap<String, String>();
//            inputMap.put("username", username);
//            inputMap.put("func", "getSubject");//push function to inputMap      
//            inputMap.put("status", "true");
//            inputMap.put("data", array.toString().replace("\\", ""));


        //send data
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("func", "getSubject");      
        //send data
        
        
        

        ArrayList listSubject = new ArrayList();

        String dataReceive = controller.SendReceiveData(jsonSend.toString());
        JSONObject jSONObject = new JSONObject(dataReceive);
        JSONArray arrayReceive = jSONObject.getJSONArray("data");
        // get json array inside json object 
        for (int i = 0; i < arrayReceive.length(); i++) {
            JSONObject jOBJ = arrayReceive.getJSONObject(i);
            listSubject.add(jOBJ.get("subject").toString());
        }
           
        //jButton_search.setIcon(setImageIcon("/GUI/Image/search-icon-2.png",25,25));
 
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
                
                
                //send data to get exam with subject or get ALL with subject selected "ALL"
                String subjectSelected = jComboBox_subject.getSelectedItem().toString();
                JSONObject jsonSend_1 = new JSONObject();
                //send data
                jsonSend_1.put("username", username);
                jsonSend_1.put("func", "getExam");
                jsonSend_1.put("subject", subjectSelected);
                
                
                
                
                String dataReceive_1;
                
                try {
                    //receive data and push it to table 
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
                        
                        
                        //add exam to each row
                        model.addRow(row);
                    }
                    
                    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            //int row = jTable1.rowAtPoint(evt.getPoint());
                            //int col = jTable1.columnAtPoint(evt.getPoint());
                            int row = jTable1.rowAtPoint( evt.getPoint() );
                            //int column = source.columnAtPoint( evt.getPoint() );
                            String s = jTable1.getModel().getValueAt(row, 0)+"";
                            String title = jTable1.getModel().getValueAt(row, 1)+"";

                            int a = JOptionPane.showConfirmDialog(null,"Start the exam ID " + s + ", with title " + title + " ?");  
                            if(a == JOptionPane.YES_OPTION){  
                                JSONObject jsonExam = arrayReceive_1.getJSONObject(Integer.parseInt(s));
                                
                                
                                try {
                                    new Exam(jsonExam,username).setVisible(true);
                                   
                                } catch (IOException ex) {
                                    Logger.getLogger(Exam_All.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }  
                        }
                    });
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
        jComboBox_subject = new javax.swing.JComboBox<>();

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
        // TODO add your handling code here:
        //get id while clicked row data
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    
    private ImageIcon setImageIcon(String path, int x, int y){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(x, y,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox_subject;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
