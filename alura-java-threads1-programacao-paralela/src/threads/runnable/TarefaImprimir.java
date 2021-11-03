package threads.runnable;

import threads.domain.Lista;

public class TarefaImprimir implements Runnable {

	private Lista lista;
	
	

	public TarefaImprimir(Lista lista) {
		this.lista = lista;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		// lista eh o mutex, eh a chave
		synchronized (lista) {

			if (!lista.estaCheia()) {

				try {
					System.out.println("indo esperar. aguardando notificacao de quando a lista estiver cheia");
					lista.wait();
					//qdo é chamado lista.notify, será retomada a exec do codigo a partir daqui desse comentario,
					//, ou seja, a prox linha posterior ao lista.wait.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			for (int i = 0; i < lista.tamanho(); i++) {

				System.out.println(i + " - " + lista.pegaElemento(i));
			}

		}

	}

}
