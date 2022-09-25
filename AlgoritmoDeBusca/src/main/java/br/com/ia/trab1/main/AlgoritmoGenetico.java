package br.com.ia.trab1.main;


import static br.com.ia.trab1.Output.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlgoritmoGenetico {

  //VARIAIVEIS GLOBAIS DE ALGORTIMOS GENETICOS (outras classes podem precisar)
  public int tamPopulacao;
  public int caminhoTotalPorIndividuo;
  public int quantidadeDeGeracoes;
  public double taxaDeMutacaoParcial; // aquela que aroveita o caminho bom
  public double taxaDeMutacaoTotal; // gera um individuo totalmente novo
  public int qntComida;

  public AlgoritmoGenetico(int tamPopulacao, int caminhoTotalPorIndividuo, int quantidadeDeGeracoes, double taxaDeMutacaoParcial, double taxaDeMutacaoTotal, int qntComida) {
    this.tamPopulacao = tamPopulacao;
    this.caminhoTotalPorIndividuo = caminhoTotalPorIndividuo;
    this.quantidadeDeGeracoes = quantidadeDeGeracoes;
    this.taxaDeMutacaoParcial = taxaDeMutacaoParcial;
    this.taxaDeMutacaoTotal = taxaDeMutacaoTotal;
    this.qntComida = qntComida;
  }


    public void executaAlgoritmoGenetico(){

    }

  


  /************************************************** */
  public double Aptidao(Individuos individuos){
      return 0.0;
  }
  public Individuos[] Crossover(Individuos father, Individuos Mother){
    
    /**FALTA CONSTRUIR */
    Individuos[] crossoverIndividuos = new Individuos[2];
    return crossoverIndividuos;

  }
  public Individuos MutacaoTotal(){ //gera um individuo totalmente aleatório
      Individuos individuoMutacao = new Individuos(caminhoTotalPorIndividuo, true);//OK
      return individuoMutacao;
  }

  public Individuos MutacaoParcial(Individuos individuo){ //aproveitara parte do caminho com sucesso
      int indexUltimaPosValida = individuo.getIndexUltimaPosValida();
      Direcoes[] caminhoOverride = individuo.getCaminhoIndividuo(); //aproveitamos a parte intera e sobreescrevemos o resto com movimentos aleatorios
      for(int i  = indexUltimaPosValida+1; i<caminhoTotalPorIndividuo; i++){
        caminhoOverride[i] = Direcoes.generateRandomDirecao();
      }
      
      Individuos individuoMutado = new Individuos(caminhoTotalPorIndividuo);
      individuoMutado.setCaminhoIndividuo(caminhoOverride);
      
      // individuoMutado.setAptidao(); PRECISAMOS DEFINIR A FUNCAO APTIDAO AINDA

      return individuoMutado;

  }
}
