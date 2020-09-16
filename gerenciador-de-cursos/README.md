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
</ul>