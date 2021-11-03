package threads.runnable;

import threads.domain.Lista;

public class TarefaAdicionarElemento implements Runnable {

	private Lista lista;
	private int numeroThread;

	public TarefaAdicionarElemento(Lista lista, int numeroThread) {
		this.lista = lista;
		// TODO Auto-generated constructor stub
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i< 100; i++) {
			lista.adicionaElementos("Thread " + numeroThread + " - " + i);
		}

	}

}
