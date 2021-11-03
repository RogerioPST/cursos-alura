package threads.main;

import threads.runnable.TarefaImprimeNumeros;

public class PrincipalOrdemDeExecucaoTarefaImprimeNumeros {
	public static void main(String[] args) {
        new Thread(new TarefaImprimeNumeros ()).start();
        new Thread(new TarefaImprimeNumeros ()).start();
    }

}
