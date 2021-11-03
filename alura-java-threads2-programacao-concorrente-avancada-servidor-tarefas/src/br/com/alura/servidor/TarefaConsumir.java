package br.com.alura.servidor;

import java.util.concurrent.BlockingQueue;

public class TarefaConsumir implements Runnable{
	
	private BlockingQueue<String> filaComandos;
	
	public TarefaConsumir(BlockingQueue<String> filaComandos) {
		
		this.filaComandos = filaComandos;
	}
	
	@Override
	public void run() {
		
		try {
			String comando = null;
		//comando = filaComandos.take() //desse jeito, sem while, soh pegava o cmd e parava
			//por isso, precisa do while
			while((comando = filaComandos.take()) != null) {
				
				System.out.println("consumindo o comando c3: " + comando + ", " + Thread.currentThread().getName());
			/*
			 * Nesse caso, as duas threads consumidores est�o ocupadas ainda, ent�o os comandos ficam guardados na 
			 * fila at� uma thread vir tirar. O servidor confirma apenas o recebimento do comando mas n�o consume 
			 * ainda. Depois de 5s as threads est�o sendo liberadas e come�am consumir esses comandos tamb�m:
			 */
				Thread.sleep(5000);
			};
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		
	}

}
