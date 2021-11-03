package threads.runnable;

import threads.domain.GerenciadorDeTransacao;
import threads.domain.PoolDeConexao;

public class TarefaAcessarProcedureDoBancoDeDados implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessarProcedureDoBancoDeDados(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		synchronized (pool) {
			System.out.println(" comecando a tx");
			pool.getConnection();
			
			synchronized (tx) {
				System.out.println("peguei a conexao");
				tx.begin();
			}
		}

	}

}
