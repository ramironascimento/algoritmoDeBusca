package br.com.ia.trab1.main;


public class Individuos {

    private int[] caminhoIndividuo;
    private int indexUltimaPosValida;
    private int aptidao;

    public Individuos(int caminhoIndividuo) {
            this.caminhoIndividuo = new int[caminhoIndividuo];
            this.indexUltimaPosValida = -1;
            this.aptidao = Integer.parseInt(null);
    }

    public void setCaminhoIndividuo(int[] caminhoIndividuo) {
        this.caminhoIndividuo = caminhoIndividuo;
    }

    public void setIndexUltimaPosValida(int indexUltimaPosValida) {
        if(indexUltimaPosValida != -1) {
            this.indexUltimaPosValida = indexUltimaPosValida;
        }
    }

    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    public double getAptidao() {
        return aptidao;
    }

    public int getIndexUltimaPosValida() {
        return indexUltimaPosValida;
    }

    public int[] getCaminhoIndividuo() {
        return caminhoIndividuo;
    }

    @Override
    public String toString() {
        return "Individuos  " +
                "caminhoIndividuo=\n" + caminhoIndividuo +
                ", indexUltimaPosValida=\n" + indexUltimaPosValida +
                ", aptidao=\n" + aptidao +
                ' ';
    }
}
