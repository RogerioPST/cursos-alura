package br.com.stapait;

public class TesteInstanciaObjeto {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//ulr -> /controle/lista
		Class<Controle> controleClasse1 = Controle.class;
		Controle controle = new Controle();
		SubControle subControle = new SubControle();
		
		Class<? extends Controle> controleClasse2 = controle.getClass();
		Class<? extends Controle> subControleClasse2 = subControle.getClass();
		
		Class<?> controleClasse3 = Class.forName("br.com.stapait.Controle");
		
		Controle objetoControle = controleClasse1.newInstance();
		
		Object outroObjetoControle = controleClasse3.newInstance();
		
		System.out.println(objetoControle instanceof Controle);
		
		System.out.println(outroObjetoControle instanceof Controle);
		
		
	}
}
