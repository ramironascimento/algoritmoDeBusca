package br.com.ia.trab1;

public class Output {

  private static final boolean LOG_ATIVO = true;

  public static void println(String msg){
    if(LOG_ATIVO) {
      System.out.println(msg);
    }
  }
  public static void print(String msg){
    if(LOG_ATIVO){
      System.out.print(msg);
    }
  }
}
