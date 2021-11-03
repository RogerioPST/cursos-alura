package br.com.alura.servidor;

public class ServidorDeTesteTratandoExcecoesEmThreadsJava8 {

    private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTesteTratandoExcecoesEmThreadsJava8 servidor = new ServidorDeTesteTratandoExcecoesEmThreadsJava8();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        try {
        	new Thread( () -> { 
			        System.out.println("Servidor começando, estaRodando = " + estaRodando );

			        while(!estaRodando) {}
			        
			        if(estaRodando) {
			        	throw new RuntimeException("deu erro na thread...");
			        }
			        
			        

			        System.out.println("Servidor rodando, estaRodando = " + estaRodando );

			        while(estaRodando) {}

			        System.out.println("Servidor terminando, estaRodando = " + estaRodando );
			    }
			).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("catch na thread main :" + e.getMessage());
			
		}
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