package br.com.ia.trab1;

public class Posicao {
    private int x;
    private int y;
    private TipoConteudo tipo;

    public Posicao(int x, int y, TipoConteudo tipo) {
        this.x = x;
        this.y = y;
        this.tipo = tipo;
    }

    public TipoConteudo getTipo() {
        return tipo;
    }
}
