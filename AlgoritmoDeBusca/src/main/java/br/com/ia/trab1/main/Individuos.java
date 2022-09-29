package br.com.ia.trab1.main;


public class Individuos {

    private Direcoes[] caminhoIndividuo;
    private int indexNPonto;
    private int comidasColetadas;
<<<<<<< HEAD
    private double aptidao; // definimos aptidao sendo quanto maior o valor, melhor o individuo
    private ArrayList<Posicao> rotaDoIndividuo; // salva as posições validas passadas pelo indivíduo
=======
    private int aptidao; // definimos aptidao sendo quanto maior o valor, melhor o individuo
>>>>>>> parent of 3eb7d04 (Percorrido larinto e gerando aptidao. Implementando eletismo)

    public Individuos(int caminhoTotalPorIndividuo) {
            this.caminhoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
            this.indexNPonto = -1;
            this.aptidao = 0;
    }

    /**
     * 
     * @param caminhoTotalPorIndividuo
     * @param novosCaminhosAleatorios utilizado para novos Individuos que precisam ser aleatorios:
     * Ex populacaoInicial, os movimentos sao aleatorios
     */
    public Individuos(int caminhoTotalPorIndividuo, boolean novosCaminhosAleatorios) { //gera autoamticamente um individuo com os caminhos
        this.caminhoIndividuo = new Direcoes[caminhoTotalPorIndividuo];
        this.indexNPonto = -1;
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

    public void setindexNPonto(int indexNPonto) {
        if(indexNPonto != -1) {
            this.indexNPonto = indexNPonto;
        }
    }

    public void setAptidao(double aptidao) {
        this.aptidao = aptidao;
    }

    public double getAptidao() {
        return aptidao;
    }

    public int getindexNPonto() {
        return indexNPonto;
    }

    public Direcoes[] getCaminhoIndividuo() {
        return caminhoIndividuo;
    }

    @Override
    public String toString() {
        return "Individuos  " +
                "caminhoIndividuo=\n" + caminhoIndividuo +
                ", indexNPonto=\n" + indexNPonto +
                ", aptidao=" + aptidao +
                ' ';
    }
}
