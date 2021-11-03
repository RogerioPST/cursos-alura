package br.com.alura.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//poderia ser um Runnable, pois não devolve nada, mas no caso o Callable tb tem uma forma de informar q n devolve nada q eh o Void
public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {

	private Future<String> futureWS;
	private Future<String> futureBanco;
	private PrintStream respostaParaCliente;

	public JuntaResultadosFutureWSFutureBanco(Future<String> futureWS, Future<String> futureBanco,
			PrintStream respostaParaCliente) {
				this.futureWS = futureWS;
				this.futureBanco = futureBanco;
				this.respostaParaCliente = respostaParaCliente;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Void call()   {
		// TODO Auto-generated method stub
		
		System.out.println("aguardando resultados do future WS e Banco");
		
		try {
		//se passar de do TimeUnit definido abaixo, vai cair no catch/na exceção	
			String numeroMagico = this.futureWS.get(20,TimeUnit.SECONDS );
			String numeroMagicoBanco = this.futureBanco.get(20,TimeUnit.SECONDS );
			
			this.respostaParaCliente.println("resultado comando c2: " + numeroMagico + ", " + numeroMagicoBanco);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			System.out.println("Timeout: cancelando execucao do comando c2, pq demorou mais do q deveria");
			this.respostaParaCliente.println("timeout na execucao do comando c2, pq demorou mais do q deveria");
			this.futureWS.cancel(true);
			this.futureBanco.cancel(true);
		}
		
		System.out.println("finalizou JuntaResultadosFutureWSFutureBanco");
		return null;

	}

}
