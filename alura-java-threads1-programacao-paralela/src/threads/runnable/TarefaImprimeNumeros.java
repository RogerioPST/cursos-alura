package threads.runnable;

public class TarefaImprimeNumeros implements Runnable{

    @Override
    public void run() {
        for(int i=0;i<1000;i++) {
            Thread threadAtual = Thread.currentThread();
            
            try {
				threadAtual.sleep(1);
				if (threadAtual.isAlive()) {
					System.out.println("estou viva na thread num: " +threadAtual.getId() + " - imprimindo num: " + i);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            System.out.println("estou exec na thread num: " +threadAtual.getId() + " - imprimindo num: " + i);
        }
    }
}