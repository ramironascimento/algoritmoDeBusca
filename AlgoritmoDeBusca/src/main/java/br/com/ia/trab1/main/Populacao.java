package br.com.ia.trab1.main;

import java.util.Random;

public class Populacao { // agrupar os individuos

    Random rnd = new Random();
    private int tamPopulacao;
    private Individuos[] individuos;
    private int indexMelhorIndviduo;

    public Populacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
        this.individuos = new Individuos[tamPopulacao];

    }

    public Individuos[] iniciaPopulacao(int caminhoTotalPorIndividuo) {
        this.individuos = new Individuos[tamPopulacao];
        //String aux = String.valueOf(rnd.nextInt(99));
        for (int i=0; i<individuos.length; i++) {
            individuos[i] = new Individuos(caminhoTotalPorIndividuo, true);

        }
        return individuos;
    }

//    private void geraPopulacao(int tamanho) {
//
//    }

    public int getTamPopulacao() {
        return tamPopulacao;
    }

    public void setTamPopulacao(int tamPopulacao) {
        this.tamPopulacao = tamPopulacao;
    }

    public Individuos[] getIndividuos() {
        return individuos;
    }

    public void setIndividuos(Individuos[] individuos) {
        this.individuos = individuos;
    }
}
