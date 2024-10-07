/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.ExecucaoMalhaController;

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

    public Carro(EstradaCelula estrada, ExclusaoMutuaTipo exclusaoMutuaTipo, ExecucaoMalhaController controller){
        this.velocidade = r.nextInt(500) + 500;
        this.estrada = estrada;
        this.exclusaoMutuaTipo = exclusaoMutuaTipo;
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(velocidade);
            while (!estrada.isSaida()) {
                // Verificar a célula à frente (baseado na direção do carro)
                if(estrada.getProximaEstrada(estrada.getDirecao()).isCruzamento()){
                    percorrerCruzamento();
                } else if (estrada.isProximaCelulaLivre()) {
                    // Mover carro para a próxima célula
                    moverParaProximaCelula();
                    // Atualizar a interface gráfica aqui (pintar célula)
                    atualizarInterfaceGrafica();
                }
                Thread.sleep(velocidade);
            }
            if (estrada.isSaida()){
                removerCarroMalha();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void removerCarroMalha() {
        estrada.setCarro(null);
        atualizarInterfaceGrafica();

        this.interrupt();
        controller.removerCarroMalha(this);
    }

    private void percorrerCruzamento() throws InterruptedException {
        // Obter a próxima estrada, que é a primeira célula do cruzamento
        EstradaCelula primeiraEstradaCruzamento = estrada.getProximaEstrada(estrada.getDirecao());

        // Verificar se a próxima estrada é realmente um cruzamento antes de continuar
        if (primeiraEstradaCruzamento.isCruzamento()) {
            // Obter o caminho completo do cruzamento
            List<EstradaCelula> cruzamentoEstradas = primeiraEstradaCruzamento.getCruzamentos();

            System.out.println("cruzamento estradas");

            for (EstradaCelula e : cruzamentoEstradas){
                System.out.println(e.toString());
            }
            System.out.println("fim");

            // Adicionar a primeira célula do cruzamento à lista
            cruzamentoEstradas.add(0, primeiraEstradaCruzamento);


//            System.out.println("Estradas do cruzamento:");
            for (EstradaCelula e: cruzamentoEstradas) {
//                System.out.println(e.toString());
                moverParaCelula(e);
                Thread.sleep(this.velocidade);
            }
//            System.out.println("Fim do cruzamento");
        }

    }


    private void moverParaProximaCelula() {
        EstradaCelula proximaEstrada = estrada.getProximaEstrada(estrada.getDirecao());

        // Atualiza a célula de origem (remove o carro da célula antiga)
       atualizarInterfaceGrafica();

        // Mover o carro para a próxima célula
        estrada.setCarro(null);
        proximaEstrada.setCarro(this);
        estrada = proximaEstrada;
        System.out.println("Carro"+this.toString()+" agora está na estrada:" +this.estrada.toString());
        // Atualiza a célula de destino (mostra o carro na nova célula)
       atualizarInterfaceGrafica();
    }

    private void moverParaCelula(EstradaCelula est){
        atualizarInterfaceGrafica();

        estrada.setCarro(null);
        est.setCarro(this);
        estrada = est;
        System.out.println("Carro"+this.toString()+" agora está na estrada:" +this.estrada.toString());
        atualizarInterfaceGrafica();
    }


    public void atualizarInterfaceGrafica() {
        // Atualizar a célula onde o carro está (pintar o carro)
        estrada.getMalha().fireTableCellUpdated(estrada.getLin(), estrada.getCol());
    }

    @Override
    public String toString() {
        return "Carro{" +
                "velocidade=" + velocidade +
                '}';
    }
}
