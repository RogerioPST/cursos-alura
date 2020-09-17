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
<li> Encapsulando o acesso às aulas: quando formos devolver referencia para objetos e queremos forçar o uso do nosso método adiciona, devemos devolver uma cópia ou algo imutável, por ex, ao usar o método unmodifiableList de Collectgions, o que vai fazer com que System.out.println(aulas == c1.getAulas()); devolva false agora:

```
public List<Aula> getAulas() {
	return Collections.unmodifiableList(aulas);
}
public Set<Aluno> getAlunos() {
	return Collections.unmodifiableSet(alunos);
}
```

Por conta do método unmodifiableList, também não consigo ordenar as aulas apenas chamando o método sort da classe Collections (Collections.sort()). Por isso, uso o construtor de ArrayList e passo nele essa coleção de aulas imutaveis (passar a unmodifiable list no construtor de uma ArrayList tradicional):

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
<li>ArrayList: o get de ArrayList é muito rápido, mas inserir, remover é muito lenta, pois precisa modificar a posição de todos os outros elementos afetados.

LinkedList: o get é muito lento, pois vai precisar percorrer toda lista, mas adicionar e remover elementos na cabeça da lista é mais rápido que ArrayList:

</li>
<li>Set X List:
- um conjunto não garante a ordem de inserção. Já LinkedHashSet garante.
- metodos como contains e remove em Set são mto mais rapidos que em List.
- conjunto n aceita elemento repetido.
- seus avós, pais são: Iterable -> Collection -> Set, List
- Quando estamos usando o método .contains() de List ele utiliza apenas o .equals() para comparar dois objetos

</li>
<li>Collections.emptySet(): imagine que um curso foi cancelado e não teve matriculas. Faz sentido devolver Collections.emptySet();

```
Set<String> nomes = Collections.emptySet();
```
</li>
<li>Set<Aluno> alunosSincronizados = Collections.synchronizedSet(alunos): Uma das características mais interessantes de JVM é que ela sabe trabalhar em paralelo. Internamente isso é feito por meio de Threads que funcionam como pequenos processos dentro da JVM.

Nada impede que usemos um método da classe Collections para transformar uma coleção comum em uma coleção para threads. É justamente isso que o método faz, retorna um nova coleção que pode ser compartilhada entre threads sem perigos.

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
<li>programando defensivamente: interessante ainda seria se nós nem tivéssemos que nos preocupar com Alunos de nome vazio, já que isto não faz sentido:

```
if (nome == null) {
    throw new NullPointerException("Nome não pode ser nulo");
}
```
</li>
<li>Iterator - desde o Java 5: 
- como era possível iterar em um conjunto (Set) se ele não possui acesso indexado como uma lista que possui o método get? Percorríamos uma lista através de um Iterator! 
- É um objeto que todas as coleções nos dão acesso e serve para iterar entre os objetos dentro da coleção. A ordem na qual os elementos são devolvidos pelo Iterator depende da implementação da Collection utilizada. 
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
<li>LinkedHashSet - O LinkedHashSet nos dá a performance de um HashSet mas com acesso previsível e ordenado.

```
Collection<Integer> numerosLinkedSet = new LinkedHashSet<Integer>();
```
</li>
<li>TreeSet: Para adicionarmos um objeto em um TreeSet ele precisa implementar a interface Comparable, se n receberá uma Exception (Exception in thread "main" java.lang.ClassCastException: br.com.stapait.Recibo cannot be cast to java.lang.Comparable). Mas o que fazer se estamos trabalhando com uma instância de uma classe que não temos acesso ou não podemos modificar para implementar Comparable? Nesse caso, o construtor do TreeSet recebe como parâmetro um objeto que implementa Comparator. Dessa forma, o critério de comparação pode ser criado em separado da classe na qual opera.
-por ex, a coleção deve guardar os alunos ordenados pelo número de matrícula
a coleção não pode ter elementos repetidos 
->TreeSet ao invés de HashSet e LinkedHashSet - A implementação TreeSet já ordena os seus elementos na hora da inserção. Qual é o critério da ordenação depende e pode ser definido através de um Comparator.

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
<li>Vector - Collection que é thread-safe e possui as mesmas características que um ArrayList, com a diferença de que o primeiro possui acesso sincronizado ou THREAD SAFETY e o segundo não.

</li>
<li>Map 
- Se uma chave for repetida, a antiga permanece, porém, o valor é sobrescrito pelo novo valor contido na chave passada, sendo o antigo valor "esquecido" pelo Map.
- Ela funciona da seguinte maneira, mapeia valores para chaves, e através da chave conseguimos acessar o valor correspondente. Por isso ela não pode ser repetida, ao contrário do valor, que podem existir iguais.
- HashMap

```
private Map<Integer, Aluno> matriculaParaAluno = new HashMap<>();

public Aluno buscaMatriculado(int numero) {
	if (!matriculaParaAluno.containsKey(numero)) {
		throw new NoSuchElementException("matricula não encontrada");
	}
	return matriculaParaAluno.get(numero);		
}

```
</li>
<li>LinkedHashMap - garante essa ordem de inserção - guarda a ordem do put - continua nos dando a performance de um HashMap, mas com acesso previsível e ordenado, seguindo a inserção dos seus elementos.

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