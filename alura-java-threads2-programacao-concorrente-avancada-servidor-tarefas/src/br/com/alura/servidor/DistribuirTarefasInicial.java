package br.com.alura.servidor;

import java.net.Socket;
import java.util.Scanner;

public class DistribuirTarefasInicial implements Runnable {

	private Socket socket;

	public DistribuirTarefasInicial(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		System.out.println("distribuindo tarefas para: "+ socket);
		try {
			Scanner entrada = new Scanner(socket.getInputStream());
			//simula um processamento demorado
			//Thread.sleep(20000);
			while(entrada.hasNextLine()) {
				System.out.println(entrada.nextLine());
			}
			
			entrada.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		System.out.println("fim do sleep - usado p teste apos ter a thread distribuirTarefas");
	}

}
