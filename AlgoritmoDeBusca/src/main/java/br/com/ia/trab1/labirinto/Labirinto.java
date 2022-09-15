package br.com.ia.trab1.labirinto;

import com.sun.nio.sctp.IllegalReceiveException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Labirinto {

  private List<List<Posicao>> matriz;

  public Labirinto(String file) {
    this.matriz = new ArrayList<>();
    preencherMatriz(file);
  }

  public static String ReadFile(String file) throws IOException {
    String data = "";
    data = new String(Files.readAllBytes(Paths.get(file)));
    return data;
  }

  public void preencherMatriz(String file) {
    var fileLines = file.replace("\r", "").split("\n");
    var xy = Integer.parseInt(fileLines[0]);
    for (int i = 1; i <= xy; i++) {
      this.matriz.add(new ArrayList<>());
      var columns = fileLines[i].split(" ");
      for (int j = 0; j < xy; j++) {
        TipoConteudo tipo;
        switch (columns[j]) {
          case "E":
            tipo = TipoConteudo.INICIO;
            break;
          case "1":
            tipo = TipoConteudo.PAREDE;
            break;
          case "0":
            tipo = TipoConteudo.CAMINHO;
            break;
          case "C":
            tipo = TipoConteudo.TESOURO;
            break;
          default:
            throw new IllegalReceiveException("valor da posicao da matriz invalido.");
        }
        this.matriz.get(i - 1).add(new Posicao(i - 1, j, tipo));
      }
      this.matriz
          .get(i - 1)
          .forEach(p -> System.out.print(p.getTipo().toString().substring(0,1) + "  "));
    }

  }

  public List<Posicao> possiveisCaminhos(Posicao posicao) {
    System.out.println(" -> possiveis caminhos");
    var posicoesPossiveis = new ArrayList<Posicao>();

    int x_inicial = 0;
    int y_inicial = 0;
    int x_final = 0;
    int y_final = 0;

    if (posicao.getX() >= 1) {
      x_inicial = posicao.getX() - 1;
    }

    if (posicao.getY() >= 1) {
      y_inicial = posicao.getY() - 1;
    }

    if (posicao.getX() <= this.matriz.size() - 1) {
      x_final = posicao.getX() + 1;
    }

    if (posicao.getY() <= this.matriz.get(0).size() - 1) {
      y_final = posicao.getY() + 1;
    }
    System.out.println("  - x inicial: " + x_inicial);
    System.out.println("  - x final: " + x_final);
    System.out.println("  - y inicial: " + y_inicial);
    System.out.println("  - y final: " + y_final);

    System.out.println("    - loop");
    for (int i = x_inicial; i <= x_final; i++) {
      System.out.println("      - i = " + i);
      for (int j = y_inicial; j <= y_final; j++) {
        if(!posicao.equals(i,j)) {
          System.out.println("        - j = " + j);
          var posicaoMatriz = this.matriz.get(i).get(j);
          if (!posicaoMatriz.getTipo().equals(TipoConteudo.PAREDE)) {
            posicoesPossiveis.add(posicaoMatriz);
            System.out.println(
                "        - possivel caminho encontrado, tipo = " + posicaoMatriz.getTipo());
          } else {
            System.out.println("        - parede");
          }
        }
      }
    }
    return posicoesPossiveis;
  }

  public Posicao getInicio() {
    return this.matriz.get(0).get(0);
  }
}
