# Controle Financeiro

Sistema de controle financeiro pessoal em Java, feito em grupo para a disciplina de Programação Orientada a Objetos na UFPB.

A ideia é simples: você registra o que entra e o que sai, escolhe se pagou no Pix ou no cartão, e o programa vai mantendo o saldo, a fatura do cartão e o histórico. Tem duas formas de usar: pelo terminal (`Main.java`) ou por uma interface gráfica feita em Swing (`frontEnd/UI.java`).

## O que dá pra fazer

- Registrar receitas e despesas com categoria (mercado, salário, aluguel...)
- Pagar despesas no Pix (sai do saldo) ou no cartão de crédito (entra na fatura, respeitando o limite)
- Ver o histórico de movimentações
- Filtrar por categoria ou por método de pagamento
- Ver o total de receitas e despesas do mês

Se tentar pagar algo sem saldo, ou estourar o limite do cartão, o sistema avisa e não registra a despesa.

## Como está organizado

```
src/
├── Main.java          versão de terminal
├── frontEnd/UI.java   versão com interface gráfica (Swing)
├── modelo/            Conta, Movimentacao, Receita, Despesa, Categoria
├── pagamento/         interface MetodoPagamento + Pix e CartaoCredito
├── excecoes/          SaldoInsuficienteException, ValorInvalidoException...
└── utilidades/        Validador
```

`Movimentacao` é uma classe abstrata e `Receita`/`Despesa` herdam dela. Os métodos de pagamento implementam a mesma interface, então pra adicionar um novo (boleto, débito) é só criar outra classe com `processarPagamento`.

## Como rodar

Precisa do Java 17 ou mais recente (o código usa pattern matching no `instanceof`).

```bash
cd src
javac -d ../out $(find . -name "*.java")

# versão de terminal
java -cp ../out Main

# versão com interface gráfica
java -cp ../out frontEnd.UI
```

Também dá pra abrir a pasta no IntelliJ ou no VS Code e rodar direto a `Main` ou a `UI`.

## O que ainda falta

- Salvar os dados em arquivo ou banco (hoje tudo some quando fecha o programa)
- Pagar a fatura do cartão
- Escolher a data da movimentação em vez de usar sempre a data de hoje

## Equipe

- Davi Malheiros
- Matheus Gomes
- Rafael Ramalho
