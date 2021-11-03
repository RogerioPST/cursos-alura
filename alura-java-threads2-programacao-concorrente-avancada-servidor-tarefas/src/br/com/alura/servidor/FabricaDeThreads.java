package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

/*
 * Talvez o padrão mais simples de enxergar o Factory Method que utilizamos dentro da nossa fábrica de threads (FabricaDeThreads). Aquele único método newThread(..) é um factory method que encapsula a criação de uma thread. O pool de threads usa aquela fábrica para criar uma thread, usa o Factory Method.
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
//Obs: Uma thread daemon é uma thread de "serviço" que será automaticamente terminada quando não houver mais nenhuma outra thread principal rodando dentro da JVM. 		
		thread.setDaemon(true);
		return thread;
	}

}
