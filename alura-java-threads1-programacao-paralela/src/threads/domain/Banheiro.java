package threads.domain;

public class Banheiro {

	public boolean ehSujo = true;

	public void fazNumero1() {

		String nome = Thread.currentThread().getName();

		System.out.println(nome + " batendo na porta do banheiro");

		// quem possue a "chave" eh o this = vou pegar a chave do banheiro
		//somente uma pessoa/uma thread pode entrar no banheiro por vez
		synchronized (this) {

			System.out.println(nome + " entrando no banheiro");

			while (ehSujo) {
				esperaLaFora(nome);
			}
			System.out.println(nome + " fazendo coisa rapida");
			dormeUmPouco(5000);
			this.ehSujo = true;
			System.out.println(nome + " dando descarga");
			System.out.println(nome + " lavando a mao");
			System.out.println(nome + " saindo do banheiro");
		}

	}

	private void dormeUmPouco(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void limpa() {
		String nome = Thread.currentThread().getName();

		System.out.println(nome + " batendo na porta");

		synchronized (this) {
			System.out.println(nome + " entrando no banheiro");

			if (!ehSujo) {
				System.out.println(nome + ", nao estah sujo. vou sair!");
				return;

			}
			System.out.println(nome + " limpando o banheiro");

			this.ehSujo = false;

			dormeUmPouco(12000);

			//notifica todas as thread q elas podem sair do estado waiting
			this.notifyAll();

			System.out.println(nome + " saindo do banheiro");
		}
	}

	private void esperaLaFora(String nome) {
		System.out.println(nome + ", eca, banheiro ta sujo");
		try {
			// e agora eu quero devolver a chave para o banheiro
			// o convidado sai do banheiro e fica esperando ser notificado com 'notifyAll'/notify			
			this.wait();
			System.out.println(nome + ", ufa, acabou a minha espera");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void lavaMao() {
		
	}
	public void fazNumero2() {

		String nome = Thread.currentThread().getName();
		System.out.println(nome + " batendo na porta do banheiro");
		// quem possue a "chave" eh o this = vou pegar a chave do banheiro
				//somente uma pessoa/uma thread pode entrar no banheiro por vez
		
		synchronized (this) {

			System.out.println(nome + " entrando no banheiro");
			while (ehSujo) {
				esperaLaFora(nome);
			}
			System.out.println(nome + " fazendo coisa demorada");

			dormeUmPouco(10000);
			this.ehSujo = true;
			System.out.println(nome + " dando descarga");
			System.out.println(nome + " lavando a mao");
			System.out.println(nome + " saindo do banheiro");
		}
	}

}
