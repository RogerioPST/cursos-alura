<ol>
<li>Array - Vetor - ArrayList no Java - Armazenamento Sequencial - Um vetor geralmente usa um array por baixo dos panos. É uma estrutura sequencial, ou seja, os elementos são armazenados um ao lado do outro.
<ul>
<li>
método adiciona com complexidade O(n) (o algoritmo tem tempo de execução linear):

```
public class Vetor {
	private Aluno[] alunos = new Aluno[100];

	public void adiciona(Aluno aluno) {
		for (int i =0; i< alunos.length ; i++) {
			if (alunos[i] == null) {
				alunos[i] = aluno;
				break;
			}
		}	
	}
```
</li>
<li>método adiciona com complexidade de tempo constante de adição:

```
public class Vetor {
	private Aluno[] alunos = new Aluno[100];
	private int totalDeAlunos = 0;
	
	public void adiciona(Aluno aluno) {
		alunos[totalDeAlunos] = aluno;
		totalDeAlunos++;
	}
```
</li>
</ul>

```
public class Vetor {
	private Aluno[] alunos = new Aluno[100];
	private int totalDeAlunos = 0;

	public void adiciona(Aluno aluno) {
		alunos[totalDeAlunos] = aluno;
		totalDeAlunos++;
	}

	public void adiciona(int posicao, Aluno aluno) {
		this.garanteEspaco();
		
		if (!posicaoValida(posicao)) {
			throw new IllegalArgumentException("posicao invalida");
		}

		for (int i = totalDeAlunos - 1; i >= posicao; i--) {
			alunos[i + 1] = alunos[i];
		}
		alunos[posicao] = aluno;
		totalDeAlunos++;
	}

	public Aluno pega(int posicao) {
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("posicao invalida");
		}
		return alunos[posicao];
	}
	
	private void garanteEspaco() {
		if (totalDeAlunos == alunos.length) {
			Aluno[] novoArray = new Aluno[alunos.length*2];
			for (int i = 0; i < alunos.length; i++) {
				novoArray[i] = alunos[i];
			}
			this.alunos = novoArray;							
		}
	}
	
	public void remove(int posicao) {
		for (int i = posicao; i <= totalDeAlunos ; i++) {
			alunos[i] = alunos[i + 1];
		}
		totalDeAlunos--;
	}

	private boolean posicaoValida(int posicao) {
		return posicao >= 0 && posicao <= totalDeAlunos;
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < totalDeAlunos;
	}

	public boolean contem(Aluno aluno) {
		for (int i = 0; i < totalDeAlunos; i++) {
			if (aluno.equals(alunos[i])) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return totalDeAlunos;
	}

	@Override
	public String toString() {
		return Arrays.toString(alunos);
	}
}
```
</li>
<li>Lista Ligada: Dado que o Vetor tem seus prós e contras, a lista ligada fará com que a adição de elementos no meio do array seja um processo mais rápido.
<ul>
<li>
A diferença dela para o Vetor é que neste os elementos estão um do lado do outro na memória, enquanto que na Lista Ligada eles estão em lugares diferentes, porém um aponta para o outro indicando o próximo
<li>
<li>
A vantagem da lista ligada é que como a relação entre duas células é feita por referências, é fácil inserir um elemento no meio da lista (afinal, basta acertar das células a esquerda e a direita).
</li>
<li>
Inserir no começo e no fim também leva tempo constante, afinal geralmente a estrutura possui referências para o primeiro e último elemento.
</li>
<li>desvantagem sobre ArrayList/Vetor: Recuperar um elemento em uma posição aleatória pode levar tempo linear. Afinal, diferente do vetor, onde pegar um elemento qualquer custa uma simples operação de array, em uma lista ligada, deve-se navegar pelas referências até encontrar o elemento desejado.
</li>
</ul>
</li>
<li>Listas duplamente ligadas: elementos não apenas apontam para seu próximo, mas também para seu anterior

```
//ListaLigada representa a lista duplamente ligada, jah q eh uma melhoria da ListaLigada simples.
public class ListaLigada {
	private Celula primeira = null;
	private Celula ultima = null;
	private int totalDeElementos =0;
	
	public void adicionaNoComeco(Object elemento) {				
		if (totalDeElementos == 0) {
			Celula nova = new Celula(elemento);
			this.primeira = nova;
			this.ultima = nova;
		} else {
			Celula nova = new Celula(this.primeira, elemento);
			this.primeira.setAnterior(nova);
			this.primeira = nova;
		}
		this.totalDeElementos++;			
	}
	public void adicionaNoFim(Object elemento) {
		if (totalDeElementos == 0) {
			adicionaNoComeco(elemento);
		} else {		
			Celula nova = new Celula(elemento);
			this.ultima.setProximo(nova);
			nova.setAnterior(ultima);
			this.ultima = nova;
			this.totalDeElementos++;
		}		
	}
	
	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.totalDeElementos;
	}
	
	private Celula pegaCelula(int posicao) {
		if (!posicaoOcupada(posicao)) {
			throw new IllegalArgumentException("posicao inexistente");
		}
		Celula atual = primeira;
		
		for (int i = 0; i < posicao; i++) {
			atual = atual.getProximo();			
		}
		return atual;
	}
	
	public void adiciona(int posicao, Object elemento) {
		if (posicao ==0) {
			adicionaNoComeco(elemento);
		} else if (posicao == this.totalDeElementos) {
			adicionaNoFim(elemento);			
		} else {
			Celula anterior = this.pegaCelula(posicao -1);
			Celula proxima = anterior.getProximo();
			Celula nova = new Celula(anterior.getProximo(), elemento);
			nova.setAnterior(anterior);
			anterior.setProximo(nova);
			proxima.setAnterior(nova);
			this.totalDeElementos++;
		}
		
	}
	public Object pega(int posicao) {
		return this.pegaCelula(posicao).getElemento();
	}
	public void removeDoComeco() {
		if (this.totalDeElementos == 0) {
			throw new IllegalArgumentException("lista vazia");		
		}
		this.primeira = this.primeira.getProximo();
		this.totalDeElementos--;
		
		if (this.totalDeElementos == 0) {
			this.ultima = null;
		}
		
	}
	public void removeDoFim() {
		if (this.totalDeElementos == 1) {
			this.removeDoComeco();
		} else {
			Celula penultima = this.ultima.getAnterior();
			penultima.setProximo(null);
			this.ultima = penultima;
			this.totalDeElementos--;			
		}
		
	}
	
	public void remove(int posicao) {
		if (posicao == 0) {
			this.removeDoComeco();
		} else if (posicao == this.totalDeElementos -1) {
			this.removeDoFim();
		} else {
			Celula anterior = this.pegaCelula(posicao-1);
			Celula atual = anterior.getProximo();
			Celula proxima =atual.getProximo();
			
			anterior.setProximo(proxima);
			proxima.setAnterior(anterior);
			this.totalDeElementos--;
		}
		
	}
	
	public int tamanho() {
		return this.totalDeElementos;
	}
	public boolean contem(Object elemento) {
		Celula atual = this.primeira;
		
		while (atual != null) {
			if (atual.getElemento().equals(elemento)) {
				return true;
			}
			atual = atual.getProximo();
		}
		return false;
	}
	@Override
	public String toString() {
		if (totalDeElementos == 0) {
			return "[]";
		} 
		Celula atual = primeira;
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < totalDeElementos; i++) {
			builder.append(atual.getElemento());
			builder.append(",");
			atual = atual.getProximo();			
		}
		builder.append("]");
		return builder.toString();
	}	
```
</li>
<li>Pilha/Stack: é uma estrutura de dados na qual o último elemento a entrar é o primeiro a sair, também conhecida como LIFO(Last-In First-Out), por exemplo, uma pilha de pratos
<ul>
<li>A inserção e remoção é constante. Afinal, ao se usar uma lista ligada por baixo, sabe-se que a inserção e a remoção do último elemento gasta tempo constante (basta acertar as referências).
</li>
<li>Usos: compiladores usam, usa-se pilha p implementar automato, pilha p guardar os estados do CTRL+Z, inverter uma palavra etc.
</li>
<li>Operações:
-push - insere no topo da pilha
-pop - retira do topo da pilha
-peek - mostra o valor do elemento do topo, mas n retira
</li>

```
public class Pilha {
	private List<String> nomes = new LinkedList<String>();
	
	public void insere(String nome) {
		nomes.add(nome);
	}
	
	public String remove() {
		return nomes.remove(nomes.size()-1);
	}
	
	public boolean estaVazia() {
		return nomes.isEmpty();
	}
	
	@Override
	public String toString() {
		return nomes.toString();
	}
}
```
</ul>
</li>
<li>Fila/Queue: Fila é uma estrutura de dados onde o primeiro a entrar é o primeiro a sair (FIFO - first in, first out). É como uma fila de banco: o primeiro que chega na fila é o primeiro a ser atendido.

```
	Queue<String> filaDoJava = new LinkedList<String>();
```
<ul>
<li>métodos:
-adiciona: add
-remove: poll
</li>
<li>Assim como a pilha, o tempo é constante. Se usarmos uma lista ligada por baixo dos panos, adicionar e remover do começo leva tempo constante.
</li>
</ul>

```
public class Fila {
	private List<String> alunos = new LinkedList<>();
	
	public void adiciona(String aluno) {
		alunos.add(aluno);
	}
	
	public String remove() {
		return alunos.remove(0);
	}
	
	public boolean estaVazia() {
		return alunos.isEmpty();
	}
	
	@Override
	public String toString() {
		return alunos.toString();
	}

}
```
</li>
<li>Conjunto/Set: Conjuntos são estruturas de dados que não permitem dados repetidos.

Para garantir que os dados não fiquem repetidos, conjuntos fazem uso de funções de hash para espalhar bem os dados, e em seguida, varrer o conteúdo de maneira eficiente.


<ul>
<li>
Teremos diversos LinkedLists, um para cada "seção" do array. Mas como organizar essas listas? Para Strings, uma boa prática é pela letra inicial que, nesse caso, dividiria o array em 26 pedaços. No final, o que teremos que fazer serão LinkedLists de LinkedLists, ou seja, listas de listas.
</li>
<li>Nós utilizamos o LinkedList para implementar nosso código, porém conseguimos melhorá-lo ainda mais se mudarmos para o ArrayList, pois sua função que pega um elemento aleatório de uma lista é muito mais rápida.
</li>
<li>Para espalhar os dados, o HashSet se utiliza do método HashCode, o qual a Classe Object do Java possui:
</li>
<li>
os elementos são guardados de maneira estruturada dentro do conjunto. Por exemplo, ao invés de guardarmos todos os nomes em uma mesma lista, temos 26 listas diferentes, uma para cada letra do alfabeto. Então, ao buscarmos pelo nome "Mauricio", ele varrerá apenas a lista com os nomes que começam com M.

Essa função de espalhamento, conhecida como função de hash é o segredo. Se ela espalhar bem os dados entre as categorias, então a busca em um conjunto será extremamente eficiente.
</li>

</ul>

```
public class Conjunto {
	private ArrayList<LinkedList<String>> tabela = new ArrayList<>();
	
	public Conjunto() {
		//uma p cada letra do alfabeto
		for (int i = 0; i < 26; i++) {
			tabela.add(new LinkedList<String>());
		}
	}
	
	public void adiciona(String palavra) {
		if (!contem(palavra)) {		
			int indice = calculaIndiceDaTabela(palavra);
			List<String> lista = tabela.get(indice);
			lista.add(palavra);
		}
	}

	public void remove(String palavra) {
		if (contem(palavra)) {
			int indice = calculaIndiceDaTabela(palavra);
			List<String> lista = tabela.get(indice);
			lista.remove(palavra);
		}
	}
	private boolean contem(String palavra) {
		int indice = calculaIndiceDaTabela(palavra);
		return tabela.get(indice).contains(palavra);
	}

	private int calculaIndiceDaTabela(String palavra) {
		//eh a funcao q espalha (hash)
		//resto da divisao vai me dar um numero de 0 a 25
		return palavra.toLowerCase().charAt(0) % 26;
	}

	@Override
	public String toString() {
		return tabela.toString();				
	}	
}
```
</li>
</ol>
