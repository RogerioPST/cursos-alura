package br.com.alura.servidor;

import java.io.PrintStream;

/*
 * Por fim, temos os nossos comandos que seguem o padrão Command. Um Command encapsula a execução de "algo", encapsula alguma ação ou lógica. Em alguns casos os comandos são chamados de actions e eles realmente possuem todo o código para atender aquele pedido especifico do cliente. Enquanto o controlador analisa o pedido e decide qual comando a usar, o Command realmente possui a lógica.
 */
public class ComandoC1 implements Runnable {

	private PrintStream respostaParaCliente;

	public ComandoC1(PrintStream respostaParaCliente) {
		this.respostaParaCliente = respostaParaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("executando comando c1");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		throw new RuntimeException(e);
		}
		throw new RuntimeException("EXCECAO NO C1");
		//respostaParaCliente.println("comando c1 executado com sucesso");
		
	}

}
