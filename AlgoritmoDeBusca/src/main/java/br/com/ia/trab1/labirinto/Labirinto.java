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
            tipo = TipoConteudo.COMIDA;
            break;
          default:
            throw new IllegalReceiveException("valor da posicao da matriz invalido.");
        }
        this.matriz.get(i - 1).add(new Posicao(i - 1, j, tipo));
    }
      this.matriz
          .get(i - 1)
          .forEach(p -> System.out.print(p.getTipo() + " "));
      System.out.println("");
    }

  }
}
