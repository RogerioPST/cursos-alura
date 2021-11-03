package threads.runnable;

import threads.domain.Banheiro;

public class TarefaNumero2 implements Runnable {
	
	private Banheiro banheiro;
	
	public TarefaNumero2(Banheiro banheiro){
		this.banheiro = banheiro;
		
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		banheiro.fazNumero2();
		banheiro.lavaMao();
		
		

	}

}
