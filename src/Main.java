import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int escolhaInicial=0;

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
                    //registrarReceita();
                    break;
                case 2:
                    //registrarDespesa();
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

    }
}