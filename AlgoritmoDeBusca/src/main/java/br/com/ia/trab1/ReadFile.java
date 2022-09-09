package br.com.ia.trab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    public static String ReadFile(String file) throws IOException {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(file)));
        return data;
    }

}
