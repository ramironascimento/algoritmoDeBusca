package br.com.ia.trab1;

import com.sun.nio.sctp.IllegalReceiveException;

import java.util.ArrayList;
import java.util.List;

public class Labirinto {

    private List<List<Posicao>> matriz;

    public Labirinto(String file) {
        this.matriz = new ArrayList<>();
        preencherMatriz(file);
    }

    public void preencherMatriz(String file) {
        var fileLines = file.replace("\r", "").split("\n");
        var xy = Integer.parseInt(fileLines[0]);
        for (int i = 1; i <= xy; i++) {
            this.matriz.add(new ArrayList<>());
            var columns = fileLines[i].split(" ");
            for (int j = 0; j < xy; j++) {
                switch (columns[j]) {
                    case "E":
                        matriz.get(i - 1).add(new Posicao(i, j, TipoConteudo.INICIO));
                        break;
                    case "1":
                        matriz.get(i - 1).add(new Posicao(i, j, TipoConteudo.PAREDE));
                        break;
                    case "0":
                        matriz.get(i - 1).add(new Posicao(i, j, TipoConteudo.CAMINHO));
                        break;
                    case "C":
                        matriz.get(i - 1).add(new Posicao(i, j, TipoConteudo.COMIDA));
                        break;
                    default:
                        throw new IllegalReceiveException("valor da posicao da matriz invalido.");
                }
            }
            this.matriz
                .get(i - 1).stream()
                    .forEach(p -> System.out.print(p.getTipo()+ " "));
            System.out.println("");
        }

    }
}
