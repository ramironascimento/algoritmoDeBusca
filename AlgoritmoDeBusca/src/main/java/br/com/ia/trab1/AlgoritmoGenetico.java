package br.com.ia.trab1;


import static br.com.ia.trab1.Output.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import br.com.ia.trab1.main.Direcoes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlgoritmoGenetico {

  private int tamanhoCromossomo;
  private int quantidadeCromossomos;
  private ArrayList<ArrayList<Direcoes>> listaCromossomos;

  public AlgoritmoGenetico(int tamanhoCromossomo, int quantidadeCromossomos) {
    this.tamanhoCromossomo = tamanhoCromossomo;
    this.quantidadeCromossomos = quantidadeCromossomos;

    var listaDirecoes = new ArrayList<>(Arrays.asList(Direcoes.values()));
    var random = new Random();

    this.listaCromossomos = new ArrayList<>();
    for (int i = 0; i < quantidadeCromossomos; i++) {
      ArrayList<Direcoes> cromossomo = new ArrayList<>();
      for (int j = 0; j < tamanhoCromossomo; j++) {
        cromossomo.add(listaDirecoes.get(random.nextInt(listaDirecoes.size())));
      }
      this.listaCromossomos.add(cromossomo);
    }

    for (int i = 0; i < quantidadeCromossomos; i++) {
      println("Cromossomo " + i);
      print("   ");
      for (int j = 0; j < tamanhoCromossomo; j++) {
        print(" " + this.listaCromossomos.get(i).get(j).getFlecha());
        if(j>0 && j%20==0){
          print("\n   ");
        }
      }
      println("");
    }

  }
}
