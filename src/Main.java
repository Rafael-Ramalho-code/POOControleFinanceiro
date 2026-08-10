import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import excecoes.SaldoInsuficienteException;
import modelo.Conta;
import modelo.Despesa;
import modelo.Movimentacao;
import modelo.Receita;
import pagamento.CartaoCredito;
import pagamento.MetodoPagamento;
import pagamento.Pix;

public class Main {

    // Limpa o terminal (ANSI)
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Cabeçalho com saldo, chamado antes de cada exibição do menu
    public static void exibirCabecalho(Conta conta) {
        limparTela();
        System.out.println("");
        System.out.printf("Saldo atual: R$ %.2f%n", conta.getSaldo());
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolhaInicial = 0;
        double valor = 0;
        String categoria;

        boolean valido = false;
        double limiteInicial = 0;
        while (!valido) {
            System.out.print("Digite o limite inicial do cartão de crédito: ");
            try {
                limiteInicial = sc.nextDouble();
                sc.nextLine();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número");
                sc.nextLine();
            }
        }
        Conta minhaConta = new Conta(limiteInicial);

        while (escolhaInicial != 6) {
            boolean valido1 = false;
            exibirCabecalho(minhaConta); // saldo sempre no topo, antes do menu
            while (!valido1) {
                System.out.print("----- SISTEMA DE CONTROLE FINANCEIRO -----\n\n" +
                        "Possiveis ações:\n" +
                        "[1] Registrar receita\n" +
                        "[2] Registrar despesa\n" +
                        "[3] Ver histórico de movimentações\n" +
                        "[4] Filtrar movimentações\n" +
                        "[5] Filtrar por método de pagamento\n" +
                        "[6] SAIR\n" +
                        "Digite: ");
                try {
                    escolhaInicial = sc.nextInt();
                    sc.nextLine();
                    valido1 = true;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Digite um número");
                    sc.nextLine();
                }
            }
            switch (escolhaInicial) {
                case 1:
                    boolean valido2 = false;
                    while (!valido2) {
                        System.out.print("Valor da receita: ");
                        try {
                            valor = sc.nextDouble();
                            sc.nextLine();
                            valido2 = true;
                            System.out.println("Categoria da receita: ");
                            categoria = sc.nextLine();

                            minhaConta.adicionarMovimentacao(new Receita(valor, categoria));
                            minhaConta.setSaldo(minhaConta.getSaldo() + valor); // atualiza saldo de fato

                            exibirCabecalho(minhaConta); // reexibe já com o saldo atualizado
                            System.out.println("Receita registrada com sucesso!");
                            System.out.println("Pressione ENTER para voltar ao menu...");
                            sc.nextLine();

                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número");
                            sc.nextLine();
                        }
                    }
                    break;

                case 2:
                    boolean valido3 = false;
                    MetodoPagamento meioPagamento = null;
                    int escolha;
                    while (!valido3) {
                        System.out.println("Valor da despesa: ");
                        try {
                            valor = sc.nextDouble();
                            sc.nextLine();
                            valido3 = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida! Digite um número");
                            sc.nextLine();
                        }
                    }
                    valido3 = false;
                    while(!valido3){
                        System.out.print("--------- MÉTODO DE PAGAMENTO ---------\n" +
                                "[1] - Pix\n[2] - Cartão de crédito\nEscolha: ");
                        try{
                            escolha = sc.nextInt();
                            sc.nextLine();
                            if(escolha == 1){
                                meioPagamento = new Pix();
                                valido3 = true;
                            }else if(escolha == 2){
                                meioPagamento = new CartaoCredito();
                                valido3 = true;
                            }else{
                                System.out.println("Opção inválida. Digite [1] ou [2]");
                            }
                            
                            
                        }catch(InputMismatchException e){
                            System.out.println("Entrada inválida! Digite um número");
                            sc.nextLine();
                        }
                        
                    }
                    
                    
                    try {
                        meioPagamento.processarPagamento(valor, minhaConta);
                        System.out.println("Categoria da despesa: ");
                        categoria = sc.nextLine();

                        minhaConta.adicionarMovimentacao(new Despesa(valor, categoria, meioPagamento)); // isso estava
                                                                                                        // faltando

                        exibirCabecalho(minhaConta);
                        System.out.println("Despesa registrada com sucesso!");
                        System.out.println("Pressione ENTER para voltar ao menu...");
                        sc.nextLine();
                        break;
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        sc.nextLine();
                        break;
                    }

                case 3:
                    exibirCabecalho(minhaConta);
                    minhaConta.exibirHistorico();
                    System.out.println("\nPressione ENTER para voltar ao menu...");
                    sc.nextLine();
                    break;
                case 4:
                    exibirCabecalho(minhaConta);
                    System.out.print("Digite a categoria que quer filtrar: ");
                    String categoriaEscolhida = sc.nextLine();
                    List<Movimentacao> resultado = minhaConta.filtrarPorCategoria(categoriaEscolhida);
                    if (resultado.isEmpty()) {
                        System.out.println("Categoria não encontrada");
                    } else {
                        for (Movimentacao m : resultado) {
                            System.out.println(m);
                        }
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    exibirCabecalho(minhaConta);
                    System.out.print("Digite o método de pagamento para filtrar: ");
                    String pagamento = sc.nextLine();
                    List<Despesa> filtradas = minhaConta.filtrarPorMeioPagamento(pagamento);
                    if (filtradas.isEmpty()) {
                        System.out.println("Nenhuma despesa encontrada com esse método de pagamento");
                    } else {
                        for (Despesa d : filtradas) {
                            System.out.println(d);
                        }
                        System.out.println("\nPressione ENTER para voltar ao menu...");
                        sc.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
        sc.close();
    }
}