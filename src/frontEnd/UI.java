package frontEnd;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import modelo.Conta;
import modelo.Despesa;
import modelo.Movimentacao;
import modelo.Receita;
import pagamento.CartaoCredito;
import pagamento.MetodoPagamento;
import pagamento.Pix;
import excecoes.SaldoInsuficienteException;

public class UI {

    private static Conta minhaConta;
    private static JFrame frame;
    private static JLabel labelSaldo;

    public static void main(String[] args) {
        double limiteInicial = lerLimiteInicial();
        minhaConta = new Conta(limiteInicial);
        criarJanelaPrincipal();
    }

    private static double lerLimiteInicial() {
        while (true) {
            String entrada = JOptionPane.showInputDialog(null,
                    "Digite o limite inicial do cartão de crédito:",
                    "Controle Financeiro - Configuração Inicial",
                    JOptionPane.QUESTION_MESSAGE);
            if (entrada == null) {
                System.exit(0); // usuário cancelou -- fecha o programa
            }
            try {
                return Double.parseDouble(entrada.trim().replace(",", "."));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida! Digite um número.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void criarJanelaPrincipal() {
        frame = new JFrame("Controle Financeiro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        labelSaldo = new JLabel();
        labelSaldo.setFont(new Font("SansSerif", Font.BOLD, 16));
        atualizarLabelSaldo();
        painelPrincipal.add(labelSaldo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(6, 1, 8, 8));

        JButton btnReceita = new JButton("Registrar Receita");
        JButton btnDespesa = new JButton("Registrar Despesa");
        JButton btnHistorico = new JButton("Ver Histórico");
        JButton btnFiltrarCategoria = new JButton("Filtrar por Categoria");
        JButton btnFiltrarPagamento = new JButton("Filtrar por Método de Pagamento");
        JButton btnTotais = new JButton("Ver Totais");

        btnReceita.addActionListener(e -> registrarReceita());
        btnDespesa.addActionListener(e -> registrarDespesa());
        btnHistorico.addActionListener(e -> exibirHistorico());
        btnFiltrarCategoria.addActionListener(e -> filtrarPorCategoria());
        btnFiltrarPagamento.addActionListener(e -> filtrarPorMetodoPagamento());
        btnTotais.addActionListener(e -> exibirTotais());

        painelBotoes.add(btnReceita);
        painelBotoes.add(btnDespesa);
        painelBotoes.add(btnHistorico);
        painelBotoes.add(btnFiltrarCategoria);
        painelBotoes.add(btnFiltrarPagamento);
        painelBotoes.add(btnTotais);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    private static void atualizarLabelSaldo() {
        labelSaldo.setText(String.format("Saldo atual: R$ %.2f", minhaConta.getSaldo()));
    }

    // ---------- Registrar Receita ----------
    private static void registrarReceita() {
        Double valor = lerValorMonetario("Valor da receita:");
        if (valor == null) return; // cancelado

        String categoria = JOptionPane.showInputDialog(frame, "Categoria da receita:");
        if (categoria == null || categoria.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Categoria não pode ser vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        minhaConta.adicionarMovimentacao(new Receita(valor, categoria));
        minhaConta.setSaldo(minhaConta.getSaldo() + valor);
        atualizarLabelSaldo();

        JOptionPane.showMessageDialog(frame, "Receita registrada com sucesso!");
    }

    // ---------- Registrar Despesa ----------
    private static void registrarDespesa() {
        Double valor = lerValorMonetario("Valor da despesa:");
        if (valor == null) return;

        String[] opcoes = {"Pix", "Cartão de Crédito"};
        int escolha = JOptionPane.showOptionDialog(frame,
                "Escolha o método de pagamento:", "Método de Pagamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) return; // cancelado

        MetodoPagamento meioPagamento = (escolha == 0) ? new Pix() : new CartaoCredito();

        try {
            meioPagamento.processarPagamento(valor, minhaConta);
        } catch (SaldoInsuficienteException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Pagamento não realizado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String categoria = JOptionPane.showInputDialog(frame, "Categoria da despesa:");
        if (categoria == null || categoria.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Categoria não pode ser vazia.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        minhaConta.adicionarMovimentacao(new Despesa(valor, categoria, meioPagamento));
        atualizarLabelSaldo();

        JOptionPane.showMessageDialog(frame, "Despesa registrada com sucesso!");
    }

    // ---------- Histórico ----------
    private static void exibirHistorico() {
        List<Movimentacao> movimentacoes = minhaConta.getMovimentacoes();
        if (movimentacoes.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma movimentação registrada na conta.");
            return;
        }
        mostrarListaEmJanela("Histórico de Movimentações", movimentacoes);
    }

    // ---------- Filtrar por Categoria ----------
    private static void filtrarPorCategoria() {
        String categoria = JOptionPane.showInputDialog(frame, "Digite a categoria para filtrar:");
        if (categoria == null || categoria.isBlank()) return;

        List<Movimentacao> resultado = minhaConta.filtrarPorCategoria(categoria);
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Categoria não encontrada.");
        } else {
            mostrarListaEmJanela("Movimentações - Categoria: " + categoria, resultado);
        }
    }

    // ---------- Filtrar por Método de Pagamento ----------
    private static void filtrarPorMetodoPagamento() {
        String[] opcoes = {"Pix", "Cartão de Crédito"};
        int escolha = JOptionPane.showOptionDialog(frame,
                "Escolha o método de pagamento para filtrar:", "Filtrar por Método de Pagamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == JOptionPane.CLOSED_OPTION) return;

        String metodo = opcoes[escolha];
        List<Despesa> resultado = minhaConta.filtrarPorMeioPagamento(metodo);
        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma despesa encontrada com esse método de pagamento.");
        } else {
            mostrarListaEmJanela("Despesas - Método: " + metodo, resultado);
        }
    }

    // ---------- Totais ----------
    private static void exibirTotais() {
        double totalReceitas = 0;
        double totalDespesas = 0;

        for (Movimentacao m : minhaConta.getMovimentacoes()) {
            if (m instanceof Despesa d) {
                totalDespesas += d.getValor();
            } else if (m instanceof Receita r) {
                totalReceitas += r.getValor();
            }
        }

        String mensagem = String.format(
                "Receita total: R$ %.2f%nDespesa total: R$ %.2f",
                totalReceitas, totalDespesas);

        JOptionPane.showMessageDialog(frame, mensagem, "Totais", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------- Auxiliares ----------

    private static void mostrarListaEmJanela(String titulo, List<? extends Movimentacao> lista) {
        StringBuilder texto = new StringBuilder();
        for (Movimentacao m : lista) {
            texto.append(m.toString()).append("\n\n");
        }

        JTextArea area = new JTextArea(texto.toString());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(frame, scroll, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    private static Double lerValorMonetario(String mensagem) {
        while (true) {
            String entrada = JOptionPane.showInputDialog(frame, mensagem);
            if (entrada == null) return null; // usuário cancelou

            try {
                double valor = Double.parseDouble(entrada.trim().replace(",", "."));
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(frame, "O valor deve ser maior que zero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Entrada inválida! Digite um número (ex: 150.50).", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}