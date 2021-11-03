package br.com.alura.servidor;

import java.util.Set;

public class MainThreadsProgramaticamente {
	public static void main(String[] args) {
		/*
		 * No video mostramos a ferramenta JConsole para mostrar todas as threads da JVM. Se a JConsole consegue mostrar as threads, ser� que a gente tamb�m pode? Claro que sim e � relativamente f�cil!

		A classe Thread possui um m�todo est�tico getAllStackTraces que devolve um conjunto com todas as threads da JVM. Veja o c�digo:
		 */
		Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

		for (Thread thread : todasAsThreads) {
		    System.out.println(thread.getName());
		}
		
		/*
		 * Tamb�m podemos "perguntar" quantos processadores temos dispon�veis. Para tal usamos a classe Runtime:
		 */
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);
	}

}
