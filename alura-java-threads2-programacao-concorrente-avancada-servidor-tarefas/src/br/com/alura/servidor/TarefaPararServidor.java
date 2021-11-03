package br.com.alura.servidor;

/*
 * será que existe uma solução sem volatile usando synchronized?

Existe sim (mas mais complexo). A ideia é que não acessamos a variável estaRodando diretamente 
pela thread, e sim sempre acessamos através de um método sincronizado!
 */
public class TarefaPararServidor implements Runnable {

    private ServidorDeTesteAlternativaDoUsoDeVolatile servidor;

    //recebendo o servidor como parametro
    public TarefaPararServidor(ServidorDeTesteAlternativaDoUsoDeVolatile servidor) {
        this.servidor = servidor;
    }

    public void run() {
        //chamando o método estaRodando()
        System.out.println("Servidor comecando, estaRodando=" + this.servidor.estaRodando());
        while (!this.servidor.estaRodando()) {
        }

        System.out.println("Servidor rodando, estaRodando=" + this.servidor.estaRodando());

        while (this.servidor.estaRodando()) {
        }

        System.out.println("Servidor terminando, estaRodando=" + this.servidor.estaRodando());
    }
}