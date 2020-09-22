package br.com.stapait;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Alurator {
	
	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}
	
	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao
		
		// /produto/lista
		Request request = new Request(url);
		String nomeControle  = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		
		Map<String, Object> params = request.getQueryParams();
		
		
		/* try { */
		/*
		 * Object instanciaControle = new Reflexao()
		 * .refletClasse(pacoteBase+nomeControle) .getContrutorPadrão() .invoca();
		 */
			
		Object retorno = new Reflexao().refletClasse(pacoteBase+nomeControle)
				.criaInstancia()							
				.getMetodo(nomeMetodo, params)
				.comTratamentoDeExcecao((metodo, e) -> {
                    System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
                    throw new RuntimeException("ERRO!");
                })
				.invoca();
				
			/*
			 * Class<?> classeControle = Class.forName(pacoteBase + nomeControle); Object
			 * instanciaControle = classeControle.getDeclaredConstructor().newInstance();
			 */
		/* System.out.println(instanciaControle); */
		System.out.println(retorno);
		retorno = new ConversorXML().converte(retorno);
		return retorno;
		/*
		 * } catch (ClassNotFoundException | InstantiationException
		 * |IllegalAccessException | IllegalArgumentException | NoSuchMethodException |
		 * SecurityException e) { e.printStackTrace(); throw new RuntimeException(e); }
		 * catch (InvocationTargetException e) { e.printStackTrace(); throw new
		 * RuntimeException("Erro no construtor! exception original: " ,
		 * e.getTargetException()); }
		 */
	}
}
