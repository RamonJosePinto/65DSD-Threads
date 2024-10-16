/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeListener;

import model.MalhaTableModel;

import java.awt.event.ActionListener;

/**
 *
 * @author RamonJoseP
 */
public class ExecucaoMalha extends javax.swing.JFrame {

    /**
     * Creates new form ExecucaoMalha
     */
    public ExecucaoMalha() {
        initComponents();
        ScrollPaneMalha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollPaneMalha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tableMalha.setFillsViewportHeight(true);
        tableMalha.setDefaultRenderer(Object.class, new MalhaCellRenderer());
    }

    public void adicionarAcaoBotaoIniciarSimulacao(ActionListener acao){
        jbIniciarSimulacao.addActionListener(acao);
    }

    public void adicionarAcaoBotaoEncerrarInsercao(ActionListener acao){
        jbEncerrarInsercao.addActionListener(acao);
    }

    public void adicionarAcaoBotaoEncerrarSimulacao(ActionListener acao){
        jbEncerrarSimulacao.addActionListener(acao);
    }

    public void adicionarAcaoSpinnerQtdVeiculos(ChangeListener acao){
        jsQtdVeiculos.addChangeListener(acao);
    }
    public void adicionarAcaoSpinnerIntervalo(ChangeListener acao){
        jsIntervalo.addChangeListener(acao);
    }

    public void setTableModel(MalhaTableModel malhaTableModel) {
        tableMalha.setModel(malhaTableModel);

        // Remover o cabeçalho da tabela
        tableMalha.setTableHeader(null);

        // Ajustar para preencher todo o espaço disponível no JScrollPane
        ajustarTamanhoCelulas();
    }

    public JTable getTableMalha() {
        return tableMalha;
    }

    public int getQtdVeiculos(){
        return (Integer) jsQtdVeiculos.getValue();
    }

    public int getIntervalo(){
        return (Integer) jsIntervalo.getValue();
    }

    // Método para ajustar dinamicamente o tamanho das células
    private void ajustarTamanhoCelulas() {
        // Definir o tamanho desejado do JScrollPane (exemplo de 650x650 máximo)
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

    public void exibirTela() {
        setVisible(true);
    }

    public void fecharTela() {
        setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jsQtdVeiculos = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jsIntervalo = new javax.swing.JSpinner();
        jbIniciarSimulacao = new javax.swing.JButton();
        jbEncerrarInsercao = new javax.swing.JButton();
        jbEncerrarSimulacao = new javax.swing.JButton();
        ScrollPaneMalha = new javax.swing.JScrollPane();
        tableMalha = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Quantidade de véiculos:");

        jLabel2.setText("Intervalo de inserção (ms):");

        jbIniciarSimulacao.setText("Iniciar simulação");

        jbEncerrarInsercao.setText("Encerrar inserção");

        jbEncerrarSimulacao.setText("Encerrar simulação");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollPaneMalha, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addComponent(jsQtdVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jbEncerrarSimulacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbEncerrarInsercao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbIniciarSimulacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jsIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ScrollPaneMalha, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jsQtdVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jsIntervalo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbIniciarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbEncerrarInsercao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbEncerrarSimulacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ExecucaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExecucaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExecucaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExecucaoMalha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExecucaoMalha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollPaneMalha;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jbEncerrarInsercao;
    private javax.swing.JButton jbEncerrarSimulacao;
    private javax.swing.JButton jbIniciarSimulacao;
    private javax.swing.JSpinner jsIntervalo;
    private javax.swing.JSpinner jsQtdVeiculos;
    private javax.swing.JTable tableMalha;
    // End of variables declaration//GEN-END:variables
}
