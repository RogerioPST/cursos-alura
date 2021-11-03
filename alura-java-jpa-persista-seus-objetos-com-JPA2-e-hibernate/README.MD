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
