package br.com.alura.servidor;

/*
 * ser� que existe uma solu��o sem volatile usando synchronized?

Existe sim (mas mais complexo). A ideia � que n�o acessamos a vari�vel estaRodando diretamente 
pela thread, e sim sempre acessamos atrav�s de um m�todo sincronizado!
 */
public class TarefaPararServidor implements Runnable {

    private ServidorDeTesteAlternativaDoUsoDeVolatile servidor;

    //recebendo o servidor como parametro
    public TarefaPararServidor(ServidorDeTesteAlternativaDoUsoDeVolatile servidor) {
        this.servidor = servidor;
    }

    public void run() {
        //chamando o m�todo estaRodando()
        System.out.println("Servidor comecando, estaRodando=" + this.servidor.estaRodando());
        while (!this.servidor.estaRodando()) {
        }

        System.out.println("Servidor rodando, estaRodando=" + this.servidor.estaRodando());

        while (this.servidor.estaRodando()) {
        }

        System.out.println("Servidor terminando, estaRodando=" + this.servidor.estaRodando());
    }
}