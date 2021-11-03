package threads.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import threads.runnable.TarefaAdicionarElementoArrayList;

public class PrincipalArrayList {
	public static void main(String[] args) throws InterruptedException {
		//nao eh synchronized 
		List<String> list = new ArrayList<>();
		//Vector eh synchronized
		List<String> list2 = new Vector<>();
		
		//eh synchronized
		List<String> lista = Collections.synchronizedList(list);
		
		for(int i = 0; i< 10;i++) {
			Thread thread = new Thread(new TarefaAdicionarElementoArrayList(lista, i));
			thread.start();						
		}
		
		Thread.sleep(2000);
		
		for (int i =0; i< lista.size(); i++) {
			
			System.out.println(i + " - " +lista.get(i));
			
		}
	}
	

}
