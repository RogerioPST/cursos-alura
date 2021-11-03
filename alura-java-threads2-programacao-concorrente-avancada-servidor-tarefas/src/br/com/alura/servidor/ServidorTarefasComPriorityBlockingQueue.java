package br.com.alura.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefasComPriorityBlockingQueue {

	private ExecutorService threadPool;
	private ServerSocket servidor;
	//p entender o problema e pq usar AtomicBoolean ou volatile, olhar ServidorDeTeste e ServidorDeTesteComAtomicBoolean
	private AtomicBoolean estaRodando;
	//private volatile boolean estaRodando;
	private BlockingQueue<ComandoSimulaHTTP> filaComandos; //thread safe

	public ServidorTarefasComPriorityBlockingQueue() throws IOException {
		// As informa��es que a JVM exp�e n�o param por ai. Tamb�m podemos "perguntar"
		// quantos processadores temos dispon�veis. Para tal usamos a classe Runtime:
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);

		// Um socket � o ponto-final de um fluxo de comunica��o entre duas aplica��es,
		// atrav�s de uma rede
		System.out.println("----iniciando o servidor----");
		this.servidor = new ServerSocket(12345);

		// dentro do pool, vai ter apenas 2 threads fixos
		// ExecutorService threadPool = Executors.newFixedThreadPool(2);

		// vai criando as threads dinamicamente a medida q for precisando
		//this.threadPool = Executors.newCachedThreadPool();
		//this.threadPool = Executors.newFixedThreadPool(4, new FabricaDeThreads());
		this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreads());
		
		
//this.threadPool = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
//this.threadPool = Executors.newCachedThreadPool(new FabricaDeThreadsUsandoDefaultThreadFactory(Executors.defaultThreadFactory()));
		 
		
		this.estaRodando = new AtomicBoolean(true);
		this.filaComandos = new ArrayBlockingQueue<>(5);
		inicializarConsumidores();

	}
/*
 * A ideia � a seguinte: ao receber o comando no servidor, n�o vamos processar nada na hora e sim 
 * apenas adicionar o comando em uma fila. A fila � o lugar onde ficam guardados os comandos a serem 
 * processados. Nela, ter�o alguns consumidores que ficar�o lendo os comandos e realmente processando 
 * os dados.
 */
private void inicializarConsumidores() {
	int qtdConsumidores = 2;
	for (int i =0; i < qtdConsumidores; i++) {
		TarefaConsumirComPriorityBlockingQueue tarefa = new TarefaConsumirComPriorityBlockingQueue(filaComandos);
		this.threadPool.execute(tarefa);
	}
		
	}

	public static void main(String[] args) throws Exception {
		ServidorTarefasComPriorityBlockingQueue servidor = new ServidorTarefasComPriorityBlockingQueue();
		servidor.rodar();
		servidor.parar();

	}

	public void rodar() throws IOException {
		// O problema � que o servidor precisa chamar o m�todo accept para cada cliente,
		// e no nosso c�digo chamamos apenas uma vez accept. Como solu��o, vamos colocar
		// o m�todo dentro de um la�o infinito
		while (this.estaRodando.get()) {

			try {
				// esse eh o resultado. qdo o cliente se conecta . a partir desse cara , posso me comunicar
				// O m�todo accept � bloqueante e trava a thread principal. Ou seja, ao rodar, a
				// thread main fica parada at� receber uma conex�o atrav�s de um cliente. Repare
				// tamb�m que o retorno desse m�todo � um Socket, que abstrai os detalhes da
				// conex�o.
				Socket socket = servidor.accept();
				System.out.println("aceitando novo cliente na porta " + socket.getPort());

				// uma thread p cada cliente
				// Atrav�s dessa thread analisaremos os dados enviados pelo cliente e
				// distribuiremos a tarefa. Ent�o, nada mais justo do que chamar essa thread de
				// distribuirTarefas
				DistribuirTarefasComPriorityBlockingQueue db = new DistribuirTarefasComPriorityBlockingQueue(socket, this, threadPool, filaComandos);

				threadPool.execute(db);
				// Thread threadCliente = new Thread(db);
				// threadCliente.start();

				// A classe Thread possui um m�todo est�tico getAllStackTraces que devolve um
				// conjunto com todas as threads da JVM. Veja o c�digo:
				// Set<Thread> todasAsThreads = Thread.getAllStackTraces().keySet();

				// for (Thread thread : todasAsThreads) {
				// System.out.println(thread.getName());
				// }
			} catch (SocketException e) {
				System.out.println("SocketException , estah rodando? " + this.estaRodando);
			}

		}

	}

	public void parar() throws IOException {
		servidor.close();
		threadPool.shutdown();
		this.estaRodando.set(false);

	}
}
