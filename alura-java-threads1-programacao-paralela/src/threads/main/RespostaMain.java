package threads.main;

/*
 * Quando a tarefa � pequena, podemos usar um atalho da linguagem java. N�o � preciso criar uma classe separada 
 * que representa a tarefa. Tudo isso pode ser feito dentro da classe RespostaMain usando uma classe an�nima
 * Devemos ter cuidado com as classes an�nimas pois podem dificultar a leitura.

Al�m disso, como o compilador realmente gera uma classe, essa classe, de fato, existe quando � executada. 
Isso pode ser mais dif�cil de se entender quando recebemos uma exce��o ou depuramos o c�digo pois a classe n�o 
est� presente no nosso c�digo fonte.
 */
public class RespostaMain {
	public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("resposta");
            }
        }).start();
    }
}
