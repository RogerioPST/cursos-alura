package br.com.stapait;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ContainerIoC {
private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();
	
	public Object getInstancia(Class<?> tipoFonte) {	
		Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);
		if (tipoDestino != null) {
			return getInstancia(tipoDestino);
		}
	//filtrar os construtores q n tem nenhum parametro
	Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());
	Optional<Constructor<?>> construtorPadrao = construtores.filter(construtor -> construtor.getParameterCount() == 0)
		.findFirst();
	
	
		try {
			if (construtorPadrao.isPresent()) {
				Object instancia;
			instancia = construtorPadrao.get().newInstance();
			return instancia;
			} else {
				Constructor<?> construtor = tipoFonte.getDeclaredConstructors()[0];
				List<Object> params = new ArrayList<>();
				for (Parameter param : construtor.getParameters()){
					Class<?> tipoDoParametro = param.getType();
					params.add(getInstancia(tipoDoParametro));
				}
					
				return construtor.newInstance(params.toArray());
				
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		
	}

	public void registra(Class<?> tipoFonte, Class<?> tipoDestino) {
		// TODO Auto-generated method stub
		
	}
}
