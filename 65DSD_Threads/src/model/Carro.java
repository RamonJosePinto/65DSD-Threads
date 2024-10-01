/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Random;

/**
 *
 * @author Pichau
 */
public class Carro extends Thread {
    private Random r;
    private int velocidade;
    private EstradaCelula estrada;

    public Carro(int velocidade, EstradaCelula estrada){
        this.velocidade = velocidade;
        this.estrada = estrada;
    }

    @Override
    public void run() {
        try {
            while (!estrada.isSaida()) {
                // Verificar a célula à frente (baseado na direção do carro)
                if (estrada.isProximaCelulaLivre()) {
                    // Mover carro para a próxima célula
                    moverParaProximaCelula();
                    // Atualizar a interface gráfica aqui (pintar célula)
                    atualizarInterfaceGrafica();
                }
                Thread.sleep(velocidade);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void moverParaProximaCelula() {
        EstradaCelula proximaEstrada = estrada.getProximaEstrada();

        // Atualiza a célula de origem (remove o carro da célula antiga)
        estrada.getMalha().fireTableCellUpdated(estrada.getLin(), estrada.getCol());

        // Mover o carro para a próxima célula
        estrada.setCarro(null);
        proximaEstrada.setCarro(this);
        estrada = proximaEstrada;
        System.out.println("Carro agora está na estrada:" +this.estrada.toString());
        // Atualiza a célula de destino (mostra o carro na nova célula)
        estrada.getMalha().fireTableCellUpdated(estrada.getLin(), estrada.getCol());
    }


    private void atualizarInterfaceGrafica() {
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
