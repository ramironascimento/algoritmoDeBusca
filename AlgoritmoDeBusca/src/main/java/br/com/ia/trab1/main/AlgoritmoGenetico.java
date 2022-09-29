package br.com.ia.trab1.main;

import java.util.Random;

public class AlgoritmoGenetico {

  // VARIAIVEIS GLOBAIS DE ALGORTIMOS GENETICOS (outras classes podem precisar)
  public int tamPopulacao;
  public int caminhoTotalPorIndividuo;
  public int quantidadeDeGeracoes;
  public double taxaDeMutacaoParcial; // aquela que aroveita o caminho bom
  public double taxaDeMutacaoTotal; // gera um individuo totalmente novo
  public int qntComida;

  public Individuos elitismo; // individuo com a melhor aptidao a cada geração

  /************************************************** */

  public AlgoritmoGenetico(int tamPopulacao, int caminhoTotalPorIndividuo, int quantidadeDeGeracoes,
      double taxaDeMutacaoParcial, double taxaDeMutacaoTotal, int qntComida) {
    this.tamPopulacao = tamPopulacao;
    this.caminhoTotalPorIndividuo = caminhoTotalPorIndividuo;
    this.quantidadeDeGeracoes = quantidadeDeGeracoes;
    this.taxaDeMutacaoParcial = taxaDeMutacaoParcial;
    this.taxaDeMutacaoTotal = taxaDeMutacaoTotal;
    this.qntComida = qntComida;
    this.elitismo = getMinValue();

  }

  private Individuos getMinValue() {
    Individuos individuos = new Individuos();
    individuos.setAptidao(Double.MIN_VALUE);
    return individuos;
  }

  public void executaAlgoritmoGenetico() {

    Populacao popInical = new Populacao(tamPopulacao);
    popInical.iniciaPopulacao(caminhoTotalPorIndividuo);
    int n_geracoes = 10;

    for (int i = 0; i < n_geracoes; i++) {

      for (Individuos individuo : popInical.getIndividuos()) {

        individuo = PercorreLabirinto.PercorrerLabirinto(individuo);
        individuo.setAptidao(Aptidao(individuo));
      }
    }

    //serve para descobrir o melhoe
    elitismo = getElitismo(popInical);

    

    /*//Crossover(Pai e Mae){
      escole do melhor aptidao Pai vs Mae (pipe ponto de corte)
      ex: pai1.getIndexNPONTO => [A,B,|C|,D,E]
      ex: pai2.getIndexNPONTO => [E,|F|,G,H,I]
      filho1 = [A,B,C,H,I]
      filho2 = [E,F,C,D,E]
    } */
  }

  /**
   * 
   * @param populacao
   * @return  pai[0] e mae[1] selecionados
   */
  public Individuos[] selecao(Populacao populacao){
    Random gerador = new Random();
    Individuos PaieMae[] = new Individuos[2];

    tamPopulacao = (populacao.getIndividuos().length -1); //-1 para evitar indexOf
    Individuos[] pai = {populacao.getIndividuos()[gerador.nextInt(tamPopulacao)],populacao.getIndividuos()[gerador.nextInt(tamPopulacao)]}; //seleciona dois individuos aleatórios para o pai
    Individuos[] mae = {populacao.getIndividuos()[gerador.nextInt(tamPopulacao)],populacao.getIndividuos()[gerador.nextInt(tamPopulacao)]}; //seleciona dois individuos aleatórios para o mae

    //seleciona o maior pai
    if(pai[0].getAptidao() > pai[1].getAptidao()) 
      PaieMae[0] = pai[0];

  }

  /**
   * 
   * @param pai
   * @param mae
   * @return Individuos filhos já selecionados se são melhor
   */
  public Individuos[] crossover(Individuos pai, Individuos mae){

  }
  public Individuos getElitismo(Populacao populacao) {
    int indexOfMaxValue = -1;
    double MaxAptidão = 0;
    for (int i = 0; i < populacao.getIndividuos().length; i++) {
      if (populacao.getIndividuos()[i].getAptidao() > MaxAptidão) {
        indexOfMaxValue = i; 
      }
    }
    if (indexOfMaxValue == -1) {
      return this.elitismo;
    } else {
      return populacao.getIndividuos()[indexOfMaxValue];
    }
  }

  public double Aptidao(Individuos individuos) {
    Individuos IndividuoPercorrido = PercorreLabirinto.PercorrerLabirinto(individuos);
    double aptidao = 0.0;
    // Modelo para adpitdao;
    if (IndividuoPercorrido.getindexNPonto() == 0) {
      aptidao = -1; // punimos aquele que não consegue sair do lugar
    }

    // valorizamos aqueles que percorrer o menor caminho + em dobro as comidas
    // coletadas #Pode ser ser bom, ou pode ser ruim pois privilegia somente queem
    // anda pouco e nao faz nada#
    aptidao = (IndividuoPercorrido.getmovimentosDoIndividuo().length - IndividuoPercorrido
        .getindexNPonto())
        * (2 * IndividuoPercorrido.getComidasColetadas()
        * 0.1); // 0.1 pois se for 0, irá zerar todo

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

  public Individuos MutacaoParcial(
      Individuos individuo) { // aproveitara parte do caminho com sucesso
    int indexUltimaPosValida = individuo.getindexNPonto();
    Direcoes[] caminhoOverride = individuo
        .getmovimentosDoIndividuo(); // aproveitamos a parte intera e sobreescrevemos
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
