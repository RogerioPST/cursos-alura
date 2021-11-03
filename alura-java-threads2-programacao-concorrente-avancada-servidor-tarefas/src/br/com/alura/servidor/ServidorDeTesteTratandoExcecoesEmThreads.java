package br.com.alura.servidor;

public class ServidorDeTesteTratandoExcecoesEmThreads {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTesteTratandoExcecoesEmThreads servidor = new ServidorDeTesteTratandoExcecoesEmThreads();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        
        	
			Thread thread = new Thread(new Runnable() {

		    public void run() {
		        System.out.println("Servidor começando, estaRodando = " + estaRodando );

		        while(!estaRodando) {}
		        
		        if(estaRodando) {
		        	throw new RuntimeException("deu erro na thread...");
		        }
		        
		        

		        System.out.println("Servidor rodando, estaRodando = " + estaRodando );

		        while(estaRodando) {}

		        System.out.println("Servidor terminando, estaRodando = " + estaRodando );
		    }
		});
			
			thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
			thread.start();		
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;        
    }
}