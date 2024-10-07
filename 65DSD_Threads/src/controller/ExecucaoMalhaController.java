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

/**
 *
 * @author Pichau
 */
public class ExecucaoMalhaController extends Thread {

    private ExecucaoMalha telaExecucao;
    private String malhaSelecionada;
    private MalhaTableModel malhaTableModel;
    private int qtdVeiculos;
    private List<Carro> veiculosMalha;
    private int intervalo;
    private Random random;
    private boolean simulacaoAtiva;
    private ExclusaoMutuaTipo exclusaoMutuaTipo;

    public ExecucaoMalhaController(ExecucaoMalha telaExecucao, String malhaSelecionada, ExclusaoMutuaTipo exclusaoMutuaTipo) {
        this.telaExecucao = telaExecucao;
        this.malhaSelecionada = malhaSelecionada;
        inicializarTabela();
        this.veiculosMalha = new ArrayList<>();
        this.random = new Random();
        this.exclusaoMutuaTipo = exclusaoMutuaTipo;
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

    public void acaoIniciarSimulacao() {
        this.qtdVeiculos = telaExecucao.getQtdVeiculos();
        this.intervalo = telaExecucao.getIntervalo();

        List<EstradaCelula> entradas = malhaTableModel.getPontosDeEntrada();

        if (!(veiculosMalha.size() == qtdVeiculos)) {
            simulacaoAtiva = true;
            for (int i = 0; i < qtdVeiculos && simulacaoAtiva; i++) {
                EstradaCelula estradaEntrada = entradas.get(random.nextInt(entradas.size()));

                try {
                    Carro carro = new Carro(estradaEntrada, exclusaoMutuaTipo, this);
                    estradaEntrada.setCarro(carro);
                    veiculosMalha.add(carro);

                    // Atualiza a célula para exibir o carro
                    estradaEntrada.getMalha().fireTableCellUpdated(estradaEntrada.getLin(), estradaEntrada.getCol());


                    carro.start();
                    carro.atualizarInterfaceGrafica();
                    sleepProximoCarro();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // como fazer para esperar o intervalo de inserção
            }
        }
    }

    @Override
    public void run() {
        this.acaoIniciarSimulacao();
    }

    private void sleepProximoCarro() throws InterruptedException {
        int tempoSleep = 10;
        if (this.intervalo > 0) {
            tempoSleep = intervalo * 1000;
        }
        sleep(tempoSleep);
    }

    public void acaoEncerrarInsercao(){

    }

    public void acaoEncerrarSimulacao(){
        simulacaoAtiva = false;

        for (Carro carro : veiculosMalha) {
            carro.interrupt();
        }
    }

    public void acaoAlterarQtdVeiculos(){

    }

    public void acaoAlterarIntervalo(){

    }

    private void inicializarBotoes() {
        telaExecucao.adicionarAcaoBotaoIniciarSimulacao(acao -> this.start());
        telaExecucao.adicionarAcaoBotaoEncerrarInsercao(acao -> acaoEncerrarInsercao());
        telaExecucao.adicionarAcaoBotaoEncerrarSimulacao(acao -> acaoEncerrarSimulacao());
        telaExecucao.adicionarAcaoSpinnerQtdVeiculos(acao -> acaoAlterarQtdVeiculos());
        telaExecucao.adicionarAcaoSpinnerIntervalo(acao -> acaoAlterarIntervalo());
    }

}
