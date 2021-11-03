package threads.runnable;

import java.util.List;

public class TarefaAdicionarElementoArrayList implements Runnable {

	private List<String> lista;
	private int numeroThread;

	public TarefaAdicionarElementoArrayList(List<String> lista, int numeroThread) {
		this.lista = lista;
		// TODO Auto-generated constructor stub
		this.numeroThread = numeroThread;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i< 100; i++) {
			lista.add("Thread " + numeroThread + " - " + i);
		}

	}

}
