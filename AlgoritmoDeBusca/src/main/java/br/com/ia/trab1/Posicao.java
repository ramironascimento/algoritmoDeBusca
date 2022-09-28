package br.com.ia.trab1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Posicao {

  private int linha;
  private int coluna;
  private TipoConteudo tipo;

  public boolean equals(Posicao obj) {
    if(this.linha== obj.getLinha() && this.coluna== obj.getColuna()){
      return true;
    }
    return false;
  }

  public boolean equals(int linha, int coluna) {
    return this.linha == linha && this.coluna == coluna;
  }

  @Override
  public String toString() {
    return "Posicao{" +
        "linha=" + linha +
        ", coluna=" + coluna +
        ", tipo=" + tipo +
        '}';
  }
}
