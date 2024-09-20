/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JOptionPane;
import view.ExecucaoMalha;
import view.SelecaoMalha;
/**
 *
 * @author Pichau
 */
public class SelecaoMalhaController {
    private SelecaoMalha telaSelecao;
    private String malhaSelecionada;

    public SelecaoMalhaController(SelecaoMalha tela) {
        this.telaSelecao = tela;
        this.telaSelecao.getRadioMalha1().setSelected(true);
        acaoSelecionarMalha(1);
        inicializarBotoes();
    }
    
    
    
    public void exibirTela(){
        telaSelecao.exibirTela();
    }
    
    public void fecharTela(){
        telaSelecao.fecharTela();
    }
    
    public void acaoBotaoConfirmar(){
        ExecucaoMalhaController execucaoMalhaController = new ExecucaoMalhaController(new ExecucaoMalha(), malhaSelecionada);
        execucaoMalhaController.exibirTela();
        fecharTela();
    }
    
    public void acaoSelecionarMalha(int opcao){
          switch (opcao) {
            case 1:
                telaSelecao.redimensionarImagem("/images/malha1.png");
                setMalhaSelecionada("/images/malha1.png");
                break;
            case 2:
                telaSelecao.redimensionarImagem("/images/malha2.png");
                setMalhaSelecionada("/images/malha2.png");
                break;
            case 3:
                telaSelecao.redimensionarImagem("/images/malha3.png");
                setMalhaSelecionada("/images/malha3.png");
                break;
            default:
                break;
        }
    }
    

    private void inicializarBotoes() {
        telaSelecao.adicionarAcaoBotaoConfirmar(acao -> acaoBotaoConfirmar());
        telaSelecao.adicionarAcaoRadioMalha1(acao -> acaoSelecionarMalha(1));
        telaSelecao.adicionarAcaoRadioMalha2(acao -> acaoSelecionarMalha(2));
        telaSelecao.adicionarAcaoRadioMalha3(acao -> acaoSelecionarMalha(3));
    }

    public String getMalhaSelecionada() {
        return malhaSelecionada;
    }

    public void setMalhaSelecionada(String malhaSelecionada) {
        this.malhaSelecionada = malhaSelecionada;
    }
    
    
    
}
