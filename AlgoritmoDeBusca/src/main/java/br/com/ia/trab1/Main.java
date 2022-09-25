package br.com.ia.trab1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var labirinto = new Labirinto(ReadFile.ReadFile("labirinto1.txt"));
    }
}