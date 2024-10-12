/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ExecucaoMalhaController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pichau
 */
public class Carro extends Thread {
    private Random r = new Random();
    private int velocidade;
    private EstradaCelula estrada;
    private ExclusaoMutuaTipo exclusaoMutuaTipo;
    private ExecucaoMalhaController controller;
    private static final Object cruzamentoMonitor = new Object();
    private static final Object moverEstradaNormal = new Object();

    public Carro(EstradaCelula estrada, ExclusaoMutuaTipo exclusaoMutuaTipo, ExecucaoMalhaController controller){
        this.velocidade = r.nextInt(500) + 500;
        this.estrada = estrada;
        this.exclusaoMutuaTipo = exclusaoMutuaTipo;
        this.controller = controller;
        System.out.println("Exclusao selecionada: "+exclusaoMutuaTipo);
    }

    @Override
    public void run() {
        while (!estrada.isSaida() && !this.isInterrupted()) {

            if (estrada.getProximaEstrada(estrada.getDirecao()).isCruzamento()) {
                try {
                    percorrerCruzamento();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (estrada.isProximaCelulaLivre()) {
                moverParaProximaCelula();
            }

            atualizarInterfaceGrafica();

            try {
                Thread.sleep(velocidade);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (estrada.isSaida()) {
            if (!this.isInterrupted()) this.interrupt();
            removerCarroMalha();
        }
    }

//  private void removerCarroMalha() {
    public void removerCarroMalha() {
        estrada.setCarro(null);

        //this.interrupt();
        controller.removerCarroMalha(this);
        estrada.liberarEstrada();
        atualizarInterfaceGrafica();
    }

    public void monitorPercorrerCruzamento(List<EstradaCelula> cruzamentoEstradas) throws InterruptedException {
        synchronized (cruzamentoMonitor) {
            for (EstradaCelula e : cruzamentoEstradas) {
                moverParaCelula(e, false);
                // TODO: ver melhor forma de resolver isso, talvez retirar a ultima estrada
                // da lista de cruzamentoEstradas, que é a primeira estrada pós cruzamento
                if (e.isCruzamento()) { // Resolver delay duplo ao sair do cruzamento
                    atualizarInterfaceGrafica();
                    Thread.sleep(this.velocidade);
                }
            }
        }
    }

    private void percorrerCruzamento() throws InterruptedException {
        // Obter a próxima estrada, que é a primeira célula do cruzamento
        EstradaCelula primeiraEstradaCruzamento = estrada.getProximaEstrada(estrada.getDirecao());

        // Verificar se a próxima estrada é realmente um cruzamento antes de continuar
        if (primeiraEstradaCruzamento.isCruzamento()) {
            // Obter o caminho completo do cruzamento
            List<EstradaCelula> cruzamentoEstradas = primeiraEstradaCruzamento.getCruzamentos();
            if(exclusaoMutuaTipo == ExclusaoMutuaTipo.MONITOR){
                System.out.println("Realmente entrou no monitor");
                monitorPercorrerCruzamento(cruzamentoEstradas);
            } else {
                List<EstradaCelula> cruzamentosReservados = getCruzamentosReservados(cruzamentoEstradas);

            System.out.println("Carro: "+this.getName()+"cruzamentos: "+cruzamentoEstradas.size()+"reservados: "+cruzamentosReservados.size());

                if (cruzamentoEstradas.size() == cruzamentosReservados.size()) {
                    for (EstradaCelula e : cruzamentoEstradas) {
                        moverParaCelula(e, false);
                        // TODO: ver melhor forma de resolver isso, talvez retirar a ultima estrada
                        // da lista de cruzamentoEstradas, que é a primeira estrada pós cruzamento
                        if (e.isCruzamento()) { // Resolver delay duplo ao sair do cruzamento
                            atualizarInterfaceGrafica();
                            Thread.sleep(this.velocidade);
                        }
                    }
                }
            }
        }
    }

    private List<EstradaCelula> getCruzamentosReservados(List<EstradaCelula> cruzamentoEstradas) {
        ArrayList<EstradaCelula> cruzamentosReservados = new ArrayList<>();
        for (EstradaCelula cruzamentoTentaReservar : cruzamentoEstradas) {
            if (cruzamentoTentaReservar.tentarEntrarEstrada()) {
                cruzamentosReservados.add(cruzamentoTentaReservar);
            } else {
                this.liberarEstradaList(cruzamentosReservados);
                break;
            }
        }
        return cruzamentosReservados;
    }

    private void liberarEstradaList(ArrayList<EstradaCelula> cruzamentosReservados) {
        for (EstradaCelula estrada : cruzamentosReservados) {
            estrada.liberarEstrada();
        }
    }


    private void moverParaProximaCelula() {
        EstradaCelula proximaEstrada = estrada.getProximaEstrada(estrada.getDirecao());
//        if(proximaEstrada.tentarEntrarEstrada()){
        moverParaCelula(proximaEstrada, true);
//        }
    }

    private void moverParaCelula(EstradaCelula est, boolean testar){
        if (exclusaoMutuaTipo == ExclusaoMutuaTipo.MONITOR){
            monitorMoverParaCelular(est);
        } else {

            boolean reservado = false;
            if (testar) {
                do {
                    if (est.tentarEntrarEstrada()) {
                        reservado = true;
                    }
                } while (!reservado);
            }

            estrada.setCarro(null);
            est.setCarro(this);
            estrada.liberarEstrada();
            estrada = est;
            //        atualizarInterfaceGrafica();
        }
    }

    private void monitorMoverParaCelular(EstradaCelula est) {
        synchronized (moverEstradaNormal) {
            estrada.setCarro(null);
            est.setCarro(this);
            estrada = est;
        }
    }


    public void atualizarInterfaceGrafica() {
        // Atualizar a célula onde o carro está (pintar o carro)
        estrada.getMalha().fireTableCellUpdated(estrada.getLin(), estrada.getCol());
        estrada.getMalha().fireTableDataChanged();
    }

    @Override
    public String toString() {
        return "Carro{" +
                "velocidade=" + velocidade +
                '}';
    }
}
