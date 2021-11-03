package br.com.alura.servidor;

import java.util.concurrent.atomic.AtomicBoolean;

/*
 * a motivação dessa classe: tem dois clientes conectados e ambos enviam o cmd 'fim' e podem acontecer
 * coisas inesperadas e essa classe tenta simular/simula exatamente esse problema q pode ocorrer.
 * 
 * No caso, o problema q ocorre eh q dessa forma q está o codigo, cada thread tem a sua memoria,
 * tem o seu cache c os valores de 'estaRodando' e n atualizam uma unica variavel 'estaRodando'
 * de uma memoria principal.
 * 
 * Se eu quiser dizer p o Java usar essa memoria unica p variavel 'estaRodando', eu uso volatile 
 * ou AtomicBoolean
 */
public class ServidorDeTesteComAtomicBoolean {

	//descomentar a linha abaixo p ver o problema ocorrer.
	//private boolean estaRodando = false;	
    
    // p fazer o projeto funcionar , descomentar a linha abaixo ou usar AtomicBoolean
    //private volatile boolean estaRodando = false;
	private AtomicBoolean estaRodando = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTesteComAtomicBoolean servidor = new ServidorDeTesteComAtomicBoolean();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Servidor começando, estaRodando = " + estaRodando );

                while(!estaRodando.get()) {}

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando.get()) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            }
        }).start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando.set(true);

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando.set(false);        
    }
}