package br.com.ia.trab1;

import br.com.ia.trab1.labirinto.Labirinto;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    var labirinto = new Labirinto(Labirinto.ReadFile("labirinto1.txt"));
  }
}