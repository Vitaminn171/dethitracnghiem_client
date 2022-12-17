package GUI;

import BLL.Controller;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class EditUserInfor extends javax.swing.JFrame {
    ButtonGroup G;
    public boolean flag = false;

    public EditUserInfor(JSONObject json) throws ParseException {
        initComponents();
        this.setTitle("Quiz Exam");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jFormattedTextField_username.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField_username.putClientProperty("JTextField.placeholderText", "Email");

        jFormattedTextField_fullname.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField_fullname.putClientProperty("JTextField.placeholderText", "Fullname");

        jXDatePicker1.putClientProperty("JXDatePicker.roundRect", true);
        jXDatePicker1.putClientProperty("JXDatePicker.placeholderText", "date");
        jXDatePicker1.setFormats("dd/MM/yyyy");

        jButton_cancel.putClientProperty("JButton.buttonType", "roundRect");
        jButton_submit.putClientProperty("JButton.buttonType", "roundRect");

        G = new ButtonGroup();
        G.add(jRadioButton_male);
        G.add(jRadioButton_female);

        // Set data
        jFormattedTextField_username.setText(json.getString("username"));
        jFormattedTextField_username.setEnabled(false);
        jFormattedTextField_fullname.setText(json.getString("fullname"));

        String sDate1 = json.getString("birth");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(date);
        date = new SimpleDateFormat("dd/MM/yyyy").parse(formattedDate);
        jXDatePicker1.setDate(date);

        if (json.getBoolean("gender")) {
            jRadioButton_male.setSelected(true);
        } else {
            jRadioButton_female.setSelected(true);
        }

        // Lấy thông tin chỉnh sửa và tiến hành kiểm tra dữ liệu đầu vào
        jButton_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = jFormattedTextField_username.getText();
                String fullname = jFormattedTextField_fullname.getText();
                Date birthDate = jXDatePicker1.getDate(); // Get birthday to check it must before today
                boolean gender = false;
                if (jRadioButton_male.isSelected()) {
                    gender = true; // Male Selected
                } else if (jRadioButton_female.isSelected()) {
                    gender = false; // Female Selected
                }
                checkValidate(username, fullname, birthDate, gender);
            }
        });

        jButton_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Dashboard(json.getString("username")).setVisible(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        datePickerAddon1 = new org.jdesktop.swingx.plaf.DatePickerAddon();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField_username = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField_fullname = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton_male = new javax.swing.JRadioButton();
        jRadioButton_female = new javax.swing.JRadioButton();
        jButton_cancel = new javax.swing.JButton();
        jButton_submit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 400));
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDIT ACCOUNT");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(30, 20, 20, 20);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Email");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 16, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jFormattedTextField_username.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField_username.setMinimumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField_username.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jFormattedTextField_username, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Full name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 16, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jFormattedTextField_fullname.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jFormattedTextField_fullname.setMinimumSize(new java.awt.Dimension(120, 40));
        jFormattedTextField_fullname.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jFormattedTextField_fullname, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Birthday");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 16, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        jXDatePicker1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jXDatePicker1.setMinimumSize(new java.awt.Dimension(120, 40));
        jXDatePicker1.setPreferredSize(new java.awt.Dimension(250, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jXDatePicker1, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Gender");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 16, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);

        jRadioButton_male.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jRadioButton_male.setText("Male");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jRadioButton_male, gridBagConstraints);

        jRadioButton_female.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jRadioButton_female.setText("Female");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        jPanel1.add(jRadioButton_female, gridBagConstraints);

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 30, 0);
        jPanel1.add(jButton_cancel, gridBagConstraints);

        jButton_submit.setBackground(new java.awt.Color(34, 133, 225));
        jButton_submit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_submit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_submit.setText("Submit");
        jButton_submit.setBorderPainted(false);
        jButton_submit.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 30, 0);
        jPanel1.add(jButton_submit, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton_cancelActionPerformed
    
    // Kiểm tra thông tin cập nhật thông tin cá nhân (đầu vào)
    private void checkValidate(String username, String fullname, Date birthDate, boolean gender) {
        try {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formater.format(jXDatePicker1.getDate()); // Get string date 

            if (fullname.length() != 0
                    || username.length() != 0
                    || birthDate.toString().length() != 0
                    || G.getSelection().isSelected() != false) {
                Date currentDate = new Date();

                if (!Controller.validateEmail(username)) {
                    JOptionPane.showMessageDialog(this, "Invalid email!");
                }
                
                if (!birthDate.before(currentDate)) {
                    JOptionPane.showMessageDialog(this, "Birthday must before " + dateString + "(today)!");
                } else {
                    Edit(username, fullname, dateString, gender);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Do not leave fields blank!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Do not input invalid data!");
        }
    }

    // Cập nhật thông tin cá nhân
    private void Edit(String username, String fullname, String dateString, boolean gender) throws Exception {

        // Gửi yêu cầu cập nhật thông tin cá nhân
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("fullname", fullname);
        jsonSend.put("birth", dateString);
        jsonSend.put("gender", gender);
        jsonSend.put("func", "editUserInfor");
        jsonSend.put("status", true);
        Controller controller = new Controller();

        try {
            String dataReceive = controller.SendReceiveData(jsonSend.toString());
            JSONObject jsonReceive = new JSONObject(dataReceive);
            if (jsonReceive.getBoolean("status")) {
                JOptionPane.showMessageDialog(this, "Change information success!");
                this.dispose();
                Dashboard dashboard = new Dashboard(jsonReceive);
                dashboard.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Change information failed!");
            }
        } catch (IOException ex) {
            Logger.getLogger(EditUserInfor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.jdesktop.swingx.plaf.DatePickerAddon datePickerAddon1;
    private javax.swing.JButton jButton_cancel;
    private javax.swing.JButton jButton_submit;
    private javax.swing.JFormattedTextField jFormattedTextField_fullname;
    private javax.swing.JFormattedTextField jFormattedTextField_username;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_female;
    private javax.swing.JRadioButton jRadioButton_male;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    // End of variables declaration//GEN-END:variables

}
