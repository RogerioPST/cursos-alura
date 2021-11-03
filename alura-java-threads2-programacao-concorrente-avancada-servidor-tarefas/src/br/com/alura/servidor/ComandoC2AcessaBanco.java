package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2AcessaBanco implements Callable<String> {

	private PrintStream respostaParaCliente;

	public ComandoC2AcessaBanco(PrintStream respostaParaCliente) {
		this.respostaParaCliente = respostaParaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String call() throws Exception {
		System.out.println("comando c2 chamou o banco");
		respostaParaCliente.println("processando c2 banco");
		Thread.sleep(65000);
		
		int numero = new Random().nextInt(100)+1;

		// throw new RuntimeException("excecao no comando c2");
		// respostaParaCliente.println("comando c2 executado com sucesso");
		System.out.println("banco finalizou comando c2");
		respostaParaCliente.println("banco finalizou comando c2");
		return Integer.toString(numero);

	}

}
