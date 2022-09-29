package br.com.ia.trab1.main;

import br.com.ia.trab1.Posicao;
import br.com.ia.trab1.TipoConteudo;
import br.com.ia.trab1.main.Labirinto;

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

  public static Individuos PercorrerLabirinto(Individuos individuo) {
    Labirinto labirinto = Labirinto.getInstance();
    List<List<Posicao>> matrizLaribinrto = labirinto.getMatrizLabirinto();
    Posicao posicaoAtual = labirinto.getInicio();

    Direcoes[] movimento = individuo.getmovimentosDoIndividuo();

    // mais tarde podemos otimizar com i recebendo o ultimo i da
    for (int i = 0; i < individuo.getmovimentosDoIndividuo().length; i++) {

      // Faz o movimento.
      int linha = posicaoAtual.getLinha();
      int coluna = posicaoAtual.getColuna();

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
      if (linha < 0 || coluna < 0) {
        
        return individuo;
      } 
      else {

        if (matrizLaribinrto.get(linha).get(coluna).getTipo() == TipoConteudo.CAMINHO)
          individuo.setindexNPonto(i);
        if (matrizLaribinrto.get(linha).get(coluna).getTipo() == TipoConteudo.TESOURO)
          individuo.setComidasColetadas();

      }
    }
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
    } while (!posicaoAtual.getTipo().equals(TipoConteudo.TESOURO));
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
      if (posicaoAtual.getTipo().equals(TipoConteudo.TESOURO)) {
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
