package br.com.ia.trab1.main;

import br.com.ia.trab1.Posicao;
import br.com.ia.trab1.TipoConteudo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PercorreLabirinto {

  /**
   * Recebe um individuo para percorrer.
   *
   * @return Individuo com suas infomacoes atualizadas
   */

  public static Individuo PercorrerLabirinto(Individuo individuo) {
    Labirinto labirinto = Labirinto.getInstance();
    List<List<Posicao>> matrizLabirinto = labirinto.getMatrizLabirinto();
    Posicao posicaoAtual = new Posicao(0,0,TipoConteudo.INICIO);

    Direcoes[] movimento = individuo.getMovimentosDoIndividuo();

    // mais tarde podemos otimizar com i recebendo o ultimo i da
    int linha = 0;
    int coluna = 0;
    //System.out.print("  passos dados:");
    for (int i = 0; i < movimento.length; i++) {
      // Faz o movimento.

      switch (movimento[i]) {

        case LESTE:
          posicaoAtual.setColuna(coluna++);
          break;

        case OESTE:
          posicaoAtual.setColuna(coluna--);
          break;

        case SUL:
          posicaoAtual.setLinha(linha++);
          break;

        case NORTE:
          posicaoAtual.setLinha(linha--);
          break;

        case NORDESTE:
          posicaoAtual.setColuna(coluna++);// leste
          posicaoAtual.setLinha(linha--);// norte
          break;

        case SUDESTE:
          posicaoAtual.setColuna(coluna++);// leste
          posicaoAtual.setLinha(linha++);// sul
          break;

        case SUDOESTE:
          posicaoAtual.setColuna(coluna--);// OESTE
          posicaoAtual.setLinha(linha++);// SUL
          break;

        case NOROESTE:
          posicaoAtual.setColuna(coluna--);// OESTE
          posicaoAtual.setLinha(linha--);// NORTE
          break;

        default:
          System.exit(1);
      }

      // VALIDA OS MOVEVIMENTOS
      if (linha < 0 || coluna < 0 ||
          linha >= matrizLabirinto.size() || coluna >= matrizLabirinto.size()) {
       // System.out.println(" movimento invalido");
        return individuo;
      } else {
        var xy = matrizLabirinto.get(linha).get(coluna);
        if (TipoConteudo.CAMINHO.equals(xy.getTipo())) {
          System.out.print("[" + xy.getTipo() +"]");
          individuo.setindexNPonto(i);
        }
        else if (TipoConteudo.COMIDA.equals(xy.getTipo())) {
          individuo.setComidasColetadas(xy);
          individuo.setindexNPonto(i);
          System.out.print("[" + xy.getTipo() +"]");
        }
        else if (TipoConteudo.PAREDE.equals(xy.getTipo())) {
         // System.out.println("[" + xy.getTipo() +"]");
          //System.out.println("quantidade de passos dados: " + i);
          return individuo;
        }
        else if(TipoConteudo.INICIO.equals(xy.getTipo())){
          System.out.print("[" + xy.getTipo() +"]");
          individuo.setindexNPonto(i);
        }
        else{
         // System.out.println("MOVIMENTO INVALIDO");
        }
      }
    }
    System.out.println(" ");
    return individuo;
  }

  ////////////////////////// podemos utilizar para marcar o caminho percorrido na
  ////////////////////////// matriz, ex 0,1,5,8,9
  public static List<Posicao> getComida(Labirinto labirinto) {
    List<Posicao> caminhoEncontrado = new LinkedList<>();
    Posicao posicaoAtual = labirinto.getInicio();
    var random = new Random();
    do {
      Posicao melhorPosicao;
      var possiveisPosicoes = labirinto.possiveisCaminhos(posicaoAtual);
      posicaoAtual = possiveisPosicoes.get(random.nextInt(possiveisPosicoes.size()));
      caminhoEncontrado.add(posicaoAtual);
    } while (!posicaoAtual.getTipo().equals(TipoConteudo.COMIDA));
    return caminhoEncontrado;
  }

  public static List<Posicao> getQntComida(Labirinto labirinto, int qntComida) {
    List<Posicao> caminhoEncontrado = new LinkedList<>();
    Posicao posicaoAtual = labirinto.getInicio();
    List<Posicao> comidasAchadas = new ArrayList<>();
    var random = new Random();
    do {
      var possiveisPosicoes = labirinto.possiveisCaminhos(posicaoAtual);
      posicaoAtual = possiveisPosicoes.get(random.nextInt(possiveisPosicoes.size()));
      caminhoEncontrado.add(posicaoAtual);
      if (posicaoAtual.getTipo().equals(TipoConteudo.COMIDA)) {
        // System.out.println("achei um tesouro: " + posicaoAtual.toString());
        Posicao finalPosicaoAtual = posicaoAtual;
        if (comidasAchadas.stream().noneMatch(p -> p.equals(finalPosicaoAtual))) {
          // System.out.println(" comida nova encontrada!");
          comidasAchadas.add(posicaoAtual);
        }
      }
    } while (comidasAchadas.size() < qntComida);
    return caminhoEncontrado;
  }
}
