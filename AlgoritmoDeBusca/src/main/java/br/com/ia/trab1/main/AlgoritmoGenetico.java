package br.com.ia.trab1.main;

import static br.com.ia.trab1.Output.*;

import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlgoritmoGenetico {

  // VARIAIVEIS GLOBAIS DE ALGORTIMOS GENETICOS (outras classes podem precisar)
  public int tamPopulacao;
  public int caminhoTotalPorIndividuo;
  public int quantidadeDeGeracoes;
  public double taxaDeMutacaoParcial; // aquela que aroveita o caminho bom
  public double taxaDeMutacaoTotal; // gera um individuo totalmente novo
  public int qntComida;

  Individuos eletismo; // individuo com a melhor aptidao a cada geração

  public AlgoritmoGenetico(int tamPopulacao, int caminhoTotalPorIndividuo, int quantidadeDeGeracoes,
      double taxaDeMutacaoParcial, double taxaDeMutacaoTotal, int qntComida) {
    this.tamPopulacao = tamPopulacao;
    this.caminhoTotalPorIndividuo = caminhoTotalPorIndividuo;
    this.quantidadeDeGeracoes = quantidadeDeGeracoes;
    this.taxaDeMutacaoParcial = taxaDeMutacaoParcial;
    this.taxaDeMutacaoTotal = taxaDeMutacaoTotal;
    this.qntComida = qntComida;
    this.eletismo.setAptidao(Double.MIN_VALUE);

  public void executaAlgoritmoGenetico() {
    Populacao popInical = new Populacao(tamPopulacao);
    popInical.iniciaPopulacao(caminhoTotalPorIndividuo);
    int n_gerações = 1000;
    for (int i = 0; i < n_gerações; i++) {

      for (Individuos individuo : popInical.getIndividuos()) {
        individuo = PercorreLabirinto.PercorrerLabirinto(individuo);
        System.out.println(individuo.toString() + "||" + Aptidao(individuo));
      }
    }
    // // pegar maior aptidao
    
  
    Elistismo = Arrays.stream(popInical.getIndividuos())
            .max(Comparator.comparingDouble(popInical.getIndividuos()::getAptidao))
            .orElseThrow(NoSuchElementException::new);
  }

  /************************************************** */
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
