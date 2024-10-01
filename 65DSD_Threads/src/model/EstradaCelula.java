package model;

public class EstradaCelula {
    private Carro carro;
    private int direcao;
    private int col;
    private int lin;
    private MalhaTableModel malha;

    public EstradaCelula(int direcao, MalhaTableModel malha, int lin, int col) {
        this.direcao = direcao;
        this.lin = lin;
        this.col = col;
        this.malha = malha;
    }

    public boolean isCruzamento() {
        return direcao >= 5 && direcao <= 12;
    }

    public EstradaCelula getProximaEstrada(){
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
        return getProximaEstrada().getCarro() == null;
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

    @Override
    public String toString() {
        return "EstradaCelula{" +
                "direcao=" + direcao +
                ", col=" + col +
                ", lin=" + lin +
                '}';
    }
}
