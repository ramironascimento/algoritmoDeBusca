package br.com.ia.trab1.labirinto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PercorreLabirinto {

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
        //System.out.println("achei um tesouro: " + posicaoAtual.toString());
        Posicao finalPosicaoAtual = posicaoAtual;
        if (comidasAchadas.stream().noneMatch(p -> p.equals(finalPosicaoAtual))) {
          //System.out.println("  comida nova encontrada!");
          comidasAchadas.add(posicaoAtual);
        }
      }
    } while (comidasAchadas.size() < qntComida);
    return caminhoEncontrado;
  }
}
