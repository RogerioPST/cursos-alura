package threads.main;

import threads.domain.Banheiro;
import threads.domain.BanheiroRogerio;
import threads.runnable.TarefaLimpeza;
import threads.runnable.TarefaNumero1;
import threads.runnable.TarefaNumero2;

public class PrincipalBanheiro {
	
	public static void main(String[] args) {
		
		Banheiro banheiro = new Banheiro();
		
		Thread convidado1 = new Thread(new TarefaNumero1(banheiro), "Joao");
		Thread convidado2 = new Thread(new TarefaNumero2(banheiro), "Pedro");
		Thread limpeza = new Thread(new TarefaLimpeza(banheiro), "Limpeza");
		//isso indica q vai rodar no background enquanto existirem outras threads rodando
		//a exemplo do garbage collector q roda enquanto existem objetos usados p limpar/coletar
		limpeza.setDaemon(true);
		limpeza.setPriority(Thread.MAX_PRIORITY);
//		Thread convidado3 = new Thread(new TarefaNumero1(banheiro), "Maria");
//		Thread convidado4 = new Thread(new TarefaNumero2(banheiro), "Ana");		
		
		convidado1.start();
		convidado2.start();
		limpeza.start();
//		convidado3.start();
//		convidado4.start();
		
	}

}
