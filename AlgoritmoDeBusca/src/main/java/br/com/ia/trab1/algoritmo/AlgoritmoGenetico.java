package br.com.ia.trab1.algoritmo;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AlgoritmoGenetico<T> {
  private int qntCromossomos;
  private List<T> listaCromossomos;
}
