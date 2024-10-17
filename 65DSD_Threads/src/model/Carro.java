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
 * @author RamonJoseP
 */
public class Carro extends Thread {
    private Random random = new Random();
    private int velocidade;
    private EstradaCelula estrada;
    private ExclusaoMutuaTipo exclusaoMutuaTipo;
    private ExecucaoMalhaController controller;
    private static final Object cruzamentoMonitor = new Object();
    private static final Object moverEstradaNormal = new Object();

    public Carro(EstradaCelula estrada, ExclusaoMutuaTipo exclusaoMutuaTipo, ExecucaoMalhaController controller){
        this.velocidade = random.nextInt(500) + 500;
        this.estrada = estrada;
        this.exclusaoMutuaTipo = exclusaoMutuaTipo;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(velocidade);
            while (!estrada.isSaida() && !this.isInterrupted()) {

                if (estrada.getProximaEstrada().isCruzamento()) {
                    try {
                        percorrerCruzamento();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (estrada.isProximaCelulaLivre()) {
                    moverParaCelula(estrada.getProximaEstrada(), true);
                }

                atualizarInterfaceGrafica();

                Thread.sleep(velocidade);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (estrada.isSaida()) {
            if (!this.isInterrupted()) this.interrupt();
            removerCarroMalha();
        }
    }

    public void removerCarroMalha() {
        estrada.setCarro(null);
        controller.removerCarroMalha(this);
        estrada.liberarEstrada();
        atualizarInterfaceGrafica();
    }

    public void monitorPercorrerCruzamento(List<EstradaCelula> estradasAtravessarCruzamento) throws InterruptedException {
        synchronized (cruzamentoMonitor) {
            System.out.println("Ele ta no cruzamento monitor");
            for (EstradaCelula e : estradasAtravessarCruzamento) {
                moverParaCelula(e, false);
                if (e.isCruzamento()) {
                    atualizarInterfaceGrafica();
                    Thread.sleep(this.velocidade);
                }
            }
        }
    }

    private void percorrerCruzamento() throws InterruptedException {
        EstradaCelula primeiraEstradaCruzamento = estrada.getProximaEstrada();

        if (primeiraEstradaCruzamento.isCruzamento()) {
            List<EstradaCelula> estradasAtravessarCruzamento = primeiraEstradaCruzamento.getListaEstradaAtrevessarCruzamento();
            if(exclusaoMutuaTipo == ExclusaoMutuaTipo.MONITOR){
                monitorPercorrerCruzamento(estradasAtravessarCruzamento);
            } else {
                List<EstradaCelula> estradasCruzamentoReservados = getCruzamentosReservados(estradasAtravessarCruzamento);

                if (estradasAtravessarCruzamento.size() == estradasCruzamentoReservados.size()) {
                    for (EstradaCelula e : estradasAtravessarCruzamento) {
                        moverParaCelula(e, false);
                        if (e.isCruzamento()) {
                            atualizarInterfaceGrafica();
                            Thread.sleep(this.velocidade);
                        }
                    }
                }
            }
        }
    }

    private List<EstradaCelula> getCruzamentosReservados(List<EstradaCelula> estradasAtravessarCruzamento) {
        ArrayList<EstradaCelula> cruzamentosReservados = new ArrayList<>();
        for (EstradaCelula cruzamentoTentaReservar : estradasAtravessarCruzamento) {
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

    private void moverParaCelula(EstradaCelula est, boolean testar){
        if (exclusaoMutuaTipo == ExclusaoMutuaTipo.MONITOR){
            monitorMoverParaCelula(est);
        } else {
            boolean reservado = false;
            if (testar) {
                try {
                    do {
                        if (est.tentarEntrarEstrada()) {
                            reservado = true;
                        } else {
                                sleep(random.nextInt(500)); // Solução funcional jantar dos filosofos
                        }
                    } while (!reservado);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            estrada.setCarro(null);
            est.setCarro(this);
            estrada.liberarEstrada();
            estrada = est;
        }
    }

    private void monitorMoverParaCelula(EstradaCelula est) {
        synchronized (moverEstradaNormal) {
            estrada.setCarro(null);
            est.setCarro(this);
            estrada = est;
        }
    }


    public void atualizarInterfaceGrafica() {
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
