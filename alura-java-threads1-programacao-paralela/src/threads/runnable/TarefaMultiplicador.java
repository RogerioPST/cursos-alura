package threads.runnable;

import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * A classe Thread na verdade já implementa a interface Runnable. Ou seja, a thread também é um Runnable!
Sabendo disso, você pode criar uma subclasse dela e sobrescrever o método run. Assim, você não precisaria da classe separada para implementar a tarefa a executar.

Apesar de ser um código mais simples, você está usando ou abusando a herança. Essa forma de herança também é chamada de "herança por preguiça".

Repare que, ao estender a classe Thread, herdamos um monte de métodos mas usamos apenas o run. Além disso, não estamos querendo aproveitar o polimorfismo que a herança traz.

Hoje em dia, apesar de ser funcional, essa forma de criar uma thread é considerada um mau exemplo de herança.

Prefira sempre implementar o Runnable a herdar de Thread. Separando as responsabilidades de ser uma thread da definição da tarefa, seguimos as boas práticas do mundo OO. Neste treinamento sempre usaremos uma tarefa (Runnable) separada da classe Thread, nunca usaremos herança.

No blog da Caelum, o Paulo Silveira fala um pouco mais sobre esse assunto e dá outros exemplos de mau uso da herança:

Como não aprender orientação a objetos - Herança
 */
public class TarefaMultiplicador extends Thread {
	
	private JTextField primeiro;
	private JTextField segundo;
	private JLabel resultado;
	
	public TarefaMultiplicador(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long valor1 = Long.parseLong(primeiro.getText());
		long valor2 = Long.parseLong(segundo.getText());
		BigInteger calculo = new BigInteger("0");
		
		for (int i = 0; i < valor1; i++) {
			for (int j = 0; j < valor2; j++) {
				calculo = calculo.add(new BigInteger("1"));
			}
		}

		resultado.setText(calculo.toString());
	}

}
