package br.com.ia.trab1.main;

import java.util.Random;
import lombok.Getter;

@Getter
public enum Direcoes {
  LESTE("→"), OESTE("←"),
  NORTE("↑"), SUL("↓"),
  NORDESTE("↗"), NOROESTE("↖"),
  SUDESTE("↘"), SUDOESTE("↙");

  private String flecha;

  Direcoes(String flecha) {
    this.flecha = flecha;
  }

  public static Direcoes generateRandomDirecao() {

    Direcoes[] values = Direcoes.values();
    int length = values.length;
    int randIndex = new Random().nextInt(length);
    return values[randIndex];

  }

  @Override
  public String toString() {
    return " {" + flecha + "}" + super.toString();
  }
}

