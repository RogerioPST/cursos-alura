package threads.runnable;

import java.math.BigInteger;

import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * A classe Thread na verdade j� implementa a interface Runnable. Ou seja, a thread tamb�m � um Runnable!
Sabendo disso, voc� pode criar uma subclasse dela e sobrescrever o m�todo run. Assim, voc� n�o precisaria da classe separada para implementar a tarefa a executar.

Apesar de ser um c�digo mais simples, voc� est� usando ou abusando a heran�a. Essa forma de heran�a tamb�m � chamada de "heran�a por pregui�a".

Repare que, ao estender a classe Thread, herdamos um monte de m�todos mas usamos apenas o run. Al�m disso, n�o estamos querendo aproveitar o polimorfismo que a heran�a traz.

Hoje em dia, apesar de ser funcional, essa forma de criar uma thread � considerada um mau exemplo de heran�a.

Prefira sempre implementar o Runnable a herdar de Thread. Separando as responsabilidades de ser uma thread da defini��o da tarefa, seguimos as boas pr�ticas do mundo OO. Neste treinamento sempre usaremos uma tarefa (Runnable) separada da classe Thread, nunca usaremos heran�a.

No blog da Caelum, o Paulo Silveira fala um pouco mais sobre esse assunto e d� outros exemplos de mau uso da heran�a:

Como n�o aprender orienta��o a objetos - Heran�a
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
