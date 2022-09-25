package br.com.ia.trab1.main;


public class Individuos {

    private Direcoes[] caminhoIndividuo;
    private int indexUltimaPosValida;
    private int comidasColetadas;
    private int aptidao; // definimos aptidao sendo quanto maior o valor, melhor o individuo

    public Individuos(int caminhoTotalPorIndividuo) {
            this.caminhoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
            this.indexUltimaPosValida = -1;
            this.aptidao = 0;
    }
    public Individuos(int caminhoTotalPorIndividuo, boolean novosCaminhosAleatorios) { //gera autoamticamente um individuo com os caminhos
        this.caminhoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
        this.indexUltimaPosValida = -1;
        this.aptidao = 0; //aptidao em 0

        if(novosCaminhosAleatorios) iniciaCaminhosAleatorios();
    }


    public void iniciaCaminhosAleatorios(){

        for (int i = 0; i < caminhoIndividuo.length; i++) {
            // gerará os caminhos com movimentos aleatorios
            caminhoIndividuo[i] = Direcoes.generateRandomDirecao(); 
            
        }

    }

    public void setComidasColetadas(){
        this.comidasColetadas++;
    }
    public int getComidasColetadas(){
        return this.comidasColetadas;
    }
    public void setCaminhoIndividuo(Direcoes[] caminhoIndividuo) {
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

    public Direcoes[] getCaminhoIndividuo() {
        return caminhoIndividuo;
    }

    @Override
    public String toString() {
        return "Individuos  " +
                "caminhoIndividuo=\n" + caminhoIndividuo +
                ", indexUltimaPosValida=\n" + indexUltimaPosValida +
                ", aptidao=" + aptidao +
                ' ';
    }
}
