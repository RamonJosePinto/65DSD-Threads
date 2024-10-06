/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.MalhaCellRenderer;
import model.MalhaTableModel;

/**
 *
 * @author Pichau
 */
public class SelecaoMalha extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public SelecaoMalha() {
        initComponents();
        ScrollPaneMalha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneMalha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableMalha.setFillsViewportHeight(true);
        tableMalha.setDefaultRenderer(Object.class, new MalhaCellRenderer());

    }

    public void adicionarAcaoBotaoConfirmar(ActionListener acao) {
        buttonConfirm.addActionListener(acao);
    }

    public void adicionarAcaoRadioMalha1(ActionListener acao) {
        radioMalha1.addActionListener(acao);
    }

    public void adicionarAcaoRadioMalha2(ActionListener acao) {
        radioMalha2.addActionListener(acao);
    }

    public void adicionarAcaoRadioMalha3(ActionListener acao) {
        radioMalha3.addActionListener(acao);
    }

    public void adicionarAcaoRadioExclusaoMutua1(ActionListener acao) {rbExclusaoMutua1.addActionListener(acao);}

    public void adicionarAcaoRadioExclusaoMutua2(ActionListener acao) {rbExclusaoMutua2.addActionListener(acao);}

    public JTable getTableMalha() {
        return tableMalha;
    }

    public JRadioButton getRadioMalha1() {
        return radioMalha1;
    }

    public void exibirTela() {
        setVisible(true);
    }

    public void fecharTela() {
        setVisible(false);
    }

    public void setDefaultRadioButtonSelected(){
        rbExclusaoMutua1.setSelected(true);
    }

    public void setTableModel(MalhaTableModel malhaTableModel) {
    tableMalha.setModel(malhaTableModel);

    tableMalha.setTableHeader(null);
    ajustarTamanhoCelulas();
}

// Método para ajustar dinamicamente o tamanho das células
private void ajustarTamanhoCelulas() {
    int larguraDisponivel = Math.min(ScrollPaneMalha.getWidth(), 650);
    int alturaDisponivel = Math.min(ScrollPaneMalha.getHeight(), 650);

    // Número de linhas e colunas
    int numLinhas = tableMalha.getRowCount();
    int numColunas = tableMalha.getColumnCount();

    // Calcular a largura e altura das células
    int larguraColuna = larguraDisponivel / numColunas;
    int alturaLinha = alturaDisponivel / numLinhas;

    // Redimensionar as colunas
    for (int i = 0; i < numColunas; i++) {
        tableMalha.getColumnModel().getColumn(i).setPreferredWidth(larguraColuna);
    }

    // Redimensionar as linhas
    tableMalha.setRowHeight(alturaLinha);

    // Ajustar para que a tabela preencha todo o espaço do viewport
    tableMalha.setFillsViewportHeight(true);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        exclusaoMutuaGroup = new javax.swing.ButtonGroup();
        buttonConfirm = new javax.swing.JButton();
        radioMalha1 = new javax.swing.JRadioButton();
        radioMalha2 = new javax.swing.JRadioButton();
        radioMalha3 = new javax.swing.JRadioButton();
        ScrollPaneMalha = new javax.swing.JScrollPane();
        tableMalha = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rbExclusaoMutua1 = new javax.swing.JRadioButton();
        rbExclusaoMutua2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonConfirm.setText("Confirmar");
        buttonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioMalha1);
        radioMalha1.setText("Malha1");
        radioMalha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMalha1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioMalha2);
        radioMalha2.setText("Malha2");
        radioMalha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMalha2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioMalha3);
        radioMalha3.setText("Malha3");

        tableMalha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableMalha.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableMalha.setTableHeader(null);
        ScrollPaneMalha.setViewportView(tableMalha);

        jLabel1.setText("Malha a ser utilizada");

        jLabel2.setText("Sistema de exclusão mutua a ser usado");

        exclusaoMutuaGroup.add(rbExclusaoMutua1);
        rbExclusaoMutua1.setText("Semaforos");
        rbExclusaoMutua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbExclusaoMutua1ActionPerformed(evt);
            }
        });

        exclusaoMutuaGroup.add(rbExclusaoMutua2);
        rbExclusaoMutua2.setText("Monitores");
        rbExclusaoMutua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbExclusaoMutua2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(radioMalha1)
                            .addGap(41, 41, 41)
                            .addComponent(radioMalha2)
                            .addGap(43, 43, 43)
                            .addComponent(radioMalha3))
                        .addComponent(ScrollPaneMalha, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(352, 352, 352)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbExclusaoMutua1)
                                .addGap(18, 18, 18)
                                .addComponent(rbExclusaoMutua2))
                            .addComponent(jLabel2))))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(ScrollPaneMalha, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioMalha1)
                    .addComponent(radioMalha2)
                    .addComponent(radioMalha3)
                    .addComponent(rbExclusaoMutua1)
                    .addComponent(rbExclusaoMutua2))
                .addGap(18, 18, 18)
                .addComponent(buttonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioMalha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMalha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioMalha1ActionPerformed

    private void radioMalha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMalha2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioMalha2ActionPerformed

    private void buttonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonConfirmActionPerformed

    private void rbExclusaoMutua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbExclusaoMutua1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbExclusaoMutua1ActionPerformed

    private void rbExclusaoMutua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbExclusaoMutua2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbExclusaoMutua2ActionPerformed

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
            java.util.logging.Logger.getLogger(SelecaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelecaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelecaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelecaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelecaoMalha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneMalha;
    private javax.swing.JButton buttonConfirm;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup exclusaoMutuaGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton radioMalha1;
    private javax.swing.JRadioButton radioMalha2;
    private javax.swing.JRadioButton radioMalha3;
    private javax.swing.JRadioButton rbExclusaoMutua1;
    private javax.swing.JRadioButton rbExclusaoMutua2;
    private javax.swing.JTable tableMalha;
    // End of variables declaration//GEN-END:variables
}
