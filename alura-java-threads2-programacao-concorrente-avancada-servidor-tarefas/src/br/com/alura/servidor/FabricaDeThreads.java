package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

/*
 * Talvez o padr�o mais simples de enxergar o Factory Method que utilizamos dentro da nossa f�brica de threads (FabricaDeThreads). Aquele �nico m�todo newThread(..) � um factory method que encapsula a cria��o de uma thread. O pool de threads usa aquela f�brica para criar uma thread, usa o Factory Method.
 */
public class FabricaDeThreads implements ThreadFactory {
	
	private static int numero =1 ;

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(r, "Thread Servidor Tarefas " + numero);
		System.out.println("gerada a thread: "+ numero);
		numero++;
		 //personalizando a thread
		thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
//Obs: Uma thread daemon � uma thread de "servi�o" que ser� automaticamente terminada quando n�o houver mais nenhuma outra thread principal rodando dentro da JVM. 		
		thread.setDaemon(true);
		return thread;
	}

}
