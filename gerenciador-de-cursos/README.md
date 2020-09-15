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
```
</li>
<li>ArrayList: o get de ArrayList é muito rápido, mas inserir, remover é muito lenta, pois precisa modificar a posição de todos os outros elementos afetados.

LinkedList: o get é muito lento, pois vai precisar percorrer toda lista, mas adicionar e remover elementos na cabeça da lista é mais rápido que ArrayList:

</li>
</ul>