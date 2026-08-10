package modelo;

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
        AdicionarCategoria(m.getCategoria());
    }

    public void AdicionarCategoria(String n){
        Categoria c = new Categoria();
        c.setNome(n);
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
}
