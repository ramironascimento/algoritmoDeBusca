package br.com.ia.trab1.main;

import java.util.Random;

public enum Direcoes {
  LESTE("→"), OESTE("←"),
  NORTE("↑"), SUL("↓"),
  NORDESTE("↗"), NOROESTE("↖"),
  SUDESTE("↘"), SUDOESTE("↙");

  private String flecha;

  Direcoes(String flecha) {
    this.flecha = flecha;
  }

  public String getFlecha() {
    return flecha;
  }
  public static Direcoes generateRandomDirecao(){
    
      Direcoes[] values = Direcoes.values();
      int length = values.length;
      int randIndex = new Random().nextInt(length);
      return values[randIndex];
  
  }
}

