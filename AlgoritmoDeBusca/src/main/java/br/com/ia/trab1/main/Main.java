package br.com.ia.trab1.main;

import java.io.*;
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
                String words[] = line.split("\\s+");
                wordCount += words.length;
                whiteSpaceCount += wordCount - 1;
                String sentence[] = line.split("[!?.:]+");
                sentenceCount += sentence.length;
            }
        }
        if (sentenceCount >= 1) {
            paraCount++;
        }
        System.out.println("\nContador de Palavras -->  " + wordCount);
        System.out.println("Número de Linhas -->  " + sentenceCount);
        System.out.println("Número de Caracteres -->  " + characterCount);
        System.out.println("Número de paragrafos -->  " + paraCount);
        System.out.println("Número de Espaços -->  " + whiteSpaceCount);

        return sentenceCount;

    }
    

    public static void main(String[] args) throws IOException {
        String filenameLabirinto = "D:/Documents/GitHub/algoritmoDeBusca/AlgoritmoDeBusca/labirinto1.txt";
        informacoesArquivo(filenameLabirinto);
        Labirinto labirinto = new Labirinto(filenameLabirinto);
        System.out.println(labirinto.matriz.get(0).get(0));

        
        int tamPopulacao = 10;
        int caminhoTotalPorIndividuo = 20;
        int quantidadeDeGeracoes=100000;
        double taxaDeMutacaoParcial=0.0; // aquela que aroveita o caminho bom
        double taxaDeMutacaoTotal=0.01; // gera um individuo totalmente novo
        int qntComidas = labirinto.getQntComida();

        AlgoritmoGenetico algGen = new AlgoritmoGenetico(tamPopulacao, 
        caminhoTotalPorIndividuo, quantidadeDeGeracoes, taxaDeMutacaoParcial, taxaDeMutacaoTotal, qntComidas);

        Populacao popInical = new Populacao(tamPopulacao);
        popInical.iniciaPopulacao(caminhoTotalPorIndividuo);

        for (Individuos i : popInical.getIndividuos()) {
            for (Direcoes dir : i.getCaminhoIndividuo()) {
                System.out.print(dir + "||");
            }
            System.out.println("");
            System.out.println("=");
        }

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
