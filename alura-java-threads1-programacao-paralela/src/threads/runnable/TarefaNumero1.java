package threads.runnable;

import threads.domain.Banheiro;

public class TarefaNumero1 implements Runnable {
	
	private Banheiro banheiro;
	
	public TarefaNumero1(Banheiro banheiro){
		this.banheiro = banheiro;
		
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		banheiro.fazNumero1();
		banheiro.lavaMao();
		
		

	}

}
