package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Conta {
    private double saldo;
    private double limiteCartao;
    private double faturaCartao;
    private List <Movimentacao> movimentacoes;
    private List <Categoria> categorias;

    public Conta(double limiteCartao) {
        this.saldo = 0.0;
        this.limiteCartao = limiteCartao;
        this.faturaCartao = 0.0;
        this.movimentacoes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(double limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public double getFaturaCartao() {
        return faturaCartao;
    }

    public void setFaturaCartao(double faturaCartao) {
        this.faturaCartao = faturaCartao;
    }

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }


    public void adicionarMovimentacao(Movimentacao m){
        movimentacoes.add(m);
        adicionarCategoria(m.getCategoria());
    }

    // só cadastra a categoria se ela ainda não existir (antes duplicava a cada movimentação)
    public void adicionarCategoria(String n){
        for(Categoria c : categorias){
            if(c.getNome().equalsIgnoreCase(n.strip())){
                return;
            }
        }
        Categoria c = new Categoria();
        c.setNome(n.strip());
        categorias.add(c);
    }

    public void exibirHistorico(){
        if(movimentacoes.isEmpty()){
            System.out.println("Nenhuma movimentação registrada na conta");
            return;
        }
        for(Movimentacao m : movimentacoes){
            System.out.println(m);
        }
        
    }

    public List<Movimentacao> filtrarPorCategoria(String categoria){
        List<Movimentacao> filtradas = new ArrayList<>();
        for(Movimentacao m : movimentacoes){
            if(m.getCategoria().equalsIgnoreCase(categoria.strip())){
                filtradas.add(m);
            }
        }
        return filtradas;
    }
    public List<Despesa> filtrarPorMeioPagamento(String metodo){
        List<Despesa> filtradas = new ArrayList<>();
        for(Movimentacao m : movimentacoes){
            if(m instanceof Despesa d){
                if(d.getMeioPagamento().toString().equalsIgnoreCase(metodo.strip())){
                    filtradas.add(d);
                }
            }
        }
        return filtradas;
    }
    
    public void exibirTotais(){
        double despesaTotal = 0;
        double receitaTotal = 0;
        LocalDate hoje = LocalDate.now();
        for(Movimentacao m : movimentacoes){
            // considera só o mês atual, já que a opção é "totais do mês"
            if(m.getData().getMonth() != hoje.getMonth() || m.getData().getYear() != hoje.getYear()){
                continue;
            }
            if(m instanceof Despesa d){
                despesaTotal += d.getValor();
            }else if(m instanceof Receita r){
                receitaTotal += r.getValor();
            }
        }
        System.out.printf("--------DADOS DO MÊS--------" +
        "%nReceita total: R$ %.2f" +
        "%nDespesa total: R$ %.2f%n", receitaTotal, despesaTotal);
        
    }
    
}
