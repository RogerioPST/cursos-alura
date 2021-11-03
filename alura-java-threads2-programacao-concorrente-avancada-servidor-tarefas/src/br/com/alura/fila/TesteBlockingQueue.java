package br.com.alura.fila;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TesteBlockingQueue {
	public static void main(String[] args) throws InterruptedException {
		
		BlockingQueue<String> fila = new ArrayBlockingQueue<>(3);
		
		Queue<String> queue = new LinkedList<String>();
		queue.offer("c1");
		queue.offer("c2");
		queue.offer("c3");
		
		fila.put("c1");
		fila.put("c2");
		fila.put("c3");		
		//fila.put("c4");
		
		//devolve e retira o ultimo elem da fila
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		
		//o take bloqueia a thread até alguem disponibilizar um novo elemento
		System.out.println(fila.take());
		
		fila.offer("c3");
		//devolve o elemento, mas n retira da fila
		System.out.println(fila.peek());
		
		System.out.println(fila.size());
		System.out.println(queue.size());
		
	}

}
