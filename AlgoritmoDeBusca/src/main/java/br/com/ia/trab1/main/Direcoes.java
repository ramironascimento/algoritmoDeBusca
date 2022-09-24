package br.com.ia.trab1.main;

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
}

