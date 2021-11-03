package br.com.alura.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/*
 * Outro padr�o interessante aplicamos na classe DistribuirTarefas. Apesar da implementa��o simples, ela segue um padr�o chamado de FrontController. Esse padr�o na verdade vem do mundo de desenvolvimento web e representa uma entrada �nica na aplica��o. Na nossa aplica��o todos os "pedidos" dos clientes passam pela classe DistribuirTarefas. Ela centraliza o fluxo, analisa o pedido e decide (controla) o que � para executar. Isso � o papel do controlador ou FrontController.
 */
public class DistribuirTarefasComPriorityBlockingQueue implements Runnable {

	private Socket socket;
	private ServidorTarefasComPriorityBlockingQueue servidor;
	private ExecutorService threadPool;
	private BlockingQueue<ComandoSimulaHTTP> filaComandos;

	public DistribuirTarefasComPriorityBlockingQueue(Socket socket, ServidorTarefasComPriorityBlockingQueue servidor, ExecutorService threadPool, BlockingQueue<ComandoSimulaHTTP> filaComandos) {
		this.socket = socket;
		// TODO Auto-generated constructor stub
		this.servidor = servidor;
		this.threadPool = threadPool;
		this.filaComandos = filaComandos;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			System.out.println("distribuindo tarefas " + socket);

			Scanner entradaCliente = new Scanner(socket.getInputStream());					
			PrintStream respostaParaCliente = new PrintStream(socket.getOutputStream());

			while (entradaCliente.hasNextLine()) {
				String comando = entradaCliente.nextLine();
				System.out.println("comando recebido:  " + comando);

				switch (comando) {
					case "c1": {
						respostaParaCliente.println("confirma��o comando c1");
						/*
						 * Por fim, temos os nossos comandos que seguem o padr�o Command. Um Command encapsula a execu��o de "algo", encapsula alguma a��o ou l�gica. Em alguns casos os comandos s�o chamados de actions e eles realmente possuem todo o c�digo para atender aquele pedido especifico do cliente. Enquanto o controlador analisa o pedido e decide qual comando a usar, o Command realmente possui a l�gica.
						 */
						ComandoC1 c1 = new ComandoC1(respostaParaCliente);
						threadPool.execute(c1);
						break;
	
					}
					case "c2": {
						respostaParaCliente.println("confirma��o comando c2");
						ComandoC2 c2 = new ComandoC2(respostaParaCliente);
						ComandoC2AcessaBanco c2b = new ComandoC2AcessaBanco(respostaParaCliente);
						Future<String> futureWS = threadPool.submit(c2);
						Future<String> futureBanco = threadPool.submit(c2b);						
						
						//vai rodar numa thread separada p n travar recebimento de novos comandos do cliente
						this.threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureWS, futureBanco, respostaParaCliente));
						
						
						break;
	
					}
					case "c3": {
						filaComandos.put(new ComandoSimulaHTTP("ADD", 5, "curso=threads2&dataCriacao=12/06/2016&nivel=avancada"));
						filaComandos.put(new ComandoSimulaHTTP("UPDATE", 3, "curso=threads2&dataCriacao=13/06/2016")); 
						filaComandos.put(new ComandoSimulaHTTP("REMOVE", 1, "id=3"));
						filaComandos.put(new ComandoSimulaHTTP("GET", 2, "id=4"));
						
						respostaParaCliente.println("comando c3 adicionado na fila");						
						break;
	
					}
					case "fim": {
						respostaParaCliente.println("desligando o servidor");
						
//Repare que o acesso ao atributo 'estaRodando' acontece em uma outra thread, e at� poderia 
//acontecer em paralelo. Ser� que isso n�o pode gerar um problema?
						//olhar classe ServidorTeste, q explica a linha acima e a linha abaixo.
						servidor.parar();
						break;
	
					}
					default: {
						respostaParaCliente.println("comando n�o encontrado");
						
	
					}
				}

			}
			respostaParaCliente.close();
			entradaCliente.close();
			// Thread.sleep(20000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}

}
