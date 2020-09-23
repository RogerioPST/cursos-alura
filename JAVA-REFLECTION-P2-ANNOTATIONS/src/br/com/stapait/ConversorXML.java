package br.com.stapait;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.stapait.anotacao.NomeTagXml;

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
				NomeTagXml anotacaoClasse = classeObjeto.getDeclaredAnnotation(NomeTagXml.class);
				String nomeClasse = anotacaoClasse == null 
						? classeObjeto.getName()
						: anotacaoClasse.value();
				xmlBuilder.append("<" + nomeClasse + ">");

				for (Field atributo : classeObjeto.getDeclaredFields()) {
					atributo.setAccessible(true);
					NomeTagXml anotacaoAtributo = atributo.getDeclaredAnnotation(NomeTagXml.class);
					String nome = anotacaoAtributo == null 
							? atributo.getName()
							: anotacaoAtributo.value(); 
							
					Object valor = atributo.get(objeto);

					xmlBuilder.append("<" + anotacaoAtributo.value() + ">");
					xmlBuilder.append(valor);
					xmlBuilder.append("</" + anotacaoAtributo.value() + ">");
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
