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
</ul>