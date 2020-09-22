package br.com.stapait;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {

	private Object instancia;
	private Method metodo;
	private Map<String, Object> params;
	
	/*
	 * Desse modo, precisamos responder duas perguntas! Onde esse m�todo deve ser implementado e que tipo de par�metro ele ir� receber.

A primeira quest�o � bem simples de responder! Basta observar que o m�todo getMetodo() retorna um objeto do tipo ManipuladorMetodo. Sendo assim, se queremos que tal objeto tenha um m�todo chamado comTratamentoDeExcecao(), � justamente na classe ManipuladorMetodo que precisamos implement�-lo!
Agora, a segunda quest�o para ser respondida precisa de uma perspic�cia maior quanto �s interfaces funcionais que foram introduzidas no JDK 8! Perceba que a nossa express�o lambda recebe dois par�metros de tipos distintos! O m�todo que est� sendo invocado, portanto um objeto da classe Method e a exce��o que dever� ser tratada que nesse caso � um objeto do tipo InvocationTargetException. Por fim, essa express�o lambda deve ser capaz de dar um retorno de um terceiro tipo, o qual, nesse caso, representamos com o lan�amento de uma nova exce��o do tipo RuntimeException.

De posse dessas caracter�sticas, precisamos apenas saber se h� uma interface funcional que as atenda! � a� que entra a perspic�cia! O aluno que estiver mais por dentro desse assunto, saber� que, para a nossa alegria, h� uma interface que atende todas as nossas necessidades! E ela se chama BiFunction<T, U, R> (link do Javadoc)! Essa interface funcional possui um m�todo apply(T t, U u) que recebe dois objetos de tipos gen�ricos T e U e retorna um terceiro tipo R. Em outras palavras, � esse m�todo que deve ser invocado para que nossa express�o lambda funcione!
	 */
	private BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao;

	public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
		this.instancia = instancia;
		this.metodo = metodo;
		this.params = params;
	}
	
  public ManipuladorMetodo comTratamentoDeExcecao(BiFunction<Method, InvocationTargetException, Object> tratamentoExcecao) {
        this.tratamentoExcecao = tratamentoExcecao;
        return this;
    }
	
	public Object invoca() {
		List<Object> parametros = new ArrayList<>();
		Stream.of(metodo.getParameters())
			.forEach(p -> parametros.add(params.get(p.getName())));
		try {						
			return metodo.invoke(instancia, parametros.toArray());
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch( InvocationTargetException e) {
			
			 // tratamento especial e customizado da exce��o.
            if (tratamentoExcecao != null) {
                return tratamentoExcecao.apply(metodo, e);
            }
            
			e.printStackTrace();
			throw new RuntimeException("exception original: ", e.getTargetException());
		}
	}

}
