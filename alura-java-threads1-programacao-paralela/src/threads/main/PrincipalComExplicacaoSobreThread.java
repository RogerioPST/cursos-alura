package threads.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import threads.runnable.TarefaLimpeza;
import threads.runnable.TarefaTeste;

/*Capitulo1
1. Através das Threads podemos executar tarefas em paralelo;
2. Uma classe que implementa a interface Runnable define a tarefa que o Thread executará;
3. O construtor da classe Thread recebe esse Runnable;
4. Devemos inicializar uma Thread explicitamente através do método start();
5. Através do Thread.sleep(millis) podemos mandar uma thread dormir.
Capitulo2
6. Java suporta Threads nativamente
7. Java mapeia os Threads para o Sistema Operacional
8. A ordem de execução depende da JVM e não do nosso código
Capitulo3
9. Thread pode ter um nome - Thread.currentThread().getName();
10. Podemos pegar o Thread atual através do método Thread.currentThread();
11. p q metodo n seja executado por 2 Threads ao mesmo tempo, utilizar um bloco synchronized	
11.1 - synchronized - operação atômica - Cuja execução não pode ser interrompida na metade -
	feita através de mutex, que nada mais é do que a chave do objeto. 
11.2 - sincronize o acesso ao codigo do banheiro - synchronized(this) { <-> chave (lock)
11.3 - A chave (lock) que a thread obtém pode ser chamada também de lock implícito ou 
	lock intrínseco. Também existem formas de usar uma chave explicitamente (programaticamente) 
	através de uma classe chamada ReentrantLock, onde você tem 
	métodos para obter a chave (tryLock) e para devolver a chave (unlock).	
12 - Certamente há algumas diferenças que podemos examinar. Uma das principais é a 
	possibilidade de se criar um lock com timeout usando uma sobrecarga do método tryLock.
	boolean locked = lock.tryLock(5, TimeUnit.SECONDS); //5s
	Com esse método esperamos até cinco segundos e receberemos true caso o lock for obtido. 
	Caso contrário, receberemos false. Como desvantagem, há o fato de o programador ter a 
	responsabilidade de liberar o lock (unlock()).
13. O synchronized significa que bloqueamos o objeto para outros threads (as outras threads 
	ficam como blocked); 	
14. Multiplicador extends Thread { -> n devemos usar, mas pode extender Thread tb ao inves de implementar Runnable
	https://blog.caelum.com.br/como-nao-aprender-orientacao-a-objetos-heranca/ 
Capitulo4
15. public synchronized void adicionaElementos(String elemento) { é a mesma coisa q
	synchronized(this) qdo o corpo todo do metodo deve estar synchronized
16. ArrayList n eh thread safe (Thread safe significa que o código funciona corretamente mesmo com vários threads 
	compartilhando o objeto - n tem synchronized no metodo add, get, por ex ), 
16.1. Vector e Collections.synchronizedList(new ArrayList<String>() são THREAD SAFE.
17. Para os mapas (Map) a antiga classe Hashtable e uma implementação mais recente e 
	performática de mapas, a classe ConcurrentHashMap são THREAD SAFE.
18. Para Sets, usar Set conjunto = Collections.synchronizedSet(new HashSet()) são THREAD SAFE;
19. A implementação não thread-safe da fila é a LinkedList (sim, uma LinkedList é uma 
	lista e também é uma fila!). As versões thread-safe são as classes *BlockingQueue, por 
	exemplo ArrayBlockingQueue.
Capitulo5	
20. this.wait() devolve a chave do banheiro e fica esperando ser notificado p a thread sair do 
	estado de wait. A JVM nunca termina pois ainda há threads esperando (status waiting).
	Se n sair desse estado waiting, por ex, temos um deadlock. É preciso acordar/notificar as 
	threads para continuar executar o programa.
	Quando chamamos wait() a thread devolve a chave e fica aguardando. No vocabulário do 
	mundo de threads, essa condição também é chamado de guarded block. Em outras palavras, 
	a thread fica bloqueada aguardando um sinal/notificação 
21. Você se lembra da ferramenta jconsole? Usamos-a para mostrar os threads da JVM. Com a JVM 
	ainda rodando, vamos subir o jconsole. O interessante é que podemos ver estado de cada thread. 
	Por exemplo, o thread João está no estado WAITING
22. Status das threads:
	NEW Uma thread foi criada mas ainda não foi iniciada.
	RUNNABLE A thread está rodando dentro da JVM.
	BLOCKED A thread foi bloqueada pois não conseguiu pegar a chave.
	WAITING A thread está esperando this.notify ou notifyAll, pois foi chamado this.wait(). Vai
	executar na linha abaixo de 'this.wait()'.
	TIMED_WAITING A thread está esperando pois foi chamado this.wait(milis) ou Thread.sleep(milis).
	TERMINATED A thread está finalizada.
23. IllegalMonitorStateException!
	Analisando esses dados, podemos ver que recebemos uma exceção porque chamamos o 
	método notifyAll(). Qual é o problema? Pensando na vida real, faz sentido alguém 
	limpando o banheiro quando ele está ocupado? Claro que não! E no mundo Java isso 
	não é diferente... Só podemos limpar o banheiro e notificar os outros threads quando 
	estamos com a chave em mãos. Ou seja, só podemos chamar notifyAll() dentro de um 
	bloco sincronizado.
	A documentação da exceção diz: Thrown to indicate that a thread has attempted to 
	wait on an object's monitor or to notify other threads waiting on an object's monitor 
	without owning the specified monitor (Lançado para indicar que uma thread tentou 
	esperar no monitor de um objeto ou notificar outros threads em espera no monitor 
	de um objeto sem possuir o monitor especificado).*
	A palavra monitor podemos traduzir como chave do objeto.
24. Como fazer com que uma thread A espere a execução da thread B?
	R.: Coloque um wait() na Thread A e um notify() na Thread B.
25. é preciso "acordar" ou "notificar as threads" que estão no estado waiting. Essa 
	notificação faremos através de uma nova thread, no caso da limpeza do banheiro.
26. Você pode definir que threads dependem de outros. Em outras palavras, há threads 
	que só deveriam rodar enquanto outras existem. Queremos que a máquina virtual 
	automaticamente termine a thread de limpeza quando não existir mais nenhum convidado. 
	Esse tipo de thread se chama Daemon - limpeza.setDaemon(true)
27. Threads daemon são como prestadores de serviços para outras threads. Elas são usadas 
	para dar apoio às tarefas e só são necessárias rodar quando as threads "normais" ainda 
	estão sendo executadas. Uma thread daemon não impede a JVM de terminar desde que não 
	existem mais threads principais em execução. Um exemplo de uma thread daemon é o 
	coletor de lixo da JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
	Para definir uma thread como daemon basta usar o método setDaemon(boolean) antes de 
	inicializar.
	existem daemon threads para executar tarefas ou serviços secundários.
	daemons serão automaticamente desligados quando todas as outras threads terminarem
28. Será que não faz sentido dar preferência à limpeza quando tem muitos convidados, sabendo 
	que cada convidado quer um banheiro limpo?
	R.: limpeza.setPriority(10) - (valor de 1 (Thread.MIN_PRIORITY) a 10 (Thread.MAX_PRIORITY))
	significa que se não usamos nenhuma prioridade explícita, a thread vai assumir o valor 5 
	para a prioridade
Capitulo6
29. Temos que ter cuidado para não mandar um thread esperar quando não há necessidade
 */
public class PrincipalComExplicacaoSobreThread {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("isso eh um thread main");
		
		//Collections.synchronizedList(new ArrayList<String>());
		ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
		Thread teste = new Thread(new TarefaTeste(concurrentHashMap), "Teste");
		teste.start();
		Thread.sleep(50000);
	}

}
