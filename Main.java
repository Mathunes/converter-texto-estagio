package converter_texto;

import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {


    public Main() {
        super("Converter texto - Alterados");
        initComponents();
        setLocationRelativeTo(null);
        setIcon();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        convert = new javax.swing.JButton();
        radioSamu = new javax.swing.JRadioButton();
        radioMainframe = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);
        textArea.getAccessibleContext().setAccessibleDescription("");

        convert.setText("Converter");
        convert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertActionPerformed(evt);
            }
        });

        radioSamu.setText("Relatório SAMU");
        radioSamu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSamuActionPerformed(evt);
            }
        });

        radioMainframe.setText("Relatório Mainframe");
        radioMainframe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMainframeActionPerformed(evt);
            }
        });

        jLabel1.setText("Fonte dos dados:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(convert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioMainframe, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioSamu, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(radioMainframe)
                    .addComponent(radioSamu))
                .addGap(8, 8, 8)
                .addComponent(convert)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void convertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertActionPerformed
        
        if (radioMainframe.isSelected()) {
        
            String textInput = textArea.getText().trim();
            textArea.setText("");

            for (int i = 0; i < textInput.length(); i++) {

                String userId = "";
                String userInfo = "";
                String lotacao = "";

                while ((textInput.charAt(i) != '\n') && (i < textInput.length()-1)) {
                    userInfo += textInput.charAt(i);
                    i++;
                }

                for (int j = 0; j < 11; j++) {
                    if (userInfo.charAt(j) != ' ') {
                        userId += userInfo.charAt(j);
                    }
                }

                for (int j = 61; j < userInfo.length(); j++) {
                    lotacao += userInfo.charAt(j);
                }

                textArea.setText(textArea.getText() + "- username: " + userId + "\n  org_unit: " + lotacao + "\n");
            }
        } else if (radioSamu.isSelected()) {
            
            String textInput = textArea.getText().trim();
            textArea.setText("");
            String userId = "";
            
            if (textInput.contains("Usuários desligados:")) {
                int indiceDesligado = textInput.indexOf("Usuários desligados:");
                
                String infoDesligado = "";
                
                for (int i = indiceDesligado; i < textInput.length(); i++) {
                    infoDesligado += textInput.charAt(i);
                }
                
                textInput = textInput.replace(infoDesligado, "");
                
                System.out.println(textInput);
            }
            
            
            
            
            int i = textInput.indexOf("Usuários alterados:") + "Usuários alterados:\n".length();
            
            for (; i < textInput.length(); i++) {
                
                String userInfo = "";
                String lotacao = "";
                boolean erroLotacao = false;
                
                while ((textInput.charAt(i) != '\n') && (i < textInput.length()-1)) {
                    userInfo += textInput.charAt(i);
                    i++;
                }
                
                if (userInfo.contains("User ID.............. ")) {
                    userId = "";
                    userId = userInfo.replace("User ID.............. ", "").toUpperCase();
                }
                
                if (userInfo.contains("Lotação.............. ")) {
                    erroLotacao = true;
                    
                    lotacao = userInfo.replace("Lotação.............. ", "");
                    
                    int j = lotacao.indexOf("->");
                    
                    String aux = "";
                    
                    for (int k = 0; k < j; k++) {
                        aux += lotacao.charAt(k);
                    }
                    
                    lotacao = lotacao.replace(aux + "-> ", "");
                    
                    if (lotacao.contains("/")) {

                        int firstBar = lotacao.indexOf('/');
                        int lastBar = lotacao.lastIndexOf('/');

                        aux = "";

                        for (int k = firstBar; k <= lastBar; k++) {
                            aux += lotacao.charAt(k);
                        }

                        lotacao = lotacao.replace(aux, "-");

                    }
                    
                }
                
                if (erroLotacao) {
                    textArea.setText(textArea.getText() + "- username: " + userId + "\n  org_unit: " + lotacao + "\n");
                    erroLotacao = false;
                }
                
            }
            
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma fonte de dados!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_convertActionPerformed

    private void radioSamuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSamuActionPerformed
        radioMainframe.setSelected(false);
    }//GEN-LAST:event_radioSamuActionPerformed

    private void radioMainframeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMainframeActionPerformed
        radioSamu.setSelected(false);
    }//GEN-LAST:event_radioMainframeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton convert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton radioMainframe;
    private javax.swing.JRadioButton radioSamu;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
    }
}
