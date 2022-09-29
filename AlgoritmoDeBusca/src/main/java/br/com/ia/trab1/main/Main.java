package br.com.ia.trab1.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

  public static int informacoesArquivo(String file) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(file);
    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    Path filePath = Paths.get(file);
    List<String> linhasArquivo = Files.readAllLines(filePath)/* .subList(0, 5) */;
    linhasArquivo.forEach(System.out::println);

    String line;
    int wordCount = 0, characterCount = 0, paraCount = 0, whiteSpaceCount = 0, sentenceCount = 0;

    while ((line = bufferedReader.readLine()) != null) {
      if (line.equals("")) {
        paraCount += 1;
      } else {
        characterCount += line.length();
        String[] words = line.split("\\s+");
        wordCount += words.length;
        whiteSpaceCount += wordCount - 1;
        String[] sentence = line.split("[!?.:]+");
        sentenceCount += sentence.length;
      }
    }
    if (sentenceCount >= 1) {
      paraCount++;
    }

    return sentenceCount;

  }


  public static void main(String[] args) throws IOException {
    String filenameLabirinto = "C:\\Users\\ramir\\OneDrive\\z_Documents_antigo\\PUCRS\\2022-2\\IA\\atividades avaliativas\\trabalho 1\\algoritmoDeBusca\\AlgoritmoDeBusca\\labirinto1.txt";
    informacoesArquivo(filenameLabirinto);

    Labirinto labirinto = Labirinto.getInstance();
    labirinto.Init(filenameLabirinto);

    //System.out.println(labirinto.matriz.get(0).get(0));

    int tamPopulacao = 100;
    int caminhoTotalPorIndividuo = 20;
    int quantidadeDeGeracoes = 10;
    double taxaDeMutacaoParcial = 0.0; // aquela que aroveita o caminho bom
    double taxaDeMutacaoTotal = 0.1; // gera um individuo totalmente novo
    int qntComidas = labirinto.getQntComida();

    AlgoritmoGenetico algGen = new AlgoritmoGenetico(tamPopulacao,
        caminhoTotalPorIndividuo, quantidadeDeGeracoes, taxaDeMutacaoParcial, taxaDeMutacaoTotal,
        qntComidas);
    algGen.executaAlgoritmoGenetico();

        /*Populacao popInical = new Populacao(tamPopulacao);
        popInical.iniciaPopulacao(caminhoTotalPorIndividuo);

        for (Individuos i : popInical.getIndividuos()) {
            for (Direcoes dir : i.getCaminhoIndividuo()) {
                System.out.print(dir + "||");
            }
            System.out.println("");
            System.out.println("=");
        }*/

    // AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(200,10);

  }

}

/*
 * var resultadosPrimeiraSelecao = algoritmo.getListaCromossomos()
 * .parallelStream().map((l) -> {
 * var passosParaAcharTodasComidas = PercorreLabirinto.getQntComida(l, 5);
 * System.out.println("numero de passos: " +
 * passosParaAcharTodasComidas.size());
 * return passosParaAcharTodasComidas;
 * }).collect(Collectors.toList());
 *
 * var min =
 * resultadosPrimeiraSelecao.stream().min(Comparator.comparing(List::size));
 * System.out.println(min.get().size());
*/