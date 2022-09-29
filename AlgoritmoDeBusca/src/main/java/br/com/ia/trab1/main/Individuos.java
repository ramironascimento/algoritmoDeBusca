package br.com.ia.trab1.main;

import java.util.ArrayList;

import br.com.ia.trab1.Posicao;

public class Individuos {

    private Direcoes[] movimentosDoIndividuo;
    private int indexNPonto;
    private int comidasColetadas;
    private int aptidao; // definimos aptidao sendo quanto maior o valor, melhor o individuo
    private ArrayList<Posicao> rotaDoIndividuo; // salva as posições validas passadas pelo indivíduo

    public Individuos(int caminhoTotalPorIndividuo) {
            this.movimentosDoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
            this.indexNPonto = -1;
            this.aptidao = 0;
             rotaDoIndividuo = new ArrayList<Posicao>(); 
    }   

    /**
     * 
     * @param caminhoTotalPorIndividuo
     * @param novosCaminhosAleatorios utilizado para novos Individuos que precisam ser aleatorios:
     * Ex populacaoInicial, os movimentos sao aleatorios
     */
    public Individuos(int caminhoTotalPorIndividuo, boolean novosCaminhosAleatorios) { //gera autoamticamente um individuo com os caminhos
        this.movimentosDoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
        this.indexNPonto = -1;
        this.aptidao = 0; //aptidao em 0
        rotaDoIndividuo = new ArrayList<Posicao>(); 

        if(novosCaminhosAleatorios) iniciaCaminhosAleatorios();
    }


    public void iniciaCaminhosAleatorios(){

        for (int i = 0; i < movimentosDoIndividuo.length; i++) {
            // gerará os caminhos com movimentos aleatorios
            movimentosDoIndividuo[i] = Direcoes.generateRandomDirecao(); 
            
        }

    }

    public void setComidasColetadas(){
        this.comidasColetadas++;
    }
    public int getComidasColetadas(){
        return this.comidasColetadas;
    }
    public void setmovimentosDoIndividuo(Direcoes[] movimentosDoIndividuo) {
        this.movimentosDoIndividuo = movimentosDoIndividuo;
    }

    public void setindexNPonto(int indexNPonto) {
        if(indexNPonto != -1) {
            this.indexNPonto = indexNPonto;
        }
    }

    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    public double getAptidao() {
        return aptidao;
    }

    public int getindexNPonto() {
        return indexNPonto;
    }

    public Direcoes[] getmovimentosDoIndividuo() {
        return movimentosDoIndividuo;
    }

    public void setRotaDoIndividuo(Posicao addDirecao){
        this.rotaDoIndividuo.add(addDirecao);
    }
    
    public ArrayList<Posicao> getRotaDoIndividuo(){
        return this.rotaDoIndividuo;
    }

    @Override
    public String toString() {
        return "Individuos  " +
                "movimentosDoIndividuo=\n" + movimentosDoIndividuo.toString() +
                ", indexNPonto=\n" + indexNPonto +
                ", aptidao=" + aptidao +
                ' ';
    }
}
