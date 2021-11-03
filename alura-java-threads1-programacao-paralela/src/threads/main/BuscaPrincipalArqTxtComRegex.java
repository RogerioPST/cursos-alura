package threads.main;

import threads.runnable.TarefaBuscaTextual;

public class BuscaPrincipalArqTxtComRegex {
	public static void main(String[] args) {
		String nome = "da";
		
		//na classe Principal, busca por nome q começam c Dan ou Chad e tem 
		//(\\s - whitespace, um espaço ou tab) ou (\\w - word, um caractere ou número)
		nome = "(Dan|Chad)(\\s|\\w)*";
		
		Thread threadAssinaturas1 = new Thread(new TarefaBuscaTextual("assinaturas1.txt", nome));
		Thread threadAssinaturas2 = new Thread(new TarefaBuscaTextual("assinaturas2.txt", nome));
		Thread threadAssinaturas3 = new Thread(new TarefaBuscaTextual("autores.txt", nome));
		
		threadAssinaturas1.start();
		threadAssinaturas2.start();
		threadAssinaturas3.start();
		
		
		
	}

}
