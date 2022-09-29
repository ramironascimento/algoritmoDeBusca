package br.com.ia.trab1.main;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Selecao {

    private static final Random rnd = new Random();

    public static Individuos elitismo(Populacao populacao) {
        Individuos apropriado = new Individuos();
        int menorAptidao = -1;

        for(int i = 0; i < populacao.getIndividuos().length; i ++) {
            if ((apropriado == null)  || populacao.getIndividuos()[i].getAptidao() < menorAptidao) {
                apropriado = populacao.getIndividuos()[i];
                menorAptidao = (int) populacao.getIndividuos()[i].getAptidao();
            }
            Individuos escolhido = new Individuos(apropriado.getmovimentosDoIndividuo().length);
            escolhido.setmovimentosDoIndividuo(apropriado.getmovimentosDoIndividuo()/*clone()*/);
            return escolhido;
        }
        return elitismo(populacao);
    }


    public static Individuos torneio(List<Individuos> disponiveis) {
        Individuos primeiraOpcao;
        Individuos segundaOpcao;
        if(disponiveis.size() < 0) {
            return null;
        }
        int random = rnd.nextInt(disponiveis.size());
        primeiraOpcao = disponiveis.remove(random);
        if (disponiveis.size() == 0) {
            return primeiraOpcao;
        }
        int random2 = rnd.nextInt(disponiveis.size());
        segundaOpcao = disponiveis.remove(random2);

        if (primeiraOpcao != null && segundaOpcao != null) {
            if (primeiraOpcao.getAptidao() < segundaOpcao.getAptidao()) {
                disponiveis.add(segundaOpcao);
                return primeiraOpcao;
            }
            else {
                disponiveis.add(primeiraOpcao);
                return segundaOpcao;
            }
        }
        return torneio(disponiveis);
    }
}
