package br.com.alura.cliente;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClienteTarefas {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12345);

		System.out.println("conexao estabelecida");		

		Runnable tarefaClienteEnviar = new TarefaClienteEnviar(socket);
		
		Thread threadEnviaComando = new Thread(tarefaClienteEnviar);

		Runnable tarefaClienteReceber = new TarefaClienteReceber(socket);
		
		Thread threadReceberComando = new Thread(tarefaClienteReceber);

		threadReceberComando.start();
		threadEnviaComando.start();

//Para resolver o erro de 'socket closed' ou outros efeitos colaterais, podemos indicar � thread main esperar
// a execu��o enquanto a thread de leitura est� rodando. Isso � feito atrav�s do
// m�todo join.
// esse codigo vai falar para a Thread Main soh continuar depois que a
//threaEnviaComando terminar :
//OBS: cuidado c Thread.sleep(10000) nesse caso, pois fechar� o socket silenciosamente e parar� de 
//enviar msg o servidor
		//threadEnviaComando.join();
		// ou threadReceberComando.join();
		threadReceberComando.join();
		
		System.out.println("fechando socket do cliente");

		// teclado.close();

		socket.close();
	}

}
