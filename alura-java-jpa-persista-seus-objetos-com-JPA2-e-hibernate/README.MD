## CICLO DE VIDA JPA:
![ciclo vida jpa 1](ciclo_vida_jpa.PNG "ciclo vida jpa 2")

1. TRANSIENT: 
- nunca foi persistido, n tem ID, n está sendo gerenciado pela JPA
- como se fosse objeto JAVA puro
- n vai salvar no BD se sincronizar/fizer commit da transação

2. MANAGED:
- JPA está de olho e qualquer alteração nos atributos, vai persistir p o BD qdo ocorrer flush/commit
- se eu n quiser encerrar a transação, mas quiser sincronizar com o banco, usar metodo flush() (por ex, p gerar ID no BD)
- metodo clear() serve para limpar as entidades gerenciadas pelo Entity Manager (EM) deixando no estado DETACHED.

3. DETACHED: 
- já tem ID, já está salvo no BD, mas n está mais sendo GERENCIADO pelo EM e por isso, se mexer nos valores dos atributos, n vai alterar nd no BD.
- com o metodo merge(), volta-se o estado p MANAGED (atraves de mais um SELECT).
- o metodo merge() tem uma pegadinha: ele não muda o estado dessa ENTIDADE passada como argumento para MANAGED; ele devolve uma REFERENCIA e essa SIM está com o estado MANAGED: celulares = em.merge(celulares); (CERTO) x em.merge(celulares); (ERRADO).

4. REMOVED:
- com o metodo remove(), estando no estado MANAGED, vai gerar um DELETE no BD.
- exemplo de metodo remover na DAO:

```
	categoria = em.merge(categoria);
	this.em.remove(categoria);
```

## SELECT:
 
```
jpql = "SELECT p.preco from Produto where p.categoria.nome = :nome";
```

- qdo for buscar pelo nome da categoria do produto, por ex, por ser O.O., já vai fazer o join. 
- qdo quiser apenas buscar um atributo no SELECT, usar 'p.preco', por ex.

### FORMA 1 DE RETORNAR P RELATORIO LISTA DE ATRIBUTOS DE ENTIDADES DIFERENTES:
- devolver List<Object[]> e createQuery(jpql, Object[]):

```
SELECT produto.nome,SUM(item.quantidade),MAX(pedido.data)
FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC
```

### FORMA 2 DE RETORNAR P RELATORIO LISTA DE ATRIBUTOS DE ENTIDADES DIFERENTES:
- VO (Value Object) ou DTO (Data Transfer Object): devolver List<RelatorioDeVendasVo> e createQuery(jpql, RelatorioDeVendasVo):
- para isso, usar o new e ter construtor compativel e usar o caminho completo da classe RelatorioDeVendasVo, incluindo pacote:

```
SELECT new br.com.rogerio.vo.RelatorioDeVendasVo(produto.nome,SUM(item.quantidade),MAX(pedido.data))
FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto GROUP BY produto.nome ORDER BY item.quantidade DESC
```


## RELACIONAMENTOS BIDIRECIONAIS

- p n serem criadas duas tabelas diferentes, precisa do mappedBy com o nome do atributo já mapeado da outra tabela:

- cascade: tudo que acontecer com o pedido (persistir, remover etc), faça tambem no ItemPedido:

- metodo 'adicionarItem' é usado para adicionar tanto o pedido no ItemPedido como o o ItemPedido no itens da classe Pedido e lembrar de acumular o valorTotal:

```
class ItemPedido{
	...
	@ManyToOne
	private Pedido pedido;	
}
class Pedido{
	...	
	@OneToMany(mappedBy= "pedido", cascade=CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	void adicionarItem (ItemPedido item){
		item.setPedido(this);
		this.itens.add(item);
		this.valorTotal = this.valorTotal.add(item.getValor());
	}
}
```

## Vantagens das named queries

- Deixar as consultas de uma entidade mais próximas da classe dela: As named queries são declaradas na classe da entidade

## CARREGAMENTO EAGER - RELACIONAMENTOS ToOne (MAnyToOne, OneToOne) SEMPRE TERÃO O SELECT/JOIN FEITO SEMPRE QUE CARREGAR A ENTIDADE PRINCIPAL:

- inclusive de forma cascata

- boa pratica é em todo relacionamento toOne, colocar o (fetch= FetchType.LAZY) na classe da Entidade:

```
class Pedido{
@ManyToOne(fetch= FetchType.LAZY)
private Cliente cliente;
}
```
- N+1: Para não ter o problema do N+1 é necessário montar a querie na mão, utilizando o 'JOIN FETCH':

- JOIN FETCH: para evitar o 'LazyInitializationException': caso vc precise fazer um carregamento da entidade relacionada, já traga os resultados no APENAS NESSA CONSULTA usando 'JOIN FETCH', ao invés de fazer 'pedido.getCliente().getNome()' q pode acontecer fora da classe DAO e em qualquer outro momento, pois o EntityManager pode estar fechado e causar a exception LazyInitializationException:

```
SELECT p FROM  Pedido p JOIN FETCH p.cliente
```

- mesmo com EntityManager fechado, se eu acessasse 'pedido.getData()' n daria a LazyInitializationException, pq eh info do pedido e n tem carregamento de entidade relacionada.



## CARREGAMENTO LAZY - RELACIONAMENTOS ToMany (MAnyToMany, OneToMany) NÃO TERÃO O SELECT/JOIN FEITO SEMPRE QUE CARREGAR A ENTIDADE PRINCIPAL:

- só vai carregar a lista se eu acessar esse atributo em questão, por ex, o size do 'pedido.getItens().size()'.

- para evitar o 'LazyInitializationException': caso vc precise fazer um carregamento da entidade relacionada, já traga os resultados no select inicial


## CRITERIA API - com parâmetros opcionais:

- qdo for  usar parametros q podem ser nulos e n aparecerem no SELECT, até o proprio hibernate usa o WHERE 1=1 p n colocar IF p verificar presença de WHERE/AND etc:

-desvantagem: Código mais complexo de entender e manter

## Simplificando entidades com Embeddable

- qdo for criar uma entidade, mas quiser q seus atributos continuem mapeados p a mesma tabela onde ela está sendo referenciada,
criar a entidade com @Embeddable e mapear o atributo com @Embedded.

## Herança:

- A anotação @Inheritance deve ser adicionada apenas na classe base

- @Inheritance(strategy = InheritanceType.SINGLE_TABLE): cria uma unica tabela

- @Inheritance(strategy = InheritanceType.JOINED): cria tabelas separadas

## CHAVE COMPOSTA:

- normalmente, é criada uma classe com o nome da Entidade+Id , por ex, CategoriaId com @Embeddable e implementar Serializable.

- e o atributo CategoriaId id com @EmbeddedId. 