Cap2
  	1. A newFixedThreadPool é o pool de threads em que definimos previamente a quantidade de 
  		threads que queremos utilizar. Assim, se por exemplo estabelecermos que queremos no 
  		máximo 4 threads, este número nunca será extrapolado e elas serão reaproveitadas.
	2. A newCachedThreadPool é o pool de threads que cresce dinamicamente de acordo com as 
		solicitações. É ideal quando não sabemos o número exato de quantas threads vamos 
		precisar. O legal deste pool é que ele também diminuí a quantidade de threads 
		disponíveis quando uma thread fica ociosa por mais de 60 segundos.
	3. A newSingleThreadExecutor é o pool de threads que só permite uma única thread.
	4. classe Executors é uma fábrica de pools!
	5. A resposta correta é que o quinto cliente fica bloqueado até que algum dos clientes 
		anteriores libere uma thread para ele utilizar. Quando um cliente liberar uma das threads, 
		o pool de conexões vai pegar esta mesma thread e reaproveita-lá, disponibilizando para o 
		quinto cliente e desbloqueando-o.
	6. olhando quais são os métodos que a interface define não encontramos o método execute que já 
		usamos durante a aula. Isto é porque o ExecutorService estende uma outra interface Executor 
		que possui apenas um método, justamente aquele execute.
	7. Existe mais uma interface, a ScheduledExecutorService que por sua vez estende o ExecutorService:
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(4);
		Através desse pool podemos agendar e executar uma tarefa periodicamente, por exemplo:
		pool.scheduleAtFixedRate(tarefa, 0, 60, TimeUnit.MINUTES); //executamos uma tarefa a cada 60 minutos
Cap3
	8.	threadEnviaComando.join() - join() é usado qdo n queremos executar o codigo da linha abaixo, já q
		o socket será fechado e causará erro de 'socket closed' -podemos indicar à thread main esperar
		a execução enquanto a thread de leitura está rodando. Isso é feito através do método join.
		esse codigo vai falar para a Thread Main soh continuar depois que a
		threaEnviaComando ou threadReceberComando terminar :		
			threadReceberComando.start();
			threadEnviaComando.start();
			threadEnviaComando.join();
			ou threadReceberComando.join();
		OBS: cuidado c Thread.sleep(10000) nesse caso sem o join, pois fechará o socket silenciosamente 
		e parará de enviar msg o servidor:
	8.1. O método thread.join() faz com que a thread que executa espere até a outro acabar: 
		Quando uma thread t2 chama t1.join(), significa que t2 vai esperar t1 finalizar.
	8.2. thread.join(30000); - Isso significa que vamos esperar 30s para se "juntar" a outra 
		thread. Depois dos 30s continuaremos, mesmo se a outra thread não tiver finalizado ainda.
	9. Usar o Runnable através de classes anônimas.
	10.só faz sentido usar um pool de threads quando realmente queremos reaproveitar uma thread
11. (Opcional) - chat - https://www.caelum.com.br/apostila-java-orientacao-objetos/apendice-sockets/
12. (Opcional) Interface Gráfica - https://s3.amazonaws.com/caelum-online-public/threads2/cliente-ui-tarefas.zip
Cap4
	13. volatile e AtomicBoolean - usados p resolver o problema abaixo:
		No caso, o problema q ocorre eh q dessa forma q está o codigo da classe ServidorDeTeste, 
		cada thread tem a sua memoria, tem o seu cache c os valores de 'estaRodando' e n atualizam 
		uma unica variavel 'estaRodando' de uma memoria principal.
		o problema eh q a manipulacao do atributo 'estaRodando' ocorre por outra thread. por isso, 
		dah o problema de cachear o valor p cada thread e a necessidade de usar volatile ou Atomic.
	14. alternativa correta a respeito de como a memória é gerenciada quando variáveis são acessadas 
		de diferentes Threads: Variáveis podem ser mantidas em caches locais das Threads - as Threads 
		fazem uma cópia da memória para a sua cache, deixando assim o acesso mais rápido.
	15. AtomicBoolean para não precisar usar volatile ou syncronized ao acessar a uma variável. A 
		classe faz parte do pacote java.util.concurrent.atomic onde podemos encontrar outras classes, 
		como por exemplo AtomicInteger e AtomicLong.
		https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/atomic/package-summary.html
		No tutorial da Oracle é apresentado um pequeno exemplo como seria uma classe usando syncronized 
		comparado com AtomicInteger:
		https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html
	16. As classes Atomic* poupam a gente de usar volatile diretamente ou criar uma classe com métodos 
		sincronizados. O nome também deixa claro que o acesso é atômico, algo muito mais claro do que a palavra 
		volatile
Cap5
	17.0. - Cada thread possui a sua pilha de métodos.O tratamento de exceções deve ser específico para 
		cada pilha.
	17.  try /catch precisa ser colocado dentro de cada método run e não antes da atribuicao da Thread,
		pq se n, n vai cair no bloco catch fora do metodo run:
		try //aqui n
		Thread thread = new Thread(new Runnable() {
			try// aqui sim
		    public void run() {}
		    catch //aqui sim
	   	})
	   	catch //aqui n
   	17.1 ou p n precisar usar try e catch, usar thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
   	17.2 o problema é q no threadPool, n tem 'setUncaughtExceptionHandler'. Por isso, usamos a classe
   		'FabricaDeThreads' e nela podemos usar o metodo 'setUncaughtExceptionHandler' p cada thread.
   		O pool de threads oferece uma fábrica de threads para personalizar a criação da thread.
   		public class TratadorDeExcecao implements UncaughtExceptionHandler {
   	18. O que acontece quando uma exceção não tratada é lançada dentro de uma Thread?	
   		R. Será propagada até o método run() e encerrará a Thread. Cada thread possui a sua própria pilha. 
   		Quando a exceção é lançada na pilha, ela vai "destruindo" todos os métodos até alguem tratá-la...
   		.. e caso não tratem, encerra a thread:
   	19. Suponha que uma Thread A inicialize uma Thread B, onde ocorre um RuntimeException não tratado. 
   		Quais Threads serão finalizadas com essa exceção?
   		R. Somente a Thread B será finalizada. As Threads A e B possuem suas próprias pilhas de exceções 
   		distintas.
   	20. Quando não usamos uma ThreadFactory explicitamente é utilizada a implementação padrão que podemos
   	 	acessar através: ThreadFactory factory = Executors.defaultThreadFactory();
Cap6
	21. Quando eu precisar que a execução da thread me devolva algo, eu uso um Callable<V>, sendo V o tipo 
		de retorno, String, por ex.: public class ComandoC2 implements Callable<String> {
		obs: Diferente da Runnable, as tarefas com Callable precisam ser executadas pelo ExecutorService, 
		ou seja, através do pool de threads, sempre.
	22. ao contrario do metodo run, o metodo call pode lançar exceção:
		public String call() throws Exception {
	23. o metodo que aceita um Callable não é o 'execute', mas sim 'submit' e devolve um Future, que é
		algo q n está pronto:
		Future<String> futureWS = threadPool.submit(c2);
	24. para resgatar o q o submit acima devolve, usamos o metodo get do futureWS:
		futureWS.get();
		a thread q executa a linha acima fica bloqueada até a outra thread que executa o metodo submit 
		devolver o resultado e assim n vai conseguir receber novos comandos do cliente
		e como eu n quero isso, vai ser usada a classe 'JuntaResultadosFutureWSFutureBanco' c nova thread: 
		this.threadPool.submit(new JuntaResultadosFutureWSFutureBanco(futureWS, futureBanco, respostaParaCliente));
	25. 'JuntaResultadosFutureWSFutureBanco' poderia implementar Runnable, pois não devolve nada, 
		mas no caso o Callable tb tem uma forma de informar q n devolve nada q eh o Void:
		public class JuntaResultadosFutureWSFutureBanco implements Callable<Void> {
		...	
		public Void call()   {
		..
		return null;
		}
	26. se a exec de tarefa referente ao 'futureWS' passar do TimeUnit definido abaixo, vai cair no 
		catch/na exceção:	
		String numeroMagico = this.futureWS.get(20,TimeUnit.SECONDS );
		Caso contrário, vai retornar o resultado p o cliente:
		this.respostaParaCliente.println("resultado comando c2: " + numeroMagico);		
	27. Caso caia no catch, há a possibilidade de cancelar a tarefa relacionada ao futureWS com:
		this.futureWS.cancel(true);	
		Dentro do Callable c2 e c2b, basta aumentar o sleep p ver o erro de timeout dentro do catch.
	28. Se não houver resultado em 10s, o resultadoDoCallable será nulo:
		String resultadoDoCallable = future.get(10, TimeUnit.SECONDS); //esperando por 10s
		O método get() bloqueia a thread/a execução do nosso código  e espera até o resultado aparecer. 
		Tem uma alternativa, podemos chamar o get e esperar por um tempo limitado. Isso é útil quando não 
		podemos garantir que a execução realmente termine com sucesso:
	29. O problema é que a chamada de future.get é bloqueante, isto é, se tivermos mais instâncias da 
		classe Future teremos que esperar cada uma resolver antes de executar a próxima. Como podemos 
		resolver isso?	
		R.: classe JuntaResultadosFutureWSFutureBanco - Podemos criar uma nova thread e, na tarefa dessa 
		thread, chamar o método get() das nossas instâncias de Future
	30. Isso significa que somos obrigados de usar um pool com Callable? E se no meu código não houver a 
		possibilidade de usar um pool de threads?
		Felizmente, há uma solução para resolver esse impasse que se chama FutureTask:
			Callable<String> tarefa = new Tarefa(); //Tarefa implementa Callable
			FutureTask<String> futureTask = new FutureTask<String>(tarefa);
			new Thread(futureTask).start(); //usando Thread puro!!            
			String resultado = futureTask.get();
Cap7
	31. Há métodos que simplesmente travam a thread para disponibilizar um elemento ou tirar um elemento na 
		fila. Isto é importante, pois queremos criar produtores e consumidores para a mesma fila, tudo 
		assincronamente em threads separadas. 
		Há vários implementações da interface BlockingQueue.
		ArrayBlockingQueue possui um limite de elementos.
			private BlockingQueue<String> filaComandos; //thread safe
			this.filaComandos = new ArrayBlockingQueue<String>(2);
	32. this.filaComandos.put(comando);//lembrando, bloqueia se tiver cheia
	33. Só mais uma dica: os métodos offer e poll (que não são bloqueantes) possuem uma versão que recebe 
		um timeout:
			//tenta inserir um novo elemento por 30s
			boolean foi = fila.offer("c3", 30, TimeUnit.SECONDS);COPIAR CÓDIGO
			// tenta pegar um elemento por 30s
			String comando = fila.poll(30, TimeUnit.SECONDS);
	34. Sabemos que ambos os métodos, offer() e put() da interface BlockingQueue inserem um elemento na fila.
		Ao tentar adicionar um elemento na fila "sem vaga", o método put() bloqueia a execução aguardando por 
		uma vaga dentro da lista. Já o método offer() não bloqueia e retorna false caso não haja mais espaço 
		na fila.
		Analogamente há também os métodos poll() e take() para pegar um elemento da fila. O método take() irá 
		esperar a chegada de um novo elemento. Já o método poll() permite que seja estabelecido um Timeout para 
		a espera de um novo elemento.
	35. Considere que três threads estão bloqueadas por terem chamado o método take(), da interface BlockingQueue. 
		Marque a alternativa verdadeira:
		Resposta correta: Ao ser inserido um elemento, uma dessas threads irá ser desbloqueada e irá 	
		receber esse elemento.
		Não sabemos qual thread realmente recebe o elemento, isso depende do escalonador de threads. 
		Só sabemos que UM receberá!
	36. A PriorityBlockingQueue é uma BlockingQueue ilimitada e que só aceita elementos que implementem 
		a interface Comparable (caso contrário, uma exceção do tipo ClassCastException será lançada).
		https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/PriorityBlockingQueue.html
 *
