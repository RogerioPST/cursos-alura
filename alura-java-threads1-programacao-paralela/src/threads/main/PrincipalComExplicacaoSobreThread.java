package threads.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import threads.runnable.TarefaLimpeza;
import threads.runnable.TarefaTeste;

/*Capitulo1
1. Atrav�s das Threads podemos executar tarefas em paralelo;
2. Uma classe que implementa a interface Runnable define a tarefa que o Thread executar�;
3. O construtor da classe Thread recebe esse Runnable;
4. Devemos inicializar uma Thread explicitamente atrav�s do m�todo start();
5. Atrav�s do Thread.sleep(millis) podemos mandar uma thread dormir.
Capitulo2
6. Java suporta Threads nativamente
7. Java mapeia os Threads para o Sistema Operacional
8. A ordem de execu��o depende da JVM e n�o do nosso c�digo
Capitulo3
9. Thread pode ter um nome - Thread.currentThread().getName();
10. Podemos pegar o Thread atual atrav�s do m�todo Thread.currentThread();
11. p q metodo n seja executado por 2 Threads ao mesmo tempo, utilizar um bloco synchronized	
11.1 - synchronized - opera��o at�mica - Cuja execu��o n�o pode ser interrompida na metade -
	feita atrav�s de mutex, que nada mais � do que a chave do objeto. 
11.2 - sincronize o acesso ao codigo do banheiro - synchronized(this) { <-> chave (lock)
11.3 - A chave (lock) que a thread obt�m pode ser chamada tamb�m de lock impl�cito ou 
	lock intr�nseco. Tamb�m existem formas de usar uma chave explicitamente (programaticamente) 
	atrav�s de uma classe chamada ReentrantLock, onde voc� tem 
	m�todos para obter a chave (tryLock) e para devolver a chave (unlock).	
12 - Certamente h� algumas diferen�as que podemos examinar. Uma das principais � a 
	possibilidade de se criar um lock com timeout usando uma sobrecarga do m�todo tryLock.
	boolean locked = lock.tryLock(5, TimeUnit.SECONDS); //5s
	Com esse m�todo esperamos at� cinco segundos e receberemos true caso o lock for obtido. 
	Caso contr�rio, receberemos false. Como desvantagem, h� o fato de o programador ter a 
	responsabilidade de liberar o lock (unlock()).
13. O synchronized significa que bloqueamos o objeto para outros threads (as outras threads 
	ficam como blocked); 	
14. Multiplicador extends Thread { -> n devemos usar, mas pode extender Thread tb ao inves de implementar Runnable
	https://blog.caelum.com.br/como-nao-aprender-orientacao-a-objetos-heranca/ 
Capitulo4
15. public synchronized void adicionaElementos(String elemento) { � a mesma coisa q
	synchronized(this) qdo o corpo todo do metodo deve estar synchronized
16. ArrayList n eh thread safe (Thread safe significa que o c�digo funciona corretamente mesmo com v�rios threads 
	compartilhando o objeto - n tem synchronized no metodo add, get, por ex ), 
16.1. Vector e Collections.synchronizedList(new ArrayList<String>() s�o THREAD SAFE.
17. Para os mapas (Map) a antiga classe Hashtable e uma implementa��o mais recente e 
	perform�tica de mapas, a classe ConcurrentHashMap s�o THREAD SAFE.
18. Para Sets, usar Set conjunto = Collections.synchronizedSet(new HashSet()) s�o THREAD SAFE;
19. A implementa��o n�o thread-safe da fila � a LinkedList (sim, uma LinkedList � uma 
	lista e tamb�m � uma fila!). As vers�es thread-safe s�o as classes *BlockingQueue, por 
	exemplo ArrayBlockingQueue.
Capitulo5	
20. this.wait() devolve a chave do banheiro e fica esperando ser notificado p a thread sair do 
	estado de wait. A JVM nunca termina pois ainda h� threads esperando (status waiting).
	Se n sair desse estado waiting, por ex, temos um deadlock. � preciso acordar/notificar as 
	threads para continuar executar o programa.
	Quando chamamos wait() a thread devolve a chave e fica aguardando. No vocabul�rio do 
	mundo de threads, essa condi��o tamb�m � chamado de guarded block. Em outras palavras, 
	a thread fica bloqueada aguardando um sinal/notifica��o 
21. Voc� se lembra da ferramenta jconsole? Usamos-a para mostrar os threads da JVM. Com a JVM 
	ainda rodando, vamos subir o jconsole. O interessante � que podemos ver estado de cada thread. 
	Por exemplo, o thread Jo�o est� no estado WAITING
22. Status das threads:
	NEW Uma thread foi criada mas ainda n�o foi iniciada.
	RUNNABLE A thread est� rodando dentro da JVM.
	BLOCKED A thread foi bloqueada pois n�o conseguiu pegar a chave.
	WAITING A thread est� esperando this.notify ou notifyAll, pois foi chamado this.wait(). Vai
	executar na linha abaixo de 'this.wait()'.
	TIMED_WAITING A thread est� esperando pois foi chamado this.wait(milis) ou Thread.sleep(milis).
	TERMINATED A thread est� finalizada.
23. IllegalMonitorStateException!
	Analisando esses dados, podemos ver que recebemos uma exce��o porque chamamos o 
	m�todo notifyAll(). Qual � o problema? Pensando na vida real, faz sentido algu�m 
	limpando o banheiro quando ele est� ocupado? Claro que n�o! E no mundo Java isso 
	n�o � diferente... S� podemos limpar o banheiro e notificar os outros threads quando 
	estamos com a chave em m�os. Ou seja, s� podemos chamar notifyAll() dentro de um 
	bloco sincronizado.
	A documenta��o da exce��o diz: Thrown to indicate that a thread has attempted to 
	wait on an object's monitor or to notify other threads waiting on an object's monitor 
	without owning the specified monitor (Lan�ado para indicar que uma thread tentou 
	esperar no monitor de um objeto ou notificar outros threads em espera no monitor 
	de um objeto sem possuir o monitor especificado).*
	A palavra monitor podemos traduzir como chave do objeto.
24. Como fazer com que uma thread A espere a execu��o da thread B?
	R.: Coloque um wait() na Thread A e um notify() na Thread B.
25. � preciso "acordar" ou "notificar as threads" que est�o no estado waiting. Essa 
	notifica��o faremos atrav�s de uma nova thread, no caso da limpeza do banheiro.
26. Voc� pode definir que threads dependem de outros. Em outras palavras, h� threads 
	que s� deveriam rodar enquanto outras existem. Queremos que a m�quina virtual 
	automaticamente termine a thread de limpeza quando n�o existir mais nenhum convidado. 
	Esse tipo de thread se chama Daemon - limpeza.setDaemon(true)
27. Threads daemon s�o como prestadores de servi�os para outras threads. Elas s�o usadas 
	para dar apoio �s tarefas e s� s�o necess�rias rodar quando as threads "normais" ainda 
	est�o sendo executadas. Uma thread daemon n�o impede a JVM de terminar desde que n�o 
	existem mais threads principais em execu��o. Um exemplo de uma thread daemon � o 
	coletor de lixo da JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
	Para definir uma thread como daemon basta usar o m�todo setDaemon(boolean) antes de 
	inicializar.
	existem daemon threads para executar tarefas ou servi�os secund�rios.
	daemons ser�o automaticamente desligados quando todas as outras threads terminarem
28. Ser� que n�o faz sentido dar prefer�ncia � limpeza quando tem muitos convidados, sabendo 
	que cada convidado quer um banheiro limpo?
	R.: limpeza.setPriority(10) - (valor de 1 (Thread.MIN_PRIORITY) a 10 (Thread.MAX_PRIORITY))
	significa que se n�o usamos nenhuma prioridade expl�cita, a thread vai assumir o valor 5 
	para a prioridade
Capitulo6
29. Temos que ter cuidado para n�o mandar um thread esperar quando n�o h� necessidade
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
