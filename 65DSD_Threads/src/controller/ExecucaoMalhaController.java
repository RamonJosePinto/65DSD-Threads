/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Carro;
import model.EstradaCelula;
import model.ExclusaoMutuaTipo;
import model.MalhaTableModel;
import view.ExecucaoMalha;

import static java.lang.Thread.sleep;
import view.SelecaoMalha;

/**
 *
 * @author RamonJoseP
 */
public class ExecucaoMalhaController {

    private ExecucaoMalha telaExecucao;
    private String malhaSelecionada;
    private MalhaTableModel malhaTableModel;
    private int qtdVeiculos;
    private List<Carro> veiculosMalha;
    private int intervalo;
    private Random random;
    private boolean simulacaoAtiva;
    private ExclusaoMutuaTipo exclusaoMutuaTipo;
    private GeradorCarro geradorCarro;

    public ExecucaoMalhaController(ExecucaoMalha telaExecucao, String malhaSelecionada, ExclusaoMutuaTipo exclusaoMutuaTipo) {
        this.telaExecucao = telaExecucao;
        this.malhaSelecionada = malhaSelecionada;
        inicializarTabela();
        this.veiculosMalha = new ArrayList<>();
        this.random = new Random();
        this.exclusaoMutuaTipo = exclusaoMutuaTipo;
        this.geradorCarro = null;
    }


    public void inicializarTabela() {
        try {
            setMalhaSelecionada(malhaSelecionada);
            malhaTableModel = new MalhaTableModel(malhaSelecionada);
            telaExecucao.setTableModel(malhaTableModel);

            inicializarBotoes();
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


        telaExecucao.setTableModel(malhaTableModel);
        telaExecucao.getTableMalha().setTableHeader(null);
    }

    public void removerCarroMalha(Carro carro){
        this.veiculosMalha.remove(carro);
    }

    public List<Carro> getVeiculosMalha() {
        return veiculosMalha;
    }

    public ExclusaoMutuaTipo getExclusaoMutuaTipo() {
        return exclusaoMutuaTipo;
    }

    public void acaoIniciarSimulacao() {
        this.qtdVeiculos = telaExecucao.getQtdVeiculos();
        this.intervalo = telaExecucao.getIntervalo();

        List<EstradaCelula> entradas = malhaTableModel.getPontosDeEntrada();

        this.geradorCarro = new GeradorCarro(this, entradas, qtdVeiculos, intervalo);
        this.geradorCarro.start();
    }


    public void acaoEncerrarInsercao(){
        this.geradorCarro.interrupt();
    }

    public void acaoEncerrarSimulacao(){
        if (this.geradorCarro != null) this.geradorCarro.interrupt();
        
        for (Carro carro : veiculosMalha) {
            carro.interrupt();
        }
        
        SelecaoMalhaController selecaoMalhaController = new SelecaoMalhaController(new SelecaoMalha());
        selecaoMalhaController.exibirTela();
        fecharTela();
    }

    public void acaoAlterarQtdVeiculos(){

    }

    public void acaoAlterarIntervalo(){

    }

    private void inicializarBotoes() {
        telaExecucao.adicionarAcaoBotaoIniciarSimulacao(acao -> acaoIniciarSimulacao());
        telaExecucao.adicionarAcaoBotaoEncerrarInsercao(acao -> acaoEncerrarInsercao());
        telaExecucao.adicionarAcaoBotaoEncerrarSimulacao(acao -> acaoEncerrarSimulacao());
        telaExecucao.adicionarAcaoSpinnerQtdVeiculos(acao -> acaoAlterarQtdVeiculos());
        telaExecucao.adicionarAcaoSpinnerIntervalo(acao -> acaoAlterarIntervalo());
    }

}
