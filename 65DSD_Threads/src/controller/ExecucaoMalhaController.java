/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import javax.swing.ImageIcon;
import view.ExecucaoMalha;
/**
 *
 * @author Pichau
 */
public class ExecucaoMalhaController {
    private ExecucaoMalha telaExecucao;
    private String malhaSelecioanda;

    public ExecucaoMalhaController(ExecucaoMalha telaExecucao, String malhaSelecioanda) {
        this.telaExecucao = telaExecucao;
        this.malhaSelecioanda = malhaSelecioanda;
        
        adicionarAcaoBtnMalhaImagem();
    }
    
    public void exibirTela(){
        telaExecucao.exibirTela();
    }
    
    public void fecharTela(){
        telaExecucao.fecharTela();
    }
    
    public void adicionarAcaoBtnMalhaImagem(){
        ImageIcon icon = new ImageIcon(getClass().getResource(malhaSelecioanda));
         Image image = icon.getImage().getScaledInstance(telaExecucao.getBtnMalhaImagem().getWidth(), telaExecucao.getBtnMalhaImagem().getHeight(), Image.SCALE_SMOOTH);
        telaExecucao.getBtnMalhaImagem().setIcon(new ImageIcon(image));
    }
    
    private void inicializarBotoes() {
        
    }
    
}
