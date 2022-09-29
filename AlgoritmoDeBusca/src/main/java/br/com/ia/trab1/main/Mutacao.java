package br.com.ia.trab1.main;

import java.util.Random;

public class Mutacao {


    ////////////////////
    /* ACHO QUE PODEMOS INCLUIR MUTACOES NA CLASSE DE ALGORITMO GENETICO PARA CONCENTRAR AS ACOES */
    ///////////////////
    private void mutacao(Populacao populacao) {

        Random rnd = new Random();
        int trocas = populacao.getTamPopulacao();
        int aux;
        for (int i = 0; i < trocas; i++) {
            int individuos = rnd.nextInt(populacao.getTamPopulacao());
            if (individuos <= 99) {
               Individuos individuoEscolhido = populacao.getIndividuos()[individuos];
               int primeiraPos = rnd.nextInt(individuoEscolhido.getmovimentosDoIndividuo().length);
               int proxPos = rnd.nextInt(individuoEscolhido.getmovimentosDoIndividuo().length);
               if(proxPos != primeiraPos) {
                   aux = individuoEscolhido.getmovimentosDoIndividuo()[proxPos];
                   individuoEscolhido.getmovimentosDoIndividuo()[primeiraPos] = individuoEscolhido.getmovimentosDoIndividuo()[proxPos];
                   individuoEscolhido.getmovimentosDoIndividuo()[proxPos] = aux;

                   System.out.print(individuos + "resultou na mutação em " + primeiraPos + " | " + proxPos);
               }
            }
        }
    }
}

