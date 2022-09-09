package br.com.ia.trab1;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        var labirinto = new Labirinto(ReadFile.ReadFile("labirinto1.txt"));
    }
}