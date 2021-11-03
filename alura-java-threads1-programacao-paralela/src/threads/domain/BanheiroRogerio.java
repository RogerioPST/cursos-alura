package threads.domain;

public class BanheiroRogerio extends Banheiro {
	
	
	
	private boolean torneiraAberta = false;

	public void lavaMao() {
		String nome = Thread.currentThread().getName();
		synchronized (this) {			
			System.out.println(nome + " abrindo a torneira");
			torneiraAberta = true;
		}
		
		if (torneiraAberta) {
			System.out.println(nome + " lavando a mao com a torneira aberta");
			System.out.println(nome + " fechando a torneira");
			torneiraAberta = false;
		}				
	}

}
