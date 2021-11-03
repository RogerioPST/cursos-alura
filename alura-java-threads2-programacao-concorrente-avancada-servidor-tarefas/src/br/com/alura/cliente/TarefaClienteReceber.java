package br.com.alura.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TarefaClienteReceber implements Runnable{

	private Socket socket;

	public TarefaClienteReceber(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		
		try {
			System.out.println("recebendo dados do servidor");
			Scanner respostaServidor = new Scanner(socket.getInputStream());		
			while(respostaServidor.hasNextLine()) {
				String linha = respostaServidor.nextLine();
				System.out.println("resposta do servidor: "+ linha);
				
			}
			
			respostaServidor.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
