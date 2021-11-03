package threads.domain;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BanheiroReentrantLock extends Banheiro {
	private Lock lock = new ReentrantLock();
	boolean tryLock;

    public void fazNumero1() {
    	
        lock.lock();
        try {
			//tryLock = lock.tryLock(5, TimeUnit.SECONDS);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            System.out.println("reentrant - entrando no banheiro");
            System.out.println("fazendo coisa rapida");

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.ehSujo = true;
            System.out.println("dando descarga");
            System.out.println("lavando a mao");
            System.out.println("saindo do banheiro");
        lock.unlock();
    }

    public void fazNumero2() {

        lock.lock();
            System.out.println("reentrant - entrando no banheiro");
            System.out.println("fazendo coisa demorada");

            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.ehSujo = true;
            System.out.println("dando descarga");
            System.out.println("lavando a mao");
            System.out.println("saindo do banheiro");
        lock.unlock();
    }
}
