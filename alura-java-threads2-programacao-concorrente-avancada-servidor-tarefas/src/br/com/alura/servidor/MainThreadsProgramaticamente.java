package br.com.alura.servidor;

import java.util.Set;

public class MainThreadsProgramaticamente {
	public static void main(String[] args) {
		/*
		 * No video mostramos a ferramenta JConsole para mostrar todas as threads da JVM. Se a JConsole consegue mostrar as threads, será que a gente também pode? Claro que sim e é relativamente fácil!

		A classe Thread possui um método estático getAllStackTraces que devolve um conjunto com todas as threads da JVM. Veja o código:
		 */
		Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

		for (Thread thread : todasAsThreads) {
		    System.out.println(thread.getName());
		}
		
		/*
		 * Também podemos "perguntar" quantos processadores temos disponíveis. Para tal usamos a classe Runtime:
		 */
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);
	}

}
