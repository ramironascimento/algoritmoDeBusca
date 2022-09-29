package br.com.ia.trab1.main;

import java.util.Random;

public class Mutacao {


    ////////////////////
    //CRIADO NO ALGORITMO GENETICO
    ///////////////////



//    private void mutacao(Populacao populacao) {
//
//        Random rnd = new Random();
//        int trocas = populacao.getTamPopulacao();
//        int aux;
//        for (int i = 0; i < trocas; i++) {
//            int individuos = rnd.nextInt(populacao.getTamPopulacao());
//            if (individuos <= 99) {
//               Individuos individuoEscolhido = populacao.getIndividuos()[individuos];
//               int primeiraPos = rnd.nextInt(individuoEscolhido.getCaminhoIndividuo().length);
//               int proxPos = rnd.nextInt(individuoEscolhido.getCaminhoIndividuo().length);
//               if(proxPos != primeiraPos) {
//                   aux = individuoEscolhido.getCaminhoIndividuo()[proxPos];
//                   individuoEscolhido.getCaminhoIndividuo()[primeiraPos] = individuoEscolhido.getCaminhoIndividuo()[proxPos];
//                   individuoEscolhido.getCaminhoIndividuo()[proxPos] = aux;
//
//                   System.out.print(individuos + "resultou na mutação em " + primeiraPos + " | " + proxPos);
//               }
//            }
//        }
//    }
}

