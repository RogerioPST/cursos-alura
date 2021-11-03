package threads.main;

import threads.domain.GerenciadorDeTransacao;
import threads.domain.PoolDeConexao;
import threads.runnable.TarefaAcessarBancoDeDados;
import threads.runnable.TarefaAcessarProcedureDoBancoDeDados;

public class PrincipalBancoDeDados {

    public static void main(String[] args) {

        GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
        PoolDeConexao pool = new PoolDeConexao();
        
        new Thread(new TarefaAcessarBancoDeDados(pool, tx)).start();
        
        
        new Thread(new TarefaAcessarProcedureDoBancoDeDados(pool, tx)).start();

    }
}
