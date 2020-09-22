package br.com.stapait;

import java.lang.reflect.Field;
import java.util.Collection;

public class ConversorXML {

	public String converte(Object objeto) {
		try {
			Class<?> classeObjeto = objeto.getClass();
			StringBuilder xmlBuilder = new StringBuilder();

			if (objeto instanceof Collection) {
				Collection<?> colecao = (Collection<?>) objeto;
				xmlBuilder.append("<lista>");
				for (Object o : colecao) {
					String xml = converte(o);
					xmlBuilder.append(xml);
				}

				xmlBuilder.append("</lista>");
			} else {
				String nomeClasse = classeObjeto.getName();
				xmlBuilder.append("<" + nomeClasse + ">");

				for (Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					String nome = atributo.getName();
					Object valor = atributo.get(objeto);

					xmlBuilder.append("<" + nome + ">");
					xmlBuilder.append(valor);
					xmlBuilder.append("</" + nome + ">");
				}

				xmlBuilder.append("</" + nomeClasse + ">");
			}
			return xmlBuilder.toString();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("erro na geração do xml");
		}
		
	}

}
