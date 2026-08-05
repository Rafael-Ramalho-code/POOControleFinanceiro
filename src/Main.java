import modelo.Conta;
import modelo.Receita;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolhaInicial=0;
        double valor;
        String categoria;
        String tipo;
        String id;

        System.out.print("Digite o limite inicial do cartão de crédito: ");
        double limiteInicial = sc.nextDouble();
        sc.nextLine();

        Conta minhaConta = new Conta(limiteInicial);

        while (escolhaInicial != 5){
            System.out.print("----- SISTEMA DE CONTROLE FINANCEIRO -----\n\n" +
                    "Possiveis ações:\n" +
                    "[1] Registrar receita\n" +
                    "[2] Registrar despesa\n" +
                    "[3] Alterar movimentação\n" +
                    "[4] Ver saldo\n" +
                    "[5] SAIR\n" +
                    "Digite: ");
            escolhaInicial = sc.nextInt();
            switch (escolhaInicial){
                case 1:

                    System.out.print("Valor da receita: ");
                    valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Data da receita(digite nesse padrao DD/MM/YYYY): ");
                    System.out.println("Categoria da receita: ");
                    categoria = sc.nextLine();
                    tipo = sc.nextLine();
                    id = sc.nextLine();
                    minhaConta.adicionarMovimentacao(new Receita(valor, categoria, tipo, id));
                    break;
                case 2:
                    System.out.println("Valor da despesa: ");
                    valor = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Categoria da despesa: ");
                    categoria = sc.nextLine();
                    break;
                case 3:
                    //alterarMovimentacao();
                    break;
                case 4:
                    //exibirSaldo();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Digite uma opção válida");

            }
        }
        sc.close();
        

    }
}