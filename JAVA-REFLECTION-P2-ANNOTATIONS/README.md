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
<li><b>Annotation</b> - são os metadados da linguagem Java! Ou seja, informações que são adicionadas no código de modo que são usadas para algum propósito por baixo dos panos: seja ele omitir warnings, indicar que determinado método será removido ou até mesmo permitir que determinadas informações sejam recuperadas em tempo de execução. ou ainda, Metadados são informações relativas a dados. Anotações nada mais são que metadados na linguagem Java que servem para acrescentar informação útil em relação a alguma parte do código. Sendo todas as anotações existentes filhas da interface java.lang.annotation.Annotation e, portanto, todas as anotações padrões do Java são originárias do pacote java.lang.annotation e n podem implementar explicitamente uma interface ou estender uma classe.. novo tipo no Java definido pela palavra chave @interface e que nesse tipo façamos algumas configurações indicando como essa anotação irá funcionar! É aí que surgem as meta anotações, ou anotações que são usadas para acrescentar informações à anotações que estão sendo criadas. E é nessas meta anotações que iremos focar aqui!

https://docs.oracle.com/javase/tutorial/java/annotations/index.html

No Java, existem 5 meta anotações, são elas:

<ul>
<li>@Retention - espcifica como a anotação deve ser considerada pelo Java. Seus possíveis valores são:

RetentionPolicy.SOURCE – A anotação é considerada apenas a nível de código fonte e ignorada pelo compilador.
RetentionPolicy.CLASS (valor padrão) – A anotação é levada em consideração pelo compilador em tempo de compilação, mas ignorada pela JVM em tempo de execução.
RetentionPolicy.RUNTIME – A anotação será considerada pela JVM em tempo de execução.</li>
<li>@Documented - Indica se a anotação deve ser documentada no Javadoc juntos aos elementos onde for utilizada.
</li>
<li>@Inherited - indica que a anotação será herdada por classes filhas quando usada numa super classe. Essa herança não ocorre por padrão! Além disso, anotações "herdáveis" só podem ser aplicadas em classes.

```
@NomeTagXml("meuProduto")
public class SuperProduto {}

public class Produto extends SuperProduto {
    private String nome;
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD } )
public @interface NomeTagXml {	
	public String value(); //por convenção, toda anotação q tem um unico atributo.	

}

//no metodo main,  para obter uma referência à anotação.
classeProduto.getAnnotation(NomeTagXml.class)
```
</li>
<li>@Repeatable - introduzida a partir do Java 8, indica que a anotação pode ser aplicada mais de uma vez no mesmo lugar.
</li>
<li>@Target - Indica onde a anotação pode ser utilizada no código Java. Seus possíveis valores são:

ElementType.ANNOTATION_TYPE - pode ser aplicada numa anotação.
ElementType.CONSTRUCTOR - pode ser aplicada num construtor.
ElementType.FIELD - pode ser aplicada num atributo.
ElementType.LOCAL_VARIABLE - pode ser aplicada numa variável local.
ElementType.METHOD - pode ser aplicado num método.
ElementType.PACKAGE - pode ser aplicada numa declaração de pacote.
ElementType.PARAMETER - pode ser aplicada num parâmetro de método.
ElementType.TYPE - pode ser aplicada na declaração de uma classe, interface (incluindo anotações) ou enum.

```
//informo q quero usar a annotation na declaração de classe e atributo //{} - array, nesse caso.w
@Target({ElementType.TYPE, ElementType.FIELD } )
```
</li>
</ul>
</li>


<li><b>Injeção de dependência</b> (ou DI, abreviatura em inglês), é o termo utilizado para indicar que objetos necessários para o funcionamento de determinada classe serão providos a ela. Dessa forma, a classe em questão não tem a obrigação de criar nenhuma objeto que ela dependa.

</li>

<li><b>Inversão de controle</b> (ou IoC, abreviatura em inglês), é o termo utilizado para indicar a remoção de criação de objetos de lugares onde não estamos interessados em criá-los, apenas em utilizá-los. A criação de tais objetos é delegada para um lugar normalmente chamado de container IoC.
</li>
</ol>
