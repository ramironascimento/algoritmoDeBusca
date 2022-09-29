package br.com.ia.trab1.main;

import java.util.List;
import java.util.Random;

public class Selecao {

    private static final Random rnd = new Random();

    public static Individuo elitismo(Populacao populacao) {
        Individuo apropriado = new Individuo();
        int menorAptidao = -1;
        var populacaoIndividuos = populacao.getIndividuos();
        for(int i = 0; i < populacaoIndividuos.length; i ++) {
            if (populacaoIndividuos[i].getAptidao() < menorAptidao) {
                apropriado = populacaoIndividuos[i];
                menorAptidao = (int) populacaoIndividuos[i].getAptidao();
            }
            Individuo escolhido = new Individuo(apropriado.getmovimentosDoIndividuo().length);
            escolhido.setmovimentosDoIndividuo(apropriado.getmovimentosDoIndividuo()/*clone()*/);
            return escolhido;
        }
        return elitismo(populacao);
    }


    public static Individuo torneio(List<Individuo> disponiveis) {
        Individuo primeiraOpcao;
        Individuo segundaOpcao;
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
