/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.MalhaTableModel;
import view.ExecucaoMalha;

/**
 *
 * @author Pichau
 */
public class ExecucaoMalhaController {

    private ExecucaoMalha telaExecucao;
    private String malhaSelecionada;
    private MalhaTableModel malhaTableModel;

    public ExecucaoMalhaController(ExecucaoMalha telaExecucao, String malhaSelecionada) {
        this.telaExecucao = telaExecucao;
        this.malhaSelecionada = malhaSelecionada;
        inicializarTabela();
    }

    public void inicializarTabela() {
        try {
            setMalhaSelecionada(malhaSelecionada);
            malhaTableModel = new MalhaTableModel(malhaSelecionada);
            telaExecucao.setTableModel(malhaTableModel);  // Atualiza a tabela com o novo modelo
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a malha: " + e.getMessage());
        }
    }

    public void exibirTela() {
        telaExecucao.exibirTela();
    }

    public void fecharTela() {
        telaExecucao.fecharTela();
    }

    public String getMalhaSelecionada() {
        return malhaSelecionada;
    }

    public void setMalhaSelecionada(String malhaSelecionada) {
        this.malhaSelecionada = malhaSelecionada;
    }

    public void setMalhaTableModel(MalhaTableModel malhaTableModel) {
        this.malhaTableModel = malhaTableModel;

        // Define o modelo da tabela
        telaExecucao.setTableModel(malhaTableModel);
        telaExecucao.getTableMalha().setTableHeader(null);
    }

    private void inicializarBotoes() {

    }

}
