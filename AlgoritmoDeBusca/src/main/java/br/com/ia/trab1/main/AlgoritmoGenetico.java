package br.com.ia.trab1.main;

public class AlgoritmoGenetico {

  // VARIAIVEIS GLOBAIS DE ALGORTIMOS GENETICOS (outras classes podem precisar)
  public int tamPopulacao;
  public int caminhoTotalPorIndividuo;
  public int quantidadeDeGeracoes;
  public double taxaDeMutacaoParcial; // aquela que aroveita o caminho bom
  public double taxaDeMutacaoTotal; // gera um individuo totalmente novo
  public int qntComida;

  public Individuo elitismo; // individuo com a melhor aptidao a cada geração

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

  private Individuo getMinValue() {
    Individuo individuo = new Individuo();
    individuo.setAptidao(Double.MIN_VALUE);
    return individuo;
  }

  public void executaAlgoritmoGenetico() {

    Populacao popInical = new Populacao(tamPopulacao);
    popInical.iniciaPopulacao(caminhoTotalPorIndividuo);
    int n_geracoes = 3;
    for (int i = 0; i < n_geracoes; i++) {
      System.out.println("Iniciando geracao: " + i);
      int j = 1;
      for (Individuo individuo : popInical.getIndividuos()) {
        System.out.println("individuo " + j++);
        individuo = PercorreLabirinto.PercorrerLabirinto(individuo);
        System.out.println(individuo.toString() /*Aptidao(individuo)*/);
        Individuo elitismo = Selecao.elitismo(popInical);
      }
      // elistismo*/
    }

    //serve para descobrir o melhoe
    elitismo = getElitismo(popInical);

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

  public Individuo getElitismo(Populacao populacao) {
    int indexOfMaxValue = -1;

    for (int i = 0; i <= populacao.getIndividuos().length; i++) {
      if (populacao.getIndividuos()[i].getAptidao() > this.elitismo.getAptidao()) {
        indexOfMaxValue = i;
      }
      if (indexOfMaxValue == -1) {
        return this.elitismo;
      } else {
        return populacao.getIndividuos()[indexOfMaxValue];
      }
    }
    return null;
  }

  public double Aptidao(Individuo individuo) {
    Individuo IndividuoPercorrido = PercorreLabirinto.PercorrerLabirinto(individuo);
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

  public Individuo[] Crossover(Individuo father, Individuo Mother) {

    /** FALTA CONSTRUIR */
    Individuo[] crossoverIndividuos = new Individuo[2];
    return crossoverIndividuos;

  }

  public Individuo MutacaoTotal() { // gera um individuo totalmente aleatório
    Individuo individuoMutacao = new Individuo(caminhoTotalPorIndividuo, true);// OK
    return individuoMutacao;
  }

  public Individuo MutacaoParcial(
      Individuo individuo) { // aproveitara parte do caminho com sucesso
    int indexUltimaPosValida = individuo.getindexNPonto();
    Direcoes[] caminhoOverride = individuo
        .getmovimentosDoIndividuo(); // aproveitamos a parte intera e sobreescrevemos
    // o resto com movimentos aleatorios
    for (int i = indexUltimaPosValida + 1; i < caminhoTotalPorIndividuo; i++) {
      caminhoOverride[i] = Direcoes.generateRandomDirecao();
    }

    Individuo individuoMutado = new Individuo(caminhoTotalPorIndividuo);
    individuoMutado.setmovimentosDoIndividuo(caminhoOverride);

    // individuoMutado.setAptidao(); PRECISAMOS DEFINIR A FUNCAO APTIDAO AINDA

    return individuoMutado;
  }

}
