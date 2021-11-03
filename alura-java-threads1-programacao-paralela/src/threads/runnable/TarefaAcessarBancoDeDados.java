package threads.runnable;

import threads.domain.GerenciadorDeTransacao;
import threads.domain.PoolDeConexao;

public class TarefaAcessarBancoDeDados implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessarBancoDeDados(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		synchronized (pool) {
			System.out.println("peguei a chave do pool");
			pool.getConnection();
			
			synchronized (tx) {
				System.out.println("comecando a gerenciar a tx");
				tx.begin();
			}
		}
		

	}

}
