<ol>
<li>Classe Class, com ela, podemos:
<ul>
<li>listar seus metodos (privados ou não):
<ul>
<li>getMethods() - retorna os métodos públicos da classe, superClasse q extenda e da interface que ela implemente
</li>
<li>getMethod(String nome, Class arg) - retorna o método público da classe, superClasse q extenda e da interface que ela implemente; com o nome e tipo de argumento passado.
</li>
<li>getDeclaredMethods() - retorna todos os métodos apenas da classe em questão, diferente do getMethods()
</li>
<li>getDeclaredMethod(String nome, Class arg) - retorna o método privado da apenas da classe em questão com o nome e tipo de argumento.
</li>

```
//caso o metodo seja privado, usar setAccessible(true);
```
</ul>
</li>
<li>listar seus atributos (privados ou não)</li>
<li>listar construtores (privados ou não)
<ul>
<li>getConstructors() - retorna todos os construtores públicos
</li>
<li>getConstructor() - retorna o construtor público sem parametro</li>
<li>getConstructor(String.class) - retorna o construtor público com parametro de String </li>
<li>getDeclaredConstructor() - retorna o construtor privado/publico sem parametro

```
//caso o construtor seja privado, usar setAccessible(true);
```
</li>
<li>getDeclaredConstructor(String.class) - retorna o construtor privado com parametro de String</li>
<li>getDeclaredConstructors() - retorna todos os construtores (privados ou não)
</li>
</ul>

```
```
</li>
<li>criar objetos da classe em questão: <b>newInstance</b> depreciada a partir do java 9, pois exceções checked lançadas pelo construtor em questão são propagadas sem tratamento obrigatório e newInstance não nos avisa -> usar classe <b>Constructor</b> p isso:

```
Class<? extends Controle> subControleClasse2 = subControle.getClass();

Class<?> controleClasse3 = Class.forName("br.com.stapait.Controle");

Controle objetoControle = controleClasse1.newInstance();
```
</li>
</ul>
</li>
<li>Classe Constructor, com ela, podemos buscar:
<ul>
<li>tipos de exceções que podem ser lançadas</li>
<li>tipos de  parâmetros que recebe</li>
<li>quantidade de parâmetros que recebe</li>
<li>criar objetos da classe em questão: Possibilita ao desenvolvedor tratar possíveis exceções lançadas pelo construtor em questão através da checked exception InvocationTargetException, ao contrário do newInstance de Class.

```
Class<SubControle> subControleClasse1 = SubControle.class;

Class<?> subControleClasse2 = Class.forName("br.com.stapait.SubControle");
Class<?> controleClasse1 = Class.forName("br.com.stapait.Controle");

Constructor<SubControle> construtorPublicoSubControleSemParametro = subControleClasse1.getConstructor();
Constructor<SubControle> construtorPublicoSubControleComParametro = subControleClasse1.getConstructor(int.class);

Constructor<SubControle> construtorPrivadoSubControleComParametro = subControleClasse1.getDeclaredConstructor(String.class);


System.out.println(construtorPublicoSubControleSemParametro);
System.out.println(construtorPublicoSubControleComParametro);
System.out.println(construtorPrivadoSubControleComParametro);

SubControle subControle = construtorPublicoSubControleSemParametro.newInstance();
System.out.println(subControle instanceof SubControle);
construtorPrivadoSubControleComParametro.setAccessible(true);
SubControle subControlePrivado = construtorPrivadoSubControleComParametro.newInstance("oi");
System.out.println(subControle instanceof SubControle);
```
</li>
</ul>
</li>
<li>class <b>Method</b>. Com ela, podemos:
<ul>
<li>buscar os tipos de exceções que podem ser lançadas</li>
<li>buscar os tipos de parâmetros que recebe</li>
<li>buscar os tipos de retorno</li>
<li>invocar o método</li>
</ul>
</li>
<li>
Por padrão, para efetuar otimizações, o Java muda os nomes dos parâmetros para valores aleatórios como arg0 e arg1.
</li>
<li>
A partir do JDK 8, é possível através da API de Reflection obter os nomes originais dos parâmetros de métodos desde que façamos as configurações necessárias - a config necessária é: na opção de properties do projeto, em 'java compiler', habilitar p 'store information about method parameters (used for reflection)' .
</li>
<li>Como obter a exceção original lançada ao executar um método ou construtor.::
A API de Reflection irá lançar uma instância de InvocationTargetException que encapsulará a exceção original ocorrida dentro do método em questão. -  Inclusive, por não lançar essa exceção, o newInstance() da classe Class<T> acabou sendo descontinuado e marcado como deprecated a partir do JDK 9
</li> 
</ol>
