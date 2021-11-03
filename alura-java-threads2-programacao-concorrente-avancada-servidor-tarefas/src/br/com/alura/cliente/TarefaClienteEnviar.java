package br.com.alura.cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TarefaClienteEnviar implements Runnable{

	private Socket socket;

	public TarefaClienteEnviar(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("pode enviar comandos");
			PrintStream saida;
			saida = new PrintStream(socket.getOutputStream());
			saida.println("comando1 inicial a ser enviado");
			
			// aguardando enter
			Scanner teclado = new Scanner(System.in);
			while (teclado.hasNextLine()) {
				String linha = teclado.nextLine();
				if(linha.trim().equals("")) {
					break;
				}
				saida.println(linha);
			}
			saida.close();
			teclado.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
