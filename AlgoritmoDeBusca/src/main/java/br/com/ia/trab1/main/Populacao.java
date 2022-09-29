package br.com.ia.trab1.main;

import java.util.ArrayList;
import java.util.Random;

public class Populacao { // agrupar os individuos

  Random rnd = new Random();
  private int tamPopulacao;
  private Individuo[] individuos;
  private int indexMelhorIndviduo;

  public Populacao(int tamPopulacao) {
    this.tamPopulacao = tamPopulacao;
    this.individuos = new Individuo[tamPopulacao];

  }

  public void iniciaPopulacao(int caminhoTotalPorIndividuo) {
    this.individuos = new Individuo[tamPopulacao];
    //String aux = String.valueOf(rnd.nextInt(99));
    for (int i = 0; i < individuos.length; i++) {
      individuos[i] = new Individuo(caminhoTotalPorIndividuo, true);

    }

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

  public Individuo[] getIndividuos() {
    return individuos;
  }

  public void setIndividuos(Individuo[] individuos) {
    this.individuos = individuos;
  }
}
