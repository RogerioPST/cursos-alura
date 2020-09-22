<ol>
<li>
A partir do JDK 8, é possível através da API de Reflection obter os nomes originais dos parâmetros de métodos desde que façamos as configurações necessárias - a config necessária é: na opção de properties do projeto, em 'java compiler', habilitar p 'store information about method parameters (used for reflection)' .
</li>
<li>Classe Class, com ela, podemos:

<ul>
<li><b>listar seus atributos</b> (privados ou não):

<ul>
<li>getFields() - retorna os atributos públicos da classe ou possiveis superClasses q extenda
</li>
<li>getField(String nome, Class arg) - retorna o atributo público da classe ou possiveis superClasses q extenda; com o nome e tipo de argumento passado.
</li>
<li>getDeclaredFields() - retorna todos os atributos apenas da classe em questão, diferente do getFields()- p buscar valor doatributo privado, necessario usar setAccessible(true)
</li>
<li>getDeclaredField(String nome, Class arg) - retorna o atributo privado da apenas da classe em questão com o nome e tipo de argumento - p buscar valor do atributo privado, necessario usar setAccessible(true).
</li>
<ul>

```
Object produto = new Produto("Prod 1", 20, "marca 1");
Class<? extends Object> classe = produto.getClass();

for(Field atributo : classe.getDeclaredFields()) {
	atributo.setAccessible(true);
	System.out.println(atributo.getName() 
			+ ":" + atributo.get(produto));
}

System.out.println(classe.getField("id"));

for(Field atributo : classe.getFields()) {			
	System.out.println(atributo.getName() 
			+ ":" + atributo.get(produto));
}
```
</li>
</ul>

</li>
<li>Field/Atributo, com ele, podemos:
<ul>
<li>obter nome (atributo.getName()).</li>
<li>obter valor (atributo.get(produto)).</li>
<li>Atribuir valor a um atributo.</li>
<li>Obter o modificador de acesso do atributo.</li>
</ul>
</li>
<li>Annotation:
<ul>
<li>@Retention - informa qdo o Java precisa considerar a nossa annotation</li>
<li>@Target - informa onde vou usar a nossa annotation

```
//informo q quero usar a annotation na declaração de classe e atributo //{} - array, nesse caso.
@Target({ElementType.TYPE, ElementType.FIELD } )
```
</li>
</ul>
</li>
</ol>
