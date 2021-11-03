package threads.main;

import threads.runnable.TarefaImprimeString;

public class TesteImprimeString {
	
	public static void main(String[] args) {
		
		//
		Runnable tarefa = new TarefaImprimeString();
		
		Thread thread = new Thread(tarefa, "ThreadImprimeString");
		
		thread.start();
	}

}
