package br.com.ia.trab1.labirinto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Posicao {

  private int x;
  private int y;
  private TipoConteudo tipo;

  public boolean equals(Posicao obj) {
    if(this.x== obj.getX() && this.y== obj.getY()){
      return true;
    }
    return false;
  }

  public boolean equals(int x, int y) {
    return this.x == x && this.y == y;
  }

  @Override
  public String toString() {
    return "Posicao{" +
        "x=" + x +
        ", y=" + y +
        ", tipo=" + tipo +
        '}';
  }
}
