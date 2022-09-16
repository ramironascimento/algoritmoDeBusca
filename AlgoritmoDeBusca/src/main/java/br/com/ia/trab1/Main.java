package br.com.ia.trab1;

import br.com.ia.trab1.labirinto.Labirinto;
import br.com.ia.trab1.labirinto.PercorreLabirinto;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    // le o arquivo
    var labirinto = new Labirinto(Labirinto.ReadFile("labirinto1.txt"));

    //metodo test para achar um caminho ate uma comida
    var comida = PercorreLabirinto.getQntComida(labirinto,5);

    for (int i = 0; i < comida.size(); i++) {
      System.out.println(i + " [" + comida.get(i).toString() + " ]");
    }


  }

}