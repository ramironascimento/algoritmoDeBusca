package br.com.ia.trab1;

import br.com.ia.trab1.algoritmo.AlgoritmoGenetico;
import br.com.ia.trab1.labirinto.Labirinto;
import br.com.ia.trab1.labirinto.PercorreLabirinto;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) throws IOException {
    // le o arquivo
    //var labirinto = new Labirinto(Labirinto.ReadFile("labirinto1.txt"));
    var labirintos = List.of(new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")),
        new Labirinto(Labirinto.ReadFile("labirinto1.txt")));

    AlgoritmoGenetico<Labirinto> algoritmo = new AlgoritmoGenetico<>(10, labirintos);

    var resultadosPrimeiraSelecao = algoritmo.getListaCromossomos()
        .parallelStream()
        .map((l) -> {
          var passosParaAcharTodasComidas = PercorreLabirinto.getQntComida(l, 5);
          System.out.println("numero de passos: " + passosParaAcharTodasComidas.size());
          return passosParaAcharTodasComidas;
        })
        .collect(Collectors.toList());

    var min = resultadosPrimeiraSelecao
        .stream()
        .min(Comparator.comparing(List::size));

    System.out.println(min.get().size());
  }

}