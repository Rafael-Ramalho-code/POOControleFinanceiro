import java.util.Scanner;

import modelo.Conta;
import modelo.Despesa;
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
        double valor;
        String categoria;
        

        System.out.print("Digite o limite inicial do cartão de crédito: ");
        double limiteInicial = sc.nextDouble();
        sc.nextLine();

        Conta minhaConta = new Conta(limiteInicial);

        while (escolhaInicial != 4) {
            exibirCabecalho(minhaConta); // saldo sempre no topo, antes do menu

            System.out.print("----- SISTEMA DE CONTROLE FINANCEIRO -----\n\n" +
                    "Possiveis ações:\n" +
                    "[1] Registrar receita\n" +
                    "[2] Registrar despesa\n" +
                    "[3] Ver histórico de movimentações\n" +
                    "[4] SAIR\n" +
                    "Digite: ");
            escolhaInicial = sc.nextInt();
            sc.nextLine();

            switch (escolhaInicial) {
                case 1:
                    System.out.print("Valor da receita: ");
                    valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Categoria da receita: ");
                    categoria = sc.nextLine();

                    minhaConta.adicionarMovimentacao(new Receita(valor, categoria));
                    minhaConta.setSaldo(minhaConta.getSaldo() + valor); // atualiza saldo de fato

                    exibirCabecalho(minhaConta); // reexibe já com o saldo atualizado
                    System.out.println("Receita registrada com sucesso!");
                    System.out.println("Pressione ENTER para voltar ao menu...");
                    sc.nextLine();
                    break;

                case 2:
                    int escolha;
                    System.out.println("Valor da despesa: ");
                    valor = sc.nextDouble();
                    sc.nextLine();

                    System.out.print
                    ("--------- MÉTODO DE PAGAMENTO ---------\n"+
                    "[1] - Pix\n[2] - Cartão de crédito\nEscolha: ");
                    escolha = sc.nextInt();
                    sc.nextLine();
                    if(escolha == 1){
                        MetodoPagamento pix = new Pix();
                        pix.processarPagamento(valor, minhaConta);
                    }else if(escolha == 2){
                        MetodoPagamento cartaoCredito = new CartaoCredito();
                        cartaoCredito.processarPagamento(valor, minhaConta);
                    }else{
                        System.out.println("Digite uma opção válida");
                        continue;
                    }

                    System.out.println("Categoria da despesa: ");
                    categoria = sc.nextLine();

                    minhaConta.adicionarMovimentacao(new Despesa(valor, categoria)); // isso estava faltando
                    minhaConta.setSaldo(minhaConta.getSaldo() - valor); // desconta do saldo

                    exibirCabecalho(minhaConta);
                    System.out.println("Despesa registrada com sucesso!");
                    System.out.println("Pressione ENTER para voltar ao menu...");
                    sc.nextLine();
                    break;
                case 3:
                    exibirCabecalho(minhaConta);
                    minhaConta.exibirHistorico();
                    System.out.println("\nPressione ENTER para voltar ao menu...");
                    sc.nextLine();
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Digite uma opção válida");
            }
        }
        sc.close();
    }
}