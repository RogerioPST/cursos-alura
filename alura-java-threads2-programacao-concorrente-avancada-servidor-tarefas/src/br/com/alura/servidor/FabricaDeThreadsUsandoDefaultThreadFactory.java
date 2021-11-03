package br.com.alura.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreadsUsandoDefaultThreadFactory implements ThreadFactory {	

	    private ThreadFactory defaultFactory;

	    public FabricaDeThreadsUsandoDefaultThreadFactory(ThreadFactory defaultFactory) {
	        this.defaultFactory = defaultFactory;
	        
	    }

	    @Override
	    public Thread newThread(Runnable tarefa) {

	        //criando uma thread usando a fabrica padr�o
	        Thread thread = defaultFactory.newThread(tarefa); 

	        //personalizando a thread
	        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
	        return thread;
	    }
	}
