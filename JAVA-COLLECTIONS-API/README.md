<ul>
<li>posso ordenar usando o metodo compareTo da interface Comparable:

```
public int compareTo(Aula outraAula) {	
	return this.titulo.compareTo(outraAula.titulo);
}
```

ou posso ordenar usando o metodo comparing:

```
Collections.sort(aulas, Comparator.comparing(Aula::getTempo));
//ou
aulas.sort(Comparator.comparing(Aula::getTempo));
```
</li>
<li> Encapsulando o acesso �s aulas: quando formos devolver referencia para objetos e queremos for�ar o uso do nosso m�todo adiciona, devemos devolver uma c�pia ou algo imut�vel, por ex, ao usar o m�todo unmodifiableList de Collectgions, o que vai fazer com que System.out.println(aulas == c1.getAulas()); devolva false agora:

```
public List<Aula> getAulas() {
	return Collections.unmodifiableList(aulas);
}
public Set<Aluno> getAlunos() {
	return Collections.unmodifiableSet(alunos);
}
```

Por conta do m�todo unmodifiableList, tamb�m n�o consigo ordenar as aulas apenas chamando o m�todo sort da classe Collections (Collections.sort()). Por isso, uso o construtor de ArrayList e passo nele essa cole��o de aulas imutaveis (passar a unmodifiable list no construtor de uma ArrayList tradicional):

```
List<Aula> aulasImutaveis = c.getAulas();
System.out.println(aulasImutaveis);
List<Aula> aulasMutaveis = new ArrayList<>(aulasImutaveis);
Collections.sort(aulasMutaveis);
System.out.println(aulasMutaveis);
Collections.sort(aulasMutaveis, Comparator.comparing(Aula::getTempo));
System.out.println(aulasMutaveis);
aulasMutaveis.sort(Comparator.comparing(Aula::getTempo));
System.out.println(aulasMutaveis);
```
</li>
<li>ArrayList: o get de ArrayList � muito r�pido, mas inserir, remover � muito lenta, pois precisa modificar a posi��o de todos os outros elementos afetados.

LinkedList: o get � muito lento, pois vai precisar percorrer toda lista, mas adicionar e remover elementos na cabe�a da lista � mais r�pido que ArrayList:

</li>
<li>Set X List:
- um conjunto n�o garante a ordem de inser��o. J� LinkedHashSet garante.
- metodos como contains e remove em Set s�o mto mais rapidos que em List.
- conjunto n aceita elemento repetido.
- seus av�s, pais s�o: Iterable -> Collection -> Set, List
- Quando estamos usando o m�todo .contains() de List ele utiliza apenas o .equals() para comparar dois objetos

</li>
<li>Collections.emptySet(): imagine que um curso foi cancelado e n�o teve matriculas. Faz sentido devolver Collections.emptySet();

```
Set<String> nomes = Collections.emptySet();
```
</li>
<li>Set<Aluno> alunosSincronizados = Collections.synchronizedSet(alunos): Uma das caracter�sticas mais interessantes de JVM � que ela sabe trabalhar em paralelo. Internamente isso � feito por meio de Threads que funcionam como pequenos processos dentro da JVM.

Nada impede que usemos um m�todo da classe Collections para transformar uma cole��o comum em uma cole��o para threads. � justamente isso que o m�todo faz, retorna um nova cole��o que pode ser compartilhada entre threads sem perigos.

```
Set<Aluno> alunosSincronizados = Collections.synchronizedSet(alunos);
```

</li>
<li>
quando uso HashSet, HashMap, Hash*, preciso implementar o metodo equals e hashCode:

```
@Override
public boolean equals(Object obj) {
	Aluno outro = (Aluno) obj;
	return this.nome.equals(outro.nome);
}
@Override
public int hashCode() {
	return nome.hashCode();
}
```
</li>
<li>programando defensivamente: interessante ainda seria se n�s nem tiv�ssemos que nos preocupar com Alunos de nome vazio, j� que isto n�o faz sentido:

```
if (nome == null) {
    throw new NullPointerException("Nome n�o pode ser nulo");
}
```
</li>
<li>Iterator - desde o Java 5: 
- como era poss�vel iterar em um conjunto (Set) se ele n�o possui acesso indexado como uma lista que possui o m�todo get? Percorr�amos uma lista atrav�s de um Iterator! 
- � um objeto que todas as cole��es nos d�o acesso e serve para iterar entre os objetos dentro da cole��o. A ordem na qual os elementos s�o devolvidos pelo Iterator depende da implementa��o da Collection utilizada. 
- Veja que podemos obter um Iterator de listas e conjuntos, porque todos implementam a interface Collection.

```
Set<Aluno> alunos = c.getAlunos();
Iterator<Aluno> iterador = alunos.iterator();
while (	iterador.hasNext()) {
	Aluno proximo = iterador.next();
	System.out.println("iterador: "+proximo);
}
```
</li>
<li>LinkedHashSet - O LinkedHashSet nos d� a performance de um HashSet mas com acesso previs�vel e ordenado.

```
Collection<Integer> numerosLinkedSet = new LinkedHashSet<Integer>();
```
</li>
<li>TreeSet: Para adicionarmos um objeto em um TreeSet ele precisa implementar a interface Comparable, se n receber� uma Exception (Exception in thread "main" java.lang.ClassCastException: br.com.stapait.Recibo cannot be cast to java.lang.Comparable). Mas o que fazer se estamos trabalhando com uma inst�ncia de uma classe que n�o temos acesso ou n�o podemos modificar para implementar Comparable? Nesse caso, o construtor do TreeSet recebe como par�metro um objeto que implementa Comparator. Dessa forma, o crit�rio de compara��o pode ser criado em separado da classe na qual opera.
-por ex, a cole��o deve guardar os alunos ordenados pelo n�mero de matr�cula
a cole��o n�o pode ter elementos repetidos 
->TreeSet ao inv�s de HashSet e LinkedHashSet - A implementa��o TreeSet j� ordena os seus elementos na hora da inser��o. Qual � o crit�rio da ordena��o depende e pode ser definido atrav�s de um Comparator.

```
Set<Recibo> recibos = new TreeSet<>(Comparator.comparing(Recibo::getOrigem));
recibos.add(r1);
//comparando pelo nome

public class OrdenaPorIdade implements Comparator<Funcionario> {
	@Override
	public int compare(Funcionario f1, Funcionario f2) {
		return f1.getNome().compareTo(f2.getNome());
	}
}
//comparando pela idade do Funcionario
public class OrdenaPorIdade implements Comparator<Funcionario>{
    @Override
    public int compare(Funcionario funcionario, Funcionario outroFuncionario) {
        return funcionario.getIdade() - outroFuncionario.getIdade();
    }
}
```
</li>
<li>Vector - Collection que � thread-safe e possui as mesmas caracter�sticas que um ArrayList, com a diferen�a de que o primeiro possui acesso sincronizado ou THREAD SAFETY e o segundo n�o.

</li>
<li>Map 
- Se uma chave for repetida, a antiga permanece, por�m, o valor � sobrescrito pelo novo valor contido na chave passada, sendo o antigo valor "esquecido" pelo Map.
- Ela funciona da seguinte maneira, mapeia valores para chaves, e atrav�s da chave conseguimos acessar o valor correspondente. Por isso ela n�o pode ser repetida, ao contr�rio do valor, que podem existir iguais.
- HashMap

```
private Map<Integer, Aluno> matriculaParaAluno = new HashMap<>();

public Aluno buscaMatriculado(int numero) {
	if (!matriculaParaAluno.containsKey(numero)) {
		throw new NoSuchElementException("matricula n�o encontrada");
	}
	return matriculaParaAluno.get(numero);		
}

```
</li>
<li>LinkedHashMap - garante essa ordem de inser��o - guarda a ordem do put - continua nos dando a performance de um HashMap, mas com acesso previs�vel e ordenado, seguindo a inser��o dos seus elementos.

```
Map<Integer, String> pessoas = new HashMap<>();

pessoas.put(21, "Leonardo Cordeiro");
pessoas.put(27, "Fabio Pimentel");
pessoas.put(19, "Silvio Mattos");
pessoas.put(23, "Romulo Henrique");


Set<Integer> keySet = pessoas.keySet();
for (Integer key : keySet) {
	System.out.println(pessoas.get(key));			
}

System.out.println("___");
Map<Integer, String> pessoasLinked = new LinkedHashMap<>();

pessoasLinked.put(21, "Leonardo Cordeiro");
pessoasLinked.put(27, "Fabio Pimentel");
pessoasLinked.put(19, "Silvio Mattos");
pessoasLinked.put(23, "Romulo Henrique");


Set<Integer> keySetLinked = pessoasLinked.keySet();
for (Integer key : keySetLinked) {
	System.out.println(pessoasLinked.get(key));			
}
```
</li>
<li>Iterando por um Map:

```
Map<String, Integer> nomesParaIdade = new HashMap<>();
nomesParaIdade.put("Paulo", 31);
nomesParaIdade.put("Adriano", 25);
nomesParaIdade.put("Alberto", 33);
nomesParaIdade.put("Guilherme", 26);

Set<String> chaves = nomesParaIdade.keySet();

Collection<Integer> valores = nomesParaIdade.values();

Set<Entry<String, Integer>> associacoes = nomesParaIdade.entrySet();

for (Entry<String, Integer> associacao : associacoes) {
	System.out.println(associacao.getKey() + " - " + associacao.getValue());	
}
```
</li>
</ul>