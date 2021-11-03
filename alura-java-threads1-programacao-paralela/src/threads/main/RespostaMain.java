package threads.main;

/*
 * Quando a tarefa é pequena, podemos usar um atalho da linguagem java. Não é preciso criar uma classe separada 
 * que representa a tarefa. Tudo isso pode ser feito dentro da classe RespostaMain usando uma classe anônima
 * Devemos ter cuidado com as classes anônimas pois podem dificultar a leitura.

Além disso, como o compilador realmente gera uma classe, essa classe, de fato, existe quando é executada. 
Isso pode ser mais difícil de se entender quando recebemos uma exceção ou depuramos o código pois a classe não 
está presente no nosso código fonte.
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
