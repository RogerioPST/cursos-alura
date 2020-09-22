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
	 * Desse modo, precisamos responder duas perguntas! Onde esse método deve ser implementado e que tipo de parâmetro ele irá receber.

A primeira questão é bem simples de responder! Basta observar que o método getMetodo() retorna um objeto do tipo ManipuladorMetodo. Sendo assim, se queremos que tal objeto tenha um método chamado comTratamentoDeExcecao(), é justamente na classe ManipuladorMetodo que precisamos implementá-lo!
Agora, a segunda questão para ser respondida precisa de uma perspicácia maior quanto às interfaces funcionais que foram introduzidas no JDK 8! Perceba que a nossa expressão lambda recebe dois parâmetros de tipos distintos! O método que está sendo invocado, portanto um objeto da classe Method e a exceção que deverá ser tratada que nesse caso é um objeto do tipo InvocationTargetException. Por fim, essa expressão lambda deve ser capaz de dar um retorno de um terceiro tipo, o qual, nesse caso, representamos com o lançamento de uma nova exceção do tipo RuntimeException.

De posse dessas características, precisamos apenas saber se há uma interface funcional que as atenda! É aí que entra a perspicácia! O aluno que estiver mais por dentro desse assunto, saberá que, para a nossa alegria, há uma interface que atende todas as nossas necessidades! E ela se chama BiFunction<T, U, R> (link do Javadoc)! Essa interface funcional possui um método apply(T t, U u) que recebe dois objetos de tipos genéricos T e U e retorna um terceiro tipo R. Em outras palavras, é esse método que deve ser invocado para que nossa expressão lambda funcione!
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
			
			 // tratamento especial e customizado da exceção.
            if (tratamentoExcecao != null) {
                return tratamentoExcecao.apply(metodo, e);
            }
            
			e.printStackTrace();
			throw new RuntimeException("exception original: ", e.getTargetException());
		}
	}

}
