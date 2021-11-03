package threads.runnable;

import threads.domain.Banheiro;

public class TarefaLimpeza implements Runnable {
	
	private Banheiro banheiro;
	
	public TarefaLimpeza(Banheiro banheiro){
		this.banheiro = banheiro;
		
		
	}
	

	@Override
	public void run() {
		while(true) {
			
			banheiro.limpa();
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		

	}

}