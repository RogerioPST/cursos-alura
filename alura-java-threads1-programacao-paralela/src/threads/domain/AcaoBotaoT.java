package threads.domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import threads.runnable.TarefaMultiplicacao;

public class AcaoBotaoT implements ActionListener {

	private JTextField primeiro;
	private JTextField segundo;
	private JLabel resultado;

	public AcaoBotaoT(JTextField primeiro, JTextField segundo, JLabel resultado) {
		this.primeiro = primeiro;
		this.segundo = segundo;
		this.resultado = resultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * calculo feito c pessima performance de proposito
		 ***********
		 * A ação está rodando na mesma Thread que a interface gráfica
		 ***********
		 * long valor1 = Long.parseLong(primeiro.getText());
			long valor2 = Long.parseLong(segundo.getText());
			BigInteger calculo = new BigInteger("0");
			
			for (int i = 0; i < valor1; i++) {
				for (int j = 0; j < valor2; j++) {
					calculo = calculo.add(new BigInteger("1"));
				}
			}
		 */
		//O primeiro passo é criar a tarefa que implementa a interface Runnable
		//a TarefaMultiplicacao precisa implementar a interface Runnable nesse caso.
		Runnable tarefa = new TarefaMultiplicacao(primeiro, segundo, resultado);
		Thread threadCalculo = new Thread(tarefa, "Thread Calculador");
		
		threadCalculo.start();

		
	}

}