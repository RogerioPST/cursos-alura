package leitor.arquivos;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Properties;

public class PrincipalLeitorPropriedades implements UncaughtExceptionHandler{

    public static void main(String[] args) {
        Properties properties = new Properties();
        Thread thread = new Thread(new LeitorPropriedades(properties, "arquivo1.txt"));
        thread.setUncaughtExceptionHandler(new PrincipalLeitorPropriedades());
        thread.start();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        System.out.println("Exceção "+ exception +" capturada na Thread "+thread.getName());        
    }
}