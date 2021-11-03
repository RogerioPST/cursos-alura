package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2 implements Callable<String> {

	private PrintStream respostaParaCliente;

	public ComandoC2(PrintStream respostaParaCliente) {
		this.respostaParaCliente = respostaParaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String call() throws Exception {
		System.out.println("comando c2 chamou o servidor");
		respostaParaCliente.println("processando c2 servidor");
		Thread.sleep(20000);
		//simula um processo meio lento, como se acessasse um Web Service
		int numero = new Random().nextInt(100)+1;

		// throw new RuntimeException("excecao no comando c2");
		// respostaParaCliente.println("comando c2 executado com sucesso");
		System.out.println("servidor finalizou comando c2");
		respostaParaCliente.println("servidor finalizou comando c2");
		return Integer.toString(numero);

	}

}
