package br.com.alura.servidor;

/*
 * ser� que existe uma solu��o sem volatile usando synchronized?

Existe sim (mas mais complexo). A ideia � que n�o acessamos a vari�vel estaRodando diretamente 
pela thread, e sim sempre acessamos atrav�s de um m�todo sincronizado!
 */
public class ServidorDeTesteAlternativaDoUsoDeVolatile {
	

	    private boolean estaRodando = false;

	    public static void main(String[] args) throws InterruptedException {
	    	ServidorDeTesteAlternativaDoUsoDeVolatile servidor = new ServidorDeTesteAlternativaDoUsoDeVolatile();
	        servidor.rodar();
	        servidor.alterandoAtributo();
	    }

	    private void rodar() {
	            Thread thread = new Thread(new TarefaPararServidor(this));
	            thread.start();
	    }
	    /*
	    3 novos m�todos,  todos sincronizados para encapsular o acesso ao atributos
	    */
	    public synchronized boolean estaRodando() {
	        return this.estaRodando;
	    }

	    public synchronized void parar() {
	        this.estaRodando = false;
	    }

	    public synchronized void ligar() {
	        this.estaRodando = true;
	    }

	    private void alterandoAtributo() throws InterruptedException {
	        Thread.sleep(1000);
	        System.out.println("Main alterando estaRodando=true");
	        this.ligar();;

	        Thread.sleep(5000);
	        System.out.println("Main alterando estaRodando=false");
	        this.parar();;        
	    }
	}
