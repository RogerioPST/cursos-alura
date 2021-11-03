package br.com.alura.servidor;

/*
 * a motivação dessa classe: tem dois clientes conectados e ambos enviam o cmd 'fim' e podem acontecer
 * coisas inesperadas e essa classe tenta simular/simula exatamente esse problema q pode ocorrer.
 * 
 * No caso, o problema q ocorre eh q dessa forma q está o codigo, cada thread tem a sua memoria,
 * tem o seu cache c os valores de 'estaRodando' e n atualizam uma unica variavel 'estaRodando'
 * de uma memoria principal.
 * 
 * Se eu quiser dizer p o Java usar essa memoria unica p variavel 'estaRodando', eu uso volatile
 */

/*
 * será que existe uma solução sem volatile usando synchronized?
Usando a classe criada nesse projeto: 'ServidorDeTesteAlternativaDoUsoDeVolatile' e 'TarefaPararServidor'!!
Existe sim (mas mais complexo). A ideia é que não acessamos a variável estaRodando diretamente 
pela thread, e sim sempre acessamos através de um método sincronizado!
 */
public class ServidorDeTeste {

    private boolean estaRodando = false;
    // p fazer o projeto funcionar , descomentar a linha abaixo ou usar AtomicBoolean 
    //p entender melhor, -> olhar classe ServidorDeTesteComAtomicBoolean
    //private volatile boolean estaRodando = false;

    public static void main(String[] args) throws InterruptedException {
        ServidorDeTeste servidor = new ServidorDeTeste();
        servidor.rodar();
        servidor.alterandoAtributo();
    }

    private void rodar() {
        new Thread(new Runnable() {

            public void run() {
                System.out.println("Servidor começando, estaRodando = " + estaRodando );

                while(!estaRodando) {}

                System.out.println("Servidor rodando, estaRodando = " + estaRodando );

                while(estaRodando) {}

                System.out.println("Servidor terminando, estaRodando = " + estaRodando );
            }
        }).start();
    }

    private void alterandoAtributo() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = true");
        estaRodando = true;

        Thread.sleep(5000);
        System.out.println("Main alterando estaRodando = false");
        estaRodando = false;        
    }
}