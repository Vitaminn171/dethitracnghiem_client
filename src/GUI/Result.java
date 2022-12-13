/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Quoc An
 */
public class Result extends javax.swing.JFrame {

    /**
     * Creates new form Result
     */
    public Result(JSONObject jsonResult) {
        initComponents();
        this.setTitle("Quiz Exam Result");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        

        
        jButton1.putClientProperty( "JButton.buttonType", "roundRect" );
        jButton1.putClientProperty( "JButton.focusWidth", 1 );
        
        jLabel_examinee_data.setText(jsonResult.getString("examinee"));
        jLabel_score_data.setText(String.valueOf(jsonResult.getDouble("score")));
        jLabel_correct_data.setText(String.valueOf(jsonResult.getInt("correct")));
        jLabel_wrong_data.setText(String.valueOf(jsonResult.getInt("wrong")));
        
        jButton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
            // TODO add your handling code here:
            //this.dispose();
            Dashboard dashboard = new Dashboard(jsonResult.getString("examinee"));
            dashboard.setVisible(true);
            getContentPane().setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
        }    catch (Exception ex) {
                 Logger.getLogger(Result.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
      });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel_examinee = new javax.swing.JLabel();
        jLabel_score = new javax.swing.JLabel();
        jLabel_correct = new javax.swing.JLabel();
        jLabel_wrong = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel_examinee_data = new javax.swing.JLabel();
        jLabel_score_data = new javax.swing.JLabel();
        jLabel_correct_data = new javax.swing.JLabel();
        jLabel_wrong_data = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Result");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        jLabel_examinee.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examinee.setText("Examinee :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 0);
        getContentPane().add(jLabel_examinee, gridBagConstraints);

        jLabel_score.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_score.setText("Score :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 0);
        getContentPane().add(jLabel_score, gridBagConstraints);

        jLabel_correct.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_correct.setText("Correct Quiz :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 0);
        getContentPane().add(jLabel_correct, gridBagConstraints);

        jLabel_wrong.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_wrong.setText("Wrong Quiz :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 42, 0, 0);
        getContentPane().add(jLabel_wrong, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("OK");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 27, 18);
        getContentPane().add(jButton1, gridBagConstraints);

        jLabel_examinee_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_examinee_data.setText("Examinee");
        jLabel_examinee_data.setMaximumSize(null);
        jLabel_examinee_data.setMinimumSize(null);
        jLabel_examinee_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 42);
        getContentPane().add(jLabel_examinee_data, gridBagConstraints);

        jLabel_score_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_score_data.setText("score");
        jLabel_score_data.setMaximumSize(null);
        jLabel_score_data.setMinimumSize(null);
        jLabel_score_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 42);
        getContentPane().add(jLabel_score_data, gridBagConstraints);

        jLabel_correct_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_correct_data.setText("correct");
        jLabel_correct_data.setMaximumSize(null);
        jLabel_correct_data.setMinimumSize(null);
        jLabel_correct_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 42);
        getContentPane().add(jLabel_correct_data, gridBagConstraints);

        jLabel_wrong_data.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_wrong_data.setText("wrong");
        jLabel_wrong_data.setMaximumSize(null);
        jLabel_wrong_data.setMinimumSize(null);
        jLabel_wrong_data.setPreferredSize(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 16, 0, 42);
        getContentPane().add(jLabel_wrong_data, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Result().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_correct;
    private javax.swing.JLabel jLabel_correct_data;
    private javax.swing.JLabel jLabel_examinee;
    private javax.swing.JLabel jLabel_examinee_data;
    private javax.swing.JLabel jLabel_score;
    private javax.swing.JLabel jLabel_score_data;
    private javax.swing.JLabel jLabel_wrong;
    private javax.swing.JLabel jLabel_wrong_data;
    // End of variables declaration//GEN-END:variables
}
