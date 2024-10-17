package controller;

import java.util.List;
import java.util.Random;
import model.Carro;
import model.EstradaCelula;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author guilh
 */
public class GeradorCarro extends Thread {
    
    private ExecucaoMalhaController controller;
    private List<EstradaCelula> celulasEntrada;
    private int qtdVeiculos;
    private long intervalo;
    
    
    public GeradorCarro(ExecucaoMalhaController controller, List<EstradaCelula> celulasEntrada, int qtdVeiculos, long intervalo) {
        this.controller = controller;
        this.celulasEntrada = celulasEntrada;
        this.qtdVeiculos = qtdVeiculos;
        this.intervalo = intervalo;
    }
    
    @Override
    public void run() {
        while(!this.isInterrupted()) {
            
            if (!(controller.getVeiculosMalha().size() == qtdVeiculos)) {

                for (int i = 0; i < qtdVeiculos; i++) {
                    EstradaCelula estradaEntrada = celulasEntrada.get(new Random().nextInt(celulasEntrada.size()));

                    Carro carro = new Carro(estradaEntrada, controller.getExclusaoMutuaTipo(), controller);
                    estradaEntrada.tentarEntrarEstrada();
                    estradaEntrada.setCarro(carro);
                    controller.getVeiculosMalha().add(carro);

                    carro.atualizarInterfaceGrafica(); // TODO
                    carro.start();
                    carro.atualizarInterfaceGrafica();

                    try {
                        Thread.sleep(intervalo);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                
                this.interrupt();
            }
            
        }
    }
    
}
