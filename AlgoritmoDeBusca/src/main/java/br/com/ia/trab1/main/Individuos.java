package br.com.ia.trab1.main;

import br.com.ia.trab1.Posicao;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Individuos {

  private Direcoes[] getmovimentosDoIndividuo;
  private int indexNPonto;
  private List<Posicao> comidasColetadas = new ArrayList<>();

  private double aptidao; // definimos aptidao sendo quanto maior o valor, melhor o individuo
  private ArrayList<Posicao> rotaDoIndividuo; // salva as posições validas passadas pelo indivíduo

  public Individuos(int caminhoTotalPorIndividuo) {
    this.getmovimentosDoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
    this.indexNPonto = -1;
    this.aptidao = 0;
  }

  public Individuos() {
  }

  ;

  /**
   * @param caminhoTotalPorIndividuo
   * @param novosCaminhosAleatorios  utilizado para novos Individuos que precisam ser aleatorios: Ex
   *                                 populacaoInicial, os movimentos sao aleatorios
   */
  public Individuos(int caminhoTotalPorIndividuo,
      boolean novosCaminhosAleatorios) { //gera autoamticamente um individuo com os caminhos
    this.getmovimentosDoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
    this.indexNPonto = -1;
    this.aptidao = 0; //aptidao em 0

    if (novosCaminhosAleatorios) {
      iniciaCaminhosAleatorios();
    }
  }


  public void iniciaCaminhosAleatorios() {

    for (int i = 0; i < getmovimentosDoIndividuo.length; i++) {
      // gerará os caminhos com movimentos aleatorios
      getmovimentosDoIndividuo[i] = Direcoes.generateRandomDirecao();

    }

  }

  public int getComidasColetadas() {
    return this.comidasColetadas.size();
  }

  public void setComidasColetadas(Posicao posicao) {
    if (comidasColetadas
        .stream()
        .noneMatch(comidasColetadas -> comidasColetadas.getLinha() == posicao.getLinha() &&
            comidasColetadas.getColuna() == posicao.getColuna())) {
      this.comidasColetadas.add(posicao);
    }
  }

  public void setmovimentosDoIndividuo(Direcoes[] getmovimentosDoIndividuo) {
    this.getmovimentosDoIndividuo = getmovimentosDoIndividuo;
  }

  public void setindexNPonto(int indexNPonto) {
    if (indexNPonto != -1) {
      this.indexNPonto = indexNPonto;
    }
  }

  public double getAptidao() {
    return aptidao;
  }

  public void setAptidao(double aptidao) {
    this.aptidao = aptidao;
  }

  public int getindexNPonto() {
    return indexNPonto;
  }

  public Direcoes[] getmovimentosDoIndividuo() {
    return getmovimentosDoIndividuo;
  }

  @Override
  public String toString() {
    return "Individuos: " + "movimentacao = " + Arrays.stream(getmovimentosDoIndividuo).map(
        Direcoes::toString).collect(Collectors.joining()) +
        ",\n indexNPonto= " + indexNPonto +
        ",\n aptidao=" + aptidao + "\n";
  }
}
