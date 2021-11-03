package threads.domain;

public class Lista {

	private String[] elementos = new String[1000];
	private int indice = 0;

	//qdo o synchronized vale p o metodo inteiro, pode ser usado assim ou synchronized(this) 
	//envolvendo todo corpo do metodo
	public synchronized void adicionaElementos(String elemento) {

		this.elementos[indice] = elemento;
		this.indice++;
		
		try {
			//Thread.sleep(5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.indice== this.elementos.length) {
			System.out.println("lista ta cheia, notificando! TarefaImprimir, vc ja pode imprimir");
		//como soh tem uma thread q est� com wait, soh precisa de notify, no caso a thread de imprimir a lista
		//q est� c wait, pois soh deve imprimir se estiver c a lista cheia, no caso	.
			this.notify();
		}
	}

	public int tamanho() {
		return this.elementos.length;
	}

	public String pegaElemento(int posicao) {
		return this.elementos[posicao];
	}

	public boolean estaCheia() {
		// TODO Auto-generated method stub
		return this.indice == this.tamanho();
	}
}
