package br.com.alura.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefasInicial {

	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("localhost", 12347);

		System.out.println("conexao estabelecida");		

		PrintStream saida = new PrintStream(socket.getOutputStream());
		saida.println("cmd enviado fora do while");
		
		Scanner teclado = new Scanner(System.in);
//E para travar o nosso cliente, vamos simular uma leitura do teclado:
		//teclado.nextLine();
		 while(teclado.hasNextLine()) {
			 String linha = teclado.nextLine();
			 
			 if (linha.trim().equals("")) {
				 break;
			 }
			 saida.println(linha);
		 }
//esse codigo abaixo só será executado se sair do bloco while acima
		 //e p isso, vamos ter q usar threads tb alem da main.
// no caso, segue um exemplo de thread com esqueleto de runnable/classe anonima, so faltando metodo run.
		//Thread threadAnonima = new Thread (new Runnable() {	});
		System.out.println("recebendo dados do servidor");
		 Scanner respostaServidor = new Scanner(socket.getInputStream());
		 while(respostaServidor.hasNextLine()) {
			 String linha = respostaServidor.nextLine();
			 
			 System.out.println(linha);
		 }
		respostaServidor.close();
		saida.close();
		teclado.close();
		System.out.println("fechando socket do cliente");
		socket.close();
	}

}
