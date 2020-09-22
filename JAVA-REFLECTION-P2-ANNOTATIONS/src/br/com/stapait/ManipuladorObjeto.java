package br.com.stapait;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> params) {
		/*
		 * passos:
		 * 1-pegar todos os metodos da classe
		 * 2filtrar todos os metodos de modo que
		 * 2.1-tenham o mesmo nome informado pelo usuario
		 * 2.2-tenham a mesma quantidade de parametros passados na URL
		 * 2.3-e q cada um dos parametros tenham os mesmos nomes e tipos 
		 * iguais aos passados na URL
		 * 3-lançar uma RuntimeException caso nenhum 
		 * metodo seja encontrado 
		 */
		//passo1
		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());
		//passo2
		Method metodoSelecionado = metodos.filter(metodo -> 
			//passo2.1
			metodo.getName().equals(nomeMetodo) 
			//passo 2.2
			&& metodo.getParameterCount() == params.values().size()
			//passo 2.3
			&& Stream.of(metodo.getParameters())
			//garantir q nome de parametro esteja nos passos pelo usuario
				.allMatch(param -> {
					System.out.println(param.getName());
					return params.keySet().contains(param.getName())
			//garantir q o tipo passado seja o mesmo do metodo
				&& params.get(param.getName()).getClass().equals(param.getType()); 		
				}
				)			
			)
			.findFirst()
			//passo 3
			.orElseThrow(() -> new RuntimeException("Metodo n encontrado"));
		
		/*
		 * try { Method metodo; metodo =
		 * instancia.getClass().getDeclaredMethod(nomeMetodo);
		 */
			return new ManipuladorMetodo(instancia, metodoSelecionado, params);
		/*
		 * } catch (NoSuchMethodException | SecurityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); throw new
		 * RuntimeException(e); }
		 */
	}

}
