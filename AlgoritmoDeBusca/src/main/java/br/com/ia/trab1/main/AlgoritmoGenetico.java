package br.com.ia.trab1.main;

import static br.com.ia.trab1.Output.*;

import java.beans.IndexedPropertyChangeEvent;
import java.util.*;


public class AlgoritmoGenetico {

  // VARIAIVEIS GLOBAIS DE ALGORTIMOS GENETICOS (outras classes podem precisar)
  public int tamPopulacao;
  public int caminhoTotalPorIndividuo;
  public int quantidadeDeGeracoes;
  public double taxaDeMutacaoParcial; // aquela que aroveita o caminho bom
  public double taxaDeMutacaoTotal; // gera um individuo totalmente novo
  public int qntComida;

  public Individuos eletismo; // individuo com a melhor aptidao a cada geração

  public AlgoritmoGenetico(int tamPopulacao, int caminhoTotalPorIndividuo, int quantidadeDeGeracoes,
      double taxaDeMutacaoParcial, double taxaDeMutacaoTotal, int qntComida) {
    this.tamPopulacao = tamPopulacao;
    this.caminhoTotalPorIndividuo = caminhoTotalPorIndividuo;
    this.quantidadeDeGeracoes = quantidadeDeGeracoes;
    this.taxaDeMutacaoParcial = taxaDeMutacaoParcial;
    this.taxaDeMutacaoTotal = taxaDeMutacaoTotal;
    this.qntComida = qntComida;
    this.eletismo = getMinValue();

  }

  private Individuos getMinValue() {
    Individuos individuos = new Individuos();
    individuos.setAptidao(Double.MIN_VALUE);
    return individuos;
  }

  public void executaAlgoritmoGenetico() {
    
    Populacao popInical = new Populacao(tamPopulacao);
    popInical.iniciaPopulacao(caminhoTotalPorIndividuo);
    int n_gerações = 1000;
    for (int i = 0; i < n_gerações; i++) {

      for (Individuos individuo : popInical.getIndividuos()) {
        individuo = PercorreLabirinto.PercorrerLabirinto(individuo);
        System.out.println(individuo.toString() + "||" + Aptidao(individuo));
      }

      // elistismo*/
    }

    //serve para descobrir o melhoe
     eletismo = getEletismo(popInical);

     //SELECAO
     // =>>>Individuos[] pai = Seleção() // 
     // =>>>Individuos[] mae = Seleção() // 
     //o metodo seleção deve randomizar dois individuos 
     //(popInicial.getIndividuos()[i.randmico])para o pai e randomizar dois para mae. 
     //Pega o pai com melhor aptidao. Pega mae com maior aptidar 

    /*//Crossover(Pai e Mae){
      escole do melhor aptidao Pai vs Mae (pipe ponto de corte)
      ex: pai1.getIndexNPONTO => [A,B,|C|,D,E]
      ex: pai2.getIndexNPONTO => [E,|F|,G,H,I]
      filho1 = [A,B,C,H,I]
      filho2 = [E,F,C,D,E]
    } */
  }

  /************************************************** */
  int vIn = 1;
  boolean vOut = vIn!= 0;

  public Individuos getEletismo(Populacao populacao) {
    int indexOfMaxValue = -1;
    boolean indexOfMaxValueBoolean;

    for (int i = 0; i <= populacao.getIndividuos().length; i++) {
      if (populacao.getIndividuos()[i].getAptidao() > this.eletismo.getAptidao()) {
        indexOfMaxValue = i;
      }
      indexOfMaxValueBoolean = indexOfMaxValue == -1;
      if(indexOfMaxValueBoolean) {
        return this.eletismo;
      }
      else {
        return populacao.getIndividuos()[indexOfMaxValue];
      }
    }
    return null;
  }

  public double Aptidao(Individuos individuos) {
    Individuos IndividuoPercorrido = PercorreLabirinto.PercorrerLabirinto(individuos);
    double aptidao = 0.0;
    // Modelo para adpitdao;
    if (IndividuoPercorrido.getindexNPonto() == 0)
      aptidao = -1; // punimos aquele que não consegue sair do lugar

    // valorizamos aqueles que percorrer o menor caminho + em dobro as comidas
    // coletadas #Pode ser ser bom, ou pode ser ruim pois privilegia somente queem
    // anda pouco e nao faz nada#
    aptidao = (IndividuoPercorrido.getmovimentosDoIndividuo().length - IndividuoPercorrido.getindexNPonto())
        * (2 * IndividuoPercorrido.getComidasColetadas() * 0.1); // 0.1 pois se for 0, irá zerar todo

    // preisamos punir ao colidir em parede ou verificar se o index do ultimo
    // movimento já ajuda

    // **Pendente avaliar loop para punir

    return aptidao;
  }

  public Individuos[] Crossover(Individuos father, Individuos Mother) {

    /** FALTA CONSTRUIR */
    Individuos[] crossoverIndividuos = new Individuos[2];
    return crossoverIndividuos;

  }

  public Individuos MutacaoTotal() { // gera um individuo totalmente aleatório
    Individuos individuoMutacao = new Individuos(caminhoTotalPorIndividuo, true);// OK
    return individuoMutacao;
  }

  public Individuos MutacaoParcial(Individuos individuo) { // aproveitara parte do caminho com sucesso
    int indexUltimaPosValida = individuo.getindexNPonto();
    Direcoes[] caminhoOverride = individuo.getmovimentosDoIndividuo(); // aproveitamos a parte intera e sobreescrevemos
                                                                       // o resto com movimentos aleatorios
    for (int i = indexUltimaPosValida + 1; i < caminhoTotalPorIndividuo; i++) {
      caminhoOverride[i] = Direcoes.generateRandomDirecao();
    }

    Individuos individuoMutado = new Individuos(caminhoTotalPorIndividuo);
    individuoMutado.setmovimentosDoIndividuo(caminhoOverride);

    // individuoMutado.setAptidao(); PRECISAMOS DEFINIR A FUNCAO APTIDAO AINDA

    return individuoMutado;
  }

}
