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
```
</li>
<li>ArrayList: o get de ArrayList � muito r�pido, mas inserir, remover � muito lenta, pois precisa modificar a posi��o de todos os outros elementos afetados.

LinkedList: o get � muito lento, pois vai precisar percorrer toda lista, mas adicionar e remover elementos na cabe�a da lista � mais r�pido que ArrayList:

</li>
</ul>