package threads.runnable;

import java.util.concurrent.ConcurrentHashMap;

public class TarefaTeste implements Runnable {

	private ConcurrentHashMap<String, String> concurrentHashMap;

	public TarefaTeste(ConcurrentHashMap<String, String> concurrentHashMap) {
		this.concurrentHashMap = concurrentHashMap;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		
		synchronized (concurrentHashMap) {
			
		}
		

	}

}
