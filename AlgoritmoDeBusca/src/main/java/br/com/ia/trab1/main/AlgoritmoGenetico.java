package br.com.ia.trab1.main;

import java.util.ArrayList;
import java.util.Random;

import br.com.ia.trab1.Posicao;

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
    Individuo Individuo = new Individuo();
    Individuo.setAptidao(Double.MIN_VALUE);
    return Individuo;
  }

  public void executaAlgoritmoGenetico() {

    Populacao populacaoBase = new Populacao(tamPopulacao);

    //seta os movmentos aleatorios para cada individuo
    populacaoBase.iniciaPopulacao(caminhoTotalPorIndividuo);

    ArrayList<Populacao> populacaoIntermediaria = new ArrayList<>();

    //int n_geracoes = 3;
    //for (int i = 0; i < n_geracoes; i++) {
    for (int i = 0; i < quantidadeDeGeracoes; i++) {
      
      Populacao populacaoIntermdiariaGeracao = new Populacao(tamPopulacao);
      System.out.println("\n\n ======================================================= \n");
      System.out.println("Iniciando geracao: " + i);
      int j = 1;

      //Identifica aptidao a partir do percurso no labirinto
      for (Individuo individuo : populacaoBase.getIndividuos()) {

        //System.out.println("individuo " + j++);
        
        individuo = PercorreLabirinto.PercorrerLabirinto(individuo);
        var aptidao = Aptidao(individuo);
        
        //System.out.println("  APTIDAO" + aptidao);
        
        individuo.setAptidao(aptidao);

        System.out.println(individuo.toString() /*Aptidao(individuo)*/);      

      }

      // elistismo - descobrir o melhor individuo*/
      elitismo = getElitismo(populacaoBase);
      System.out.println("ELITISMO " + this.elitismo.toString());
      if(elitismo.getComidasColetadas()==qntComida)
        {
          System.out.println("SOLUCAÇÃO ENCONTRADA");
          System.exit(0);

        }

      ArrayList<Individuo> listaNovosIndividuos = new ArrayList<>(tamPopulacao);
      listaNovosIndividuos.add(elitismo);
      
      // Preenche a populacao intermediaria com as selecoes e crossover

      boolean executaMutacao= true;
      while(listaNovosIndividuos.size() < tamPopulacao){
          Individuo[] paiEMae = selecao(populacaoBase);
          Individuo[] filhos = crossover(paiEMae[0], paiEMae[1]);

          var moduloAuxMutacao = this.quantidadeDeGeracoes*this.taxaDeMutacaoTotal;

          //TODO
          if(i%moduloAuxMutacao == 0 && executaMutacao){ //executando mutacao ao passo certo
            filhos[new Random().nextInt(2)] = mutacao();
            executaMutacao =false;
          }
          //mutacao após filhos gerados
          
          listaNovosIndividuos.add(filhos[0]);
          if(listaNovosIndividuos.size() < tamPopulacao){
          listaNovosIndividuos.add(filhos[1]);
          }
      }
      populacaoIntermdiariaGeracao.setIndividuos(listaNovosIndividuos.toArray(Individuo[]::new));
      populacaoIntermediaria.add (populacaoIntermdiariaGeracao);
      populacaoBase = populacaoIntermdiariaGeracao;
    }

  
  }

  private Individuo mutacao() {
    System.out.println("***** mutando *****");
    return new Individuo(this.caminhoTotalPorIndividuo,true); //tamanho do individuo | novos movimentos aleatorios
  }

  /**
   * 
   * @param populacao
   * @return  pai[0] e mae[1] selecionados
   */
  public Individuo[] selecao(Populacao populacao){
    Random gerador = new Random();
    Individuo PaieMae[] = new Individuo[2];

    tamPopulacao = (populacao.getIndividuos().length -1); //-1 para evitar indexOf
    Individuo[] pai = {populacao.getIndividuos()[gerador.nextInt(tamPopulacao)] , populacao.getIndividuos()[gerador.nextInt(tamPopulacao)]}; //seleciona dois Individuo aleatórios para o pai
    Individuo[] mae = {populacao.getIndividuos()[gerador.nextInt(tamPopulacao)] , populacao.getIndividuos()[gerador.nextInt(tamPopulacao)]}; //seleciona dois Individuo aleatórios para o mae

    //seleciona o pai com melhor aptidao
    if(pai[0].getAptidao() > pai[1].getAptidao()) {
      PaieMae[0] = pai[0];
    }else{
      PaieMae[0] = pai[1];
    }

    //seleciona o mae com melhor aptidao
    if(mae[0].getAptidao() > mae[1].getAptidao()) {
      PaieMae[1] = mae[0];
    }else{
      PaieMae[1] = mae[1];
    }
    
    return PaieMae;
  }

  /**
   * 
   * @param pai
   * @param mae
   * @return Individuo filhos já selecionados se são melhor
   */
  public Individuo[] crossover(Individuo pai, Individuo mae){

      int nponto;
      if (pai.getIndexNPonto() > mae.getIndexNPonto())
        nponto = pai.getIndexNPonto();
      else nponto = mae.getIndexNPonto();

      Individuo[] filhos = new Individuo[2];

      Direcoes[] movimentosFilho0 = new Direcoes[pai.getMovimentosDoIndividuo().length];
      Direcoes[] movimentosFilho1 = new Direcoes[pai.getMovimentosDoIndividuo().length];
      
      for (int i = 0; i < pai.getMovimentosDoIndividuo().length; i++) {
          
        //Crossover de cromossos | definindo n ponto como meio para cada caso

          if( i < nponto ) {
            movimentosFilho0[i] = pai.getMovimentosDoIndividuo()[i];
            movimentosFilho1[i] = mae.getMovimentosDoIndividuo()[i];
          }
          else {
            movimentosFilho0[i] = mae.getMovimentosDoIndividuo()[i];
            movimentosFilho1[i] = pai.getMovimentosDoIndividuo()[i];
          }
        
      }
      filhos[0] = new Individuo(this.caminhoTotalPorIndividuo);
      filhos[0].setMovimentosDoIndividuo(movimentosFilho0);
      filhos[1] = new Individuo(this.caminhoTotalPorIndividuo);
      filhos[1].setMovimentosDoIndividuo(movimentosFilho1);
      

      return filhos;
      

  }
  
  public Individuo getElitismo(Populacao populacao) {
    int indexOfMaxValue = -1;
    Individuo individuoComMaiorAptidao = new Individuo();
    double getMaiorAbtidao = -2;
//    double MaxAptidao = Double.MIN_VALUE;
    for (int i = 0; i < populacao.getIndividuos().length; i++) {
      if (populacao.getIndividuos()[i].getAptidao() > getMaiorAbtidao) {
        indexOfMaxValue = i;
        getMaiorAbtidao = populacao.getIndividuos()[i].getAptidao();
      }
    }
    if (indexOfMaxValue == -1) {
      return this.elitismo;
    } else {
      return populacao.getIndividuos()[indexOfMaxValue];
    }
  }

  public double Aptidao(Individuo Individuo) {
    Individuo IndividuoPercorrido = PercorreLabirinto.PercorrerLabirinto(Individuo);
    double aptidao = 0.0;
    // Modelo para adpitdao;
    if (IndividuoPercorrido.getIndexNPonto() == 0) {
      aptidao = -1; // punimos aquele que não consegue sair do lugar
    }

    // valorizamos aqueles que percorrer o menor caminho + em dobro as comidas
    // coletadas #Pode ser ser bom, ou pode ser ruim pois privilegia somente queem
    // anda pouco e nao faz nada#
    aptidao = (IndividuoPercorrido.getIndexNPonto()+1)
        * ((2 * IndividuoPercorrido.getComidasColetadas()) == 0 ? 1 : (2 * IndividuoPercorrido.getComidasColetadas()));

    // preisamos punir ao colidir em parede ou verificar se o index do ultimo
    // movimento já ajuda

    // **Pendente avaliar loop para punir

    return aptidao;
  }

  public Individuo[] Crossover(Individuo father, Individuo Mother) {

    /** FALTA CONSTRUIR */
    Individuo[] crossoverIndividuo = new Individuo[2];
    return crossoverIndividuo;

  }

  public Individuo MutacaoTotal() { // gera um individuo totalmente aleatório
    Individuo individuoMutacao = new Individuo(caminhoTotalPorIndividuo, true);// OK
    return individuoMutacao;
  }

  public Individuo MutacaoParcial(
      Individuo individuo) { // aproveitara parte do caminho com sucesso
    int indexUltimaPosValida = individuo.getIndexNPonto();
    Direcoes[] caminhoOverride = individuo
        .getMovimentosDoIndividuo(); // aproveitamos a parte intera e sobreescrevemos
    // o resto com movimentos aleatorios
    for (int i = indexUltimaPosValida + 1; i < caminhoTotalPorIndividuo; i++) {
      caminhoOverride[i] = Direcoes.generateRandomDirecao();
    }

    Individuo individuoMutado = new Individuo(caminhoTotalPorIndividuo);
    individuoMutado.setMovimentosDoIndividuo(caminhoOverride);

    // individuoMutado.setAptidao(); PRECISAMOS DEFINIR A FUNCAO APTIDAO AINDA

    return individuoMutado;
  }

}
