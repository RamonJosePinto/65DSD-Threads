package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstradaCelula {
    private Carro carro;
    private int direcao;
    private int col;
    private int lin;
    private MalhaTableModel malha;
    private boolean cruzamento;

    public EstradaCelula(int direcao, MalhaTableModel malha, int lin, int col, boolean cruzamento) {
        this.direcao = direcao;
        this.lin = lin;
        this.col = col;
        this.malha = malha;
        this.cruzamento = cruzamento;
    }

    public boolean isCruzamento() {
        return cruzamento;
    }

    public EstradaCelula getProximaEstrada(int direcao){
        int novaLinha = lin;
        int novaColuna = col;
        switch (direcao){
            case 1: // Cima
                novaLinha--;
                break;
            case 2: // Direita
                novaColuna++;
                break;
            case 3: // Baixo
                novaLinha++;
                break;
            case 4: // Esquerda
                novaColuna--;
                break;
        }
        return (EstradaCelula) malha.getValueAt(novaLinha, novaColuna);
    }

    public boolean isProximaCelulaLivre(){
        return getProximaEstrada(this.getDirecao()).getCarro() == null;
    }

    public boolean isSaida(){
        if(col == 0){
            if(direcao == 4) return true;
        } else if (lin == 0){
            if (direcao == 1) return true;
        } else if (col == malha.getColumnCount() - 1){
            if (direcao == 2) return true;
        } else if (lin == malha.getRowCount() - 1){
            if (direcao == 3) return true;
        }
        return false;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLin() {
        return lin;
    }

    public void setLin(int lin) {
        this.lin = lin;
    }

    public MalhaTableModel getMalha() {
        return malha;
    }

    public void setMalha(MalhaTableModel malha) {
        this.malha = malha;
    }

    public void setCruzamento(boolean cruzamento) {
        this.cruzamento = cruzamento;
    }

    @Override
    public String toString() {
        return "EstradaCelula{" +
                "direcao=" + direcao +
                ", col=" + col +
                ", lin=" + lin +
                '}';
    }

    public List<EstradaCelula> getCruzamentos() {
        List<EstradaCelula> caminhoCruzamento = new ArrayList<>();

        // Verificar todas as direções possíveis no cruzamento
        List<Integer> direcoesPossiveis = new ArrayList<>();

        EstradaCelula proximaEstrada = this;

        while (proximaEstrada != null && proximaEstrada.isCruzamento()) {

        // Adiciona direções possíveis baseado no valor de direção
        if (proximaEstrada.getDirecao() == 5 || proximaEstrada.getDirecao() == 9 || proximaEstrada.getDirecao() == 10) {  // Cima permitido
            direcoesPossiveis.add(1);  // 1 representa direção Cima
        }
        if (proximaEstrada.getDirecao() == 6 || proximaEstrada.getDirecao() == 9 || proximaEstrada.getDirecao() == 11) {  // Direita permitido
            direcoesPossiveis.add(2);  // 2 representa direção Direita
        }
        if (proximaEstrada.getDirecao() == 7 || proximaEstrada.getDirecao() == 11 || proximaEstrada.getDirecao() == 12) {  // Baixo permitido
            direcoesPossiveis.add(3);  // 3 representa direção Baixo
        }
        if (proximaEstrada.getDirecao() == 8 || proximaEstrada.getDirecao() == 10 || proximaEstrada.getDirecao() == 12) {  // Esquerda permitido
            direcoesPossiveis.add(4);  // 4 representa direção Esquerda
        }

        System.out.println("direões possiveis"+direcoesPossiveis);

        // Escolher aleatoriamente uma direção
        Random random = new Random();
        int direcaoEscolhida = direcoesPossiveis.get(random.nextInt(direcoesPossiveis.size()));

        System.out.println("direcoes possiveis:" +direcoesPossiveis.toString());
        System.out.println("direacao escolhida"+direcaoEscolhida);

            // Adiciona a célula à lista de caminho do cruzamento
            caminhoCruzamento.add(proximaEstrada);
            proximaEstrada = proximaEstrada.getProximaEstrada(direcaoEscolhida);
            direcoesPossiveis.clear();
        }

        // Adiciona a última célula (que não é mais cruzamento) à lista
        if (proximaEstrada != null) {
            caminhoCruzamento.add(proximaEstrada);
        }

        // Retorna o caminho a ser percorrido no cruzamento
        return caminhoCruzamento;
    }
}
