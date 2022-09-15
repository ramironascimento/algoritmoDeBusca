package br.com.ia.trab1;

import br.com.ia.trab1.labirinto.Labirinto;
import br.com.ia.trab1.labirinto.Posicao;
import br.com.ia.trab1.labirinto.TipoConteudo;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

  public static void main(String[] args) throws IOException {
    var labirinto = new Labirinto(Labirinto.ReadFile("labirinto1.txt"));
    var posicaos = labirinto.possiveisCaminhos(new Posicao(2, 3, TipoConteudo.INICIO));

    var comida = getComida(labirinto);
    for (int i = 0; i < comida.size(); i++) {
      System.out.println(i + " [" + comida.get(i).toString() + " ]");
    }
  }

  private static List<Posicao> getComida(Labirinto labirinto) {
    List<Posicao> caminhoEncontrado = new LinkedList<>();
    Posicao posicaoAtual = labirinto.getInicio();
    var random = new Random();
    do{
      Posicao melhorPosicao;
      var possiveisPosicoes = labirinto.possiveisCaminhos(posicaoAtual);
      posicaoAtual = possiveisPosicoes.get(random.nextInt(possiveisPosicoes.size()));
      caminhoEncontrado.add(posicaoAtual);
    }while(!posicaoAtual.getTipo().equals(TipoConteudo.TESOURO));
    return caminhoEncontrado;
  }

}