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
		// As informações que a JVM expõe não param por ai. Também podemos "perguntar"
		// quantos processadores temos disponíveis. Para tal usamos a classe Runtime:
		Runtime runtime = Runtime.getRuntime();
		int qtdProcessadores = runtime.availableProcessors();
		System.out.println("Qtd de processadores: " + qtdProcessadores);

		// Um socket é o ponto-final de um fluxo de comunicação entre duas aplicações,
		// através de uma rede
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
 * A ideia é a seguinte: ao receber o comando no servidor, não vamos processar nada na hora e sim 
 * apenas adicionar o comando em uma fila. A fila é o lugar onde ficam guardados os comandos a serem 
 * processados. Nela, terão alguns consumidores que ficarão lendo os comandos e realmente processando 
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
		// O problema é que o servidor precisa chamar o método accept para cada cliente,
		// e no nosso código chamamos apenas uma vez accept. Como solução, vamos colocar
		// o método dentro de um laço infinito
		while (this.estaRodando.get()) {

			try {
				// esse eh o resultado. qdo o cliente se conecta . a partir desse cara , posso me comunicar
				// O método accept é bloqueante e trava a thread principal. Ou seja, ao rodar, a
				// thread main fica parada até receber uma conexão através de um cliente. Repare
				// também que o retorno desse método é um Socket, que abstrai os detalhes da
				// conexão.
				Socket socket = servidor.accept();
				System.out.println("aceitando novo cliente na porta " + socket.getPort());

				// uma thread p cada cliente
				// Através dessa thread analisaremos os dados enviados pelo cliente e
				// distribuiremos a tarefa. Então, nada mais justo do que chamar essa thread de
				// distribuirTarefas
				DistribuirTarefasComPriorityBlockingQueue db = new DistribuirTarefasComPriorityBlockingQueue(socket, this, threadPool, filaComandos);

				threadPool.execute(db);
				// Thread threadCliente = new Thread(db);
				// threadCliente.start();

				// A classe Thread possui um método estático getAllStackTraces que devolve um
				// conjunto com todas as threads da JVM. Veja o código:
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
