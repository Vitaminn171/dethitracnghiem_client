package GUI;

import BLL.Controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class EditExam extends javax.swing.JFrame {
    String path = "";

    public EditExam(JSONObject json) {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jFormattedTextField_examTitle.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField_examTitle.setText(json.getString("examTitle"));

        jFormattedTextField_limitTime.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField_limitTime.setText(json.getString("limitTime"));

        // Gửi yêu cầu lấy danh sách môn
        Controller controller = new Controller();
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("func", "getSubject");
        try {
            String dataReceive = controller.SendReceiveData(jsonSend.toString());
            JSONObject jSONObject = new JSONObject(dataReceive);
            JSONArray arrayReceive = jSONObject.getJSONArray("subjectlist");
            ArrayList listSubject = new ArrayList();
            
            // Load danh sách môn vào jComboBox
            for (int i = 0; i < arrayReceive.length(); i++) {
                JSONObject jSubjectList = arrayReceive.getJSONObject(i);
                listSubject.add(jSubjectList.get("subjectName").toString());
                jComboBox_subject.addItem(listSubject.get(i).toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(EditExam.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jComboBox_subject.setSelectedItem(json.getString("subjectName"));
        jButton_cancel.putClientProperty("JButton.buttonType", "roundRect");

        jButton_submit.putClientProperty("JButton.buttonType", "roundRect");
        
        jButton_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String examTitle = jFormattedTextField_examTitle.getText();
                String limitTime = jFormattedTextField_limitTime.getText();
                int subjectID = jComboBox_subject.getSelectedIndex() + 1;
                if (examTitle.length() == 0 || limitTime.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Do not leave blank fields!");
                } else {
                    JSONObject jsonSend = new JSONObject();
                    jsonSend.put("examID", json.getInt("examID"));
                    jsonSend.put("questionlist", readExcel());
                    jsonSend.put("examTitle", examTitle);
                    jsonSend.put("subjectID", subjectID);
                    jsonSend.put("limitTime", limitTime);
                    jsonSend.put("func", "editExam");
                    try {
                        String response = controller.SendReceiveData(jsonSend.toString());
                        JSONObject jResponse = new JSONObject(response);
                        if (jResponse.getBoolean("status")) {
                            JOptionPane.showMessageDialog(null, "Sửa đề thi thành công!");
                            disposeFrame();
                        } else {
                            JOptionPane.showMessageDialog(null, jResponse.getString("message"));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(EditExam.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    private void disposeFrame() {
        this.dispose();
    }

    // Đọc dữ liệu file excel khi sửa bộ câu hỏi
    private JSONArray readExcel() {
        JSONArray arrQuestion = new JSONArray();
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file); // Obtaining bytes from the file  
            
            // Creating Workbook instance that refers to .xlsx file  
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0); // Creating a Sheet object to retrieve object  
            Iterator<Row> itr = sheet.iterator(); // Iterating over excel file
            String[] questions = new String[7];
            int count;
            while (itr.hasNext()) {
                Row row = itr.next();
                count = 0;
                Iterator<Cell> cellIterator = row.cellIterator(); // Iterating over each column  
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING: // Field that represents string cell type 
                            questions[count++] = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_NUMERIC: // Field that represents number cell type
                            questions[count++] = String.valueOf((int) cell.getNumericCellValue());
                            break;
                        default:
                    }
                }
                
                // Đưa dữ liệu đọc được vào mảng JSON
                arrQuestion.put(new JSONObject().put("number", Integer.parseInt(questions[0]))
                        .put("question", questions[1])
                        .put("choice1", questions[2])
                        .put("choice2", questions[3])
                        .put("choice3", questions[4])
                        .put("choice4", questions[5]));
            }
        } catch (IOException x) {
            x.printStackTrace();
        }
        return arrQuestion;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        datePickerAddon1 = new org.jdesktop.swingx.plaf.DatePickerAddon();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton_cancel = new javax.swing.JButton();
        jButton_submit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField_examTitle = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_subject = new javax.swing.JComboBox<>();
        jFormattedTextField_limitTime = new javax.swing.JFormattedTextField();
        jButton_file = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 400));
        jPanel1.setVerifyInputWhenFocusTarget(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UPDATE EXAM INFOMANTION");

        jButton_cancel.setBackground(new java.awt.Color(255, 102, 102));
        jButton_cancel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_cancel.setForeground(new java.awt.Color(255, 255, 255));
        jButton_cancel.setText("Cancel");
        jButton_cancel.setBorderPainted(false);
        jButton_cancel.setPreferredSize(new java.awt.Dimension(100, 40));
        jButton_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelActionPerformed(evt);
            }
        });

        jButton_submit.setBackground(new java.awt.Color(34, 133, 225));
        jButton_submit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_submit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_submit.setText("Submit");
        jButton_submit.setBorderPainted(false);
        jButton_submit.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Exam Title");

        jFormattedTextField_examTitle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField_examTitle.setMinimumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField_examTitle.setPreferredSize(new java.awt.Dimension(250, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Subject");

        jComboBox_subject.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_subject.setModel(new javax.swing.DefaultComboBoxModel<>());

        jFormattedTextField_limitTime.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField_limitTime.setMinimumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField_limitTime.setPreferredSize(new java.awt.Dimension(250, 40));

        jButton_file.setText("CHỌN FILE");
        jButton_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_fileActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("NHÓM CÂU HỎI");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Time Limit");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_file, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jFormattedTextField_limitTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jFormattedTextField_examTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_examTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_subject, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextField_limitTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton_file, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_submit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(262, 262, 262))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_cancelActionPerformed

    // Lấy đường dẫn khi thêm file
    private void jButton_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_fileActionPerformed
        JFileChooser fc = new JFileChooser();
        File f;
        fc.showOpenDialog(null);
        f = fc.getSelectedFile();
        path = f.getAbsolutePath();
    }//GEN-LAST:event_jButton_fileActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.plaf.DatePickerAddon datePickerAddon1;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_file;
    private javax.swing.JButton jButton_submit;
    private javax.swing.JComboBox<String> jComboBox_subject;
    private javax.swing.JFormattedTextField jFormattedTextField_examTitle;
    private javax.swing.JFormattedTextField jFormattedTextField_limitTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
