package br.com.alura.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 Cap2
  	1. A newFixedThreadPool � o pool de threads em que definimos previamente a quantidade de 
  		threads que queremos utilizar. Assim, se por exemplo estabelecermos que queremos no 
  		m�ximo 4 threads, este n�mero nunca ser� extrapolado e elas ser�o reaproveitadas.
	2. A newCachedThreadPool � o pool de threads que cresce dinamicamente de acordo com as 
		solicita��es. � ideal quando n�o sabemos o n�mero exato de quantas threads vamos 
		precisar. O legal deste pool � que ele tamb�m diminu� a quantidade de threads 
		dispon�veis quando uma thread fica ociosa por mais de 60 segundos.
	3. A newSingleThreadExecutor � o pool de threads que s� permite uma �nica thread.
	4. classe Executors � uma f�brica de pools!
	5. A resposta correta � que o quinto cliente fica bloqueado at� que algum dos clientes 
		anteriores libere uma thread para ele utilizar. Quando um cliente liberar uma das threads, 
		o pool de conex�es vai pegar esta mesma thread e reaproveita-l�, disponibilizando para o 
		quinto cliente e desbloqueando-o.
	6. olhando quais s�o os m�todos que a interface define n�o encontramos o m�todo execute que j� 
		usamos durante a aula. Isto � porque o ExecutorService estende uma outra interface Executor 
		que possui apenas um m�todo, justamente aquele execute.
	7. Existe mais uma interface, a ScheduledExecutorService que por sua vez estende o ExecutorService:
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
		Atrav�s desse pool podemos agendar e executar uma tarefa periodicamente, por exemplo:
		pool.scheduleAtFixedRate(tarefa, 0, 60, TimeUnit.MINUTES); //executamos uma tarefa a cada 60 minutos
Cap3
	8.	threadEnviaComando.join() - join() � usado qdo n queremos executar o codigo da linha abaixo, j� q
		o socket ser� fechado e causar� erro de 'socket closed' -podemos indicar � thread main esperar
		a execu��o enquanto a thread de leitura est� rodando. Isso � feito atrav�s do m�todo join.
		esse codigo vai falar para a Thread Main soh continuar depois que a
		threaEnviaComando ou threadReceberComando terminar :		
			threadReceberComando.start();
			threadEnviaComando.start();
			threadEnviaComando.join();
			ou threadReceberComando.join();
		OBS: cuidado c Thread.sleep(10000) nesse caso sem o join, pois fechar� o socket silenciosamente 
		e parar� de enviar msg o servidor:
	8.1. O m�todo thread.join() faz com que a thread que executa espere at� a outro acabar: 
		Quando uma thread t2 chama t1.join(), significa que t2 vai esperar t1 finalizar.
	8.2. thread.join(30000); - Isso significa que vamos esperar 30s para se "juntar" a outra 
		thread. Depois dos 30s continuaremos, mesmo se a outra thread n�o tiver finalizado ainda.
	9. Usar o Runnable atrav�s de classes an�nimas.
	10.s� faz sentido usar um pool de threads quando realmente queremos reaproveitar uma thread
11. (Opcional) - chat - https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-sockets/
12. (Opcional) Interface Gr�fica - https://s3.amazonaws.com/caelum-online-public/threads2/cliente-ui-tarefas.zip
Cap4
	13. volatile e AtomicBoolean - usados p resolver o problema abaixo:
		No caso, o problema q ocorre eh q dessa forma q est� o codigo da classe ServidorDeTeste, 
		cada thread tem a sua memoria, tem o seu cache c os valores de 'estaRodando' e n atualizam 
		uma unica variavel 'estaRodando' de uma memoria principal.
		o problema eh q a manipulacao do atributo 'estaRodando' ocorre por outra thread. por isso, 
		dah o problema de cachear o valor p cada thread e a necessidade de usar volatile ou Atomic.
	14. alternativa correta a respeito de como a mem�ria � gerenciada quando vari�veis s�o acessadas 
		de diferentes Threads: Vari�veis podem ser mantidas em caches locais das Threads - as Threads 
		fazem uma c�pia da mem�ria para a sua cache, deixando assim o acesso mais r�pido.
	15. AtomicBoolean para n�o precisar usar volatile ou syncronized ao acessar a uma vari�vel. A 
		classe faz parte do pacote java.util.concurrent.atomic onde podemos encontrar outras classes, 
		como por exemplo AtomicInteger e AtomicLong.
		https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html
		No tutorial da Oracle � apresentado um pequeno exemplo como seria uma classe usando syncronized 
		comparado com AtomicInteger:
		https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html
	16. As classes Atomic* poupam a gente de usar volatile diretamente ou criar uma classe com m�todos 
		sincronizados. O nome tamb�m deixa claro que o acesso � at�mico, algo muito mais claro do que a palavra 
		volatile
Cap5
	17.0. - Cada thread possui a sua pilha de m�todos.O tratamento de exce��es deve ser espec�fico para 
		cada pilha.
	17.  try /catch precisa ser colocado dentro de cada m�todo run e n�o antes da atribuicao da Thread,
		pq se n, n vai cair no bloco catch fora do metodo run:
		try //aqui n
		Thread thread = new Thread(new Runnable() {
			try// aqui sim
		    public void run() {}
		    catch //aqui sim
	   	})
	   	catch //aqui n
   	17.1 ou p n precisar usar try e catch, usar thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
   	17.2 o problema � q no threadPool, n tem 'setUncaughtExceptionHandler'. Por isso, usamos a classe
   		'FabricaDeThreads' e nela podemos usar o metodo 'setUncaughtExceptionHandler' p cada thread.
   		O pool de threads oferece uma f�brica de threads para personalizar a cria��o da thread.
   		public class TratadorDeExcecao implements UncaughtExceptionHandler {
   	18. O que acontece quando uma exce��o n�o tratada � lan�ada dentro de uma Thread?	
   		R. Ser� propagada at� o m�todo run() e encerrar� a Thread. Cada thread possui a sua pr�pria pilha. 
   		Quando a exce��o � lan�ada na pilha, ela vai "destruindo" todos os m�todos at� alguem trat�-la...
   		.. e caso n�o tratem, encerra a thread:
   	19. Suponha que uma Thread A inicialize uma Thread B, onde ocorre um RuntimeException n�o tratado. 
   		Quais Threads ser�o finalizadas com essa exce��o?
   		R. Somente a Thread B ser� finalizada. As Threads A e B possuem suas pr�prias pilhas de exce��es 
   		distintas.
   	20. Quando n�o usamos uma ThreadFactory explicitamente � utilizada a implementa��o padr�o que podemos
   	 	acessar atrav�s: ThreadFactory factory = Executors.defaultThreadFactory();
Cap6
	21. Quando eu precisar que a execu��o da thread me devolva algo, eu uso um Callable<V>, sendo V o tipo 
		de retorno, String, por ex.: public class ComandoC2 implements Callable<String> {
		obs: Diferente da Runnable, as tarefas com Callable precisam ser executadas pelo ExecutorService, 
		ou seja, atrav�s do pool de threads, sempre.
	22. ao contrario do metodo run, o metodo call pode lan�ar exce��o:
		public String call() throws Exception {
	23. o metodo que aceita um Callable n�o � o 'execute', mas sim 'submit' e devolve um Future, que �
		algo q n est� pronto:
		Future<String> futureWS = threadPool.submit(c2);
	24. para resgatar o q o submit acima devolve, usamos o metodo get do futureWS:
		futureWS.get();
		a thread q executa a linha acima fica bloqueada at� a outra thread que executa o metodo submit 
		devolver o resultado e assim n vai conseguir receber novos comandos do cliente
		e como eu n quero isso, vai ser usada a classe 'JuntaResultadosFutureWSFutureBanco' c nova thread: 
		this.threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureWS, futureBanco, respostaParaCliente));
	25. 'JuntaResultadosFutureWSFutureBanco' poderia implementar Runnable, pois n�o devolve nada, 
		mas no caso o Callable tb tem uma forma de informar q n devolve nada q eh o Void:
		public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {
		...	
		public Void call()   {
		..
		return null;
		}
	26. se a exec de tarefa referente ao 'futureWS' passar do TimeUnit definido abaixo, vai cair no 
		catch/na exce��o:	
		String numeroMagico = this.futureWS.get(20,TimeUnit.SECONDS );
		Caso contr�rio, vai retornar o resultado p o cliente:
		this.respostaParaCliente.println("resultado comando c2: " + numeroMagico);		
	27. Caso caia no catch, h� a possibilidade de cancelar a tarefa relacionada ao futureWS com:
		this.futureWS.cancel(true);	
		Dentro do Callable c2 e c2b, basta aumentar o sleep p ver o erro de timeout dentro do catch.
	28. Se n�o houver resultado em 10s, o resultadoDoCallable ser� nulo:
		String resultadoDoCallable = future.get(10, TimeUnit.SECONDS); //esperando por 10s
		O m�todo get() bloqueia a thread/a execu��o do nosso c�digo  e espera at� o resultado aparecer. 
		Tem uma alternativa, podemos chamar o get e esperar por um tempo limitado. Isso � �til quando n�o 
		podemos garantir que a execu��o realmente termine com sucesso:
	29. O problema � que a chamada de future.get � bloqueante, isto �, se tivermos mais inst�ncias da 
		classe Future teremos que esperar cada uma resolver antes de executar a pr�xima. Como podemos 
		resolver isso?	
		R.: classe JuntaResultadosFutureWSFutureBanco - Podemos criar uma nova thread e, na tarefa dessa 
		thread, chamar o m�todo get() das nossas inst�ncias de Future
	30. Isso significa que somos obrigados de usar um pool com Callable? E se no meu c�digo n�o houver a 
		possibilidade de usar um pool de threads?
		Felizmente, h� uma solu��o para resolver esse impasse que se chama FutureTask:
			Callable<String> tarefa = new Tarefa(); //Tarefa implementa Callable
			FutureTask<String> futureTask = new FutureTask<String>(tarefa);
			new Thread(futureTask).start(); //usando Thread puro!!            
			String resultado = futureTask.get();
Cap7
	31. H� m�todos que simplesmente travam a thread para disponibilizar um elemento ou tirar um elemento na 
		fila. Isto � importante, pois queremos criar produtores e consumidores para a mesma fila, tudo 
		assincronamente em threads separadas. 
		H� v�rios implementa��es da interface BlockingQueue.
		ArrayBlockingQueue possui um limite de elementos.
			private BlockingQueue<String> filaComandos; //thread safe
			this.filaComandos = new ArrayBlockingQueue<String>(2);
	32. this.filaComandos.put(comando);//lembrando, bloqueia se tiver cheia
	33. S� mais uma dica: os m�todos offer e poll (que n�o s�o bloqueantes) possuem uma vers�o que recebe 
		um timeout:
			//tenta inserir um novo elemento por 30s
			boolean foi = fila.offer("c3", 30, TimeUnit.SECONDS);COPIAR C�DIGO
			// tenta pegar um elemento por 30s
			String comando = fila.poll(30, TimeUnit.SECONDS);
	34. Sabemos que ambos os m�todos, offer() e put() da interface BlockingQueue inserem um elemento na fila.
		Ao tentar adicionar um elemento na fila "sem vaga", o m�todo put() bloqueia a execu��o aguardando por 
		uma vaga dentro da lista. J� o m�todo offer() n�o bloqueia e retorna false caso n�o haja mais espa�o 
		na fila.
		Analogamente h� tamb�m os m�todos poll() e take() para pegar um elemento da fila. O m�todo take() ir� 
		esperar a chegada de um novo elemento. J� o m�todo poll() permite que seja estabelecido um Timeout para 
		a espera de um novo elemento.
	35. Considere que tr�s threads est�o bloqueadas por terem chamado o m�todo take(), da interface BlockingQueue. 
		Marque a alternativa verdadeira:
		Resposta correta: Ao ser inserido um elemento, uma dessas threads ir� ser desbloqueada e ir� 	
		receber esse elemento.
		N�o sabemos qual thread realmente recebe o elemento, isso depende do escalonador de threads. 
		S� sabemos que UM receber�!
	36. A PriorityBlockingQueue � uma BlockingQueue ilimitada e que s� aceita elementos que implementem 
		a interface Comparable (caso contr�rio, uma exce��o do tipo ClassCastException ser� lan�ada).
		https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html
 */
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ServidorTarefasComExplicacaoSobreThread {
public static void main(String[] args) throws Exception {
	System.out.println(" ---- Iniciando o servidor -----");
	ServerSocket servidor = new ServerSocket(12347);
	
	//ExecutorService threadPool = Executors.newFixedThreadPool(2);
	//usar preferencialmente a opcao de cached, pois TAMBEM reaproveita threads e n limita.
/*
 * A quantidade de threads cresce � medida que a demanda aumenta. O legal � que o pool tamb�m 
 * diminui a quantidade quando uma thread fica ociosa mais de 60 segundos.
 */
	ExecutorService threadPool = Executors.newCachedThreadPool();
	
	while(true) {	
/*
 * O m�todo accept � bloqueante e trava a thread principal. Ou seja, ao rodar, a thread main 
 * fica parada at� receber uma conex�o atrav�s de um cliente. Repare tamb�m que o retorno 
 * desse m�todo � um Socket, que abstrai os detalhes da conex�o.
 */
		Socket socket = servidor.accept();
/*
 * acontece a conexao na porta 12347 acima, mas depois q o servidor aceita a conexao
 * c o cliente, eles negociam uma porta diferente p cada cliente. porta 80 http eh 
 * assim tb.
 * 
 * Os clientes fazem a conex�o atrav�s da porta 12347, j� que esta � a porta para a 
 * comunica��o com o nosso servidor. Ap�s isto, a comunica��o do servidor para o 
 * cliente � feita atrav�s de uma porta especifica para cada cliente.		
 */
		System.out.println("aceitando novo cliente na porta: " + socket.getPort());
		
		DistribuirTarefasInicial distribuir = new DistribuirTarefasInicial(socket);
/*
 * � um gerenciador de objetos do tipo thread, que � capaz de limitar a quantidade de threads 
 * al�m de fazer um reaproveitamento das mesmas,n�o tendo o gasto de CPU de criar uma nova 
 * thread para cada cliente que chega no servidor..
 */
		threadPool.execute(distribuir);		
		
		
/*
 * metodo threadPool.execute(distribuir) substitui as 2 linhas abaixo;		
 */
		//Thread t1 = new Thread(distribuir);
		//t1.start();
		
		//Thread.sleep(20000);
//System.out.println("fim do sleep - usado p teste antes de ter a thread distribuirTarefas");
		
	}
	
	//precisamos realizar um threadPool.shutdown(), por ex, p isso, acrescentar as linhas, mas
	//ambos os codigos abaixo n serao alcan�ados, pq estamos c la�o while(true). p resolver esse 
	//problema, vamos criar a variavel estaRodando, mas aih vao surgir problemas c serao explicados
//	na classe ServidorDeTeste. p ver o problema, descomente as linhas abaixo:
	//threadPool.shutdown();
	//servidor.close();
	//obs: a partir daqui, olhar a classe ServidorTarefas atual/final do curso..
}
}
